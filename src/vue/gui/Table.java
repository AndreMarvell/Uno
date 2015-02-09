package gui;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import guiControleur.MancheGUIControleur;
import guiControleur.PartieGUIControleur;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import carte.Carte;
import carte.CarteSpeciale;

import customForm.Bouton;
import customForm.Fenetre;

import partie.Manche;



import sauvegarde.Sauvegarde;
import table.*;
import joueur.*;

/**
 * Classe modelisant la fenetre de jeu de l'application
 * @author ikounga_marvel
 */
public class Table extends Fenetre implements Observer{


	private static final long serialVersionUID = 1L;
	private MainGUI main;
    private TalonGUI panelTalon;
    private Bouton pioche = new Bouton();
    private JLabel sensHoraire = new JLabel();
    private JLabel sensAntihoraire = new JLabel();
    private ListeJoueur joueurs;
    private JButton direUno;
    private MancheGUIControleur manche;
    private PartieGUIControleur partie;
    //private JLabel affichage =  new JLabel();
    private JTextArea affichage =  new JTextArea();

	/**
     * Constructeur
     * @param partie Controleur de la partie
     */
    public Table(PartieGUIControleur partie) {
    	super("Table.png");    	
    	this.partie = partie;
    	this.joueurs = partie.getJoueurs();
    	/* Activation du sous menu recommencer*/
    	this.activerMenuRecommencer();
    	this.getBarredeMenu().setJoueurs(joueurs);
    	
    	initComponents();
    }

    /**
     * Methode permettant d'initialiser les composants de la table de jeu
     */
    private void initComponents() {
    	
    	int i=0;
    	
    	/* Initialisation du bouton direUno*/
    	direUno = new Bouton();
    	direUno.setIcon(new ImageIcon(getClass().getResource("/unobutton.png")));
    	direUno.setBounds(840, 430, 50, 50);
        getZoneAffichage().add(direUno,0);
        direUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	direUnoActionPerformed(evt);
            }
        });

        
        /* Initialisation du Label d'affichage*/
		affichage.setFont(new java.awt.Font("Wawati SC", 1, 15));
		affichage.setForeground(new java.awt.Color(0,0,0));
		affichage.setBounds(310, 150, 600, 30);
		affichage.setEditable(false);
		getZoneAffichage().add(affichage,0);
		
    	/* Initialisation de l'image de la pioche*/
		pioche.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/back.png")).getImage().getScaledInstance(77, 120, Image.SCALE_DEFAULT)));
		pioche.setBounds(450, 230, 80, 130);
		getZoneAffichage().add(pioche,0);
		pioche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	piocheActionPerformed(evt);
            }
        });
		
		/*Initialisation des icones de sens des manche*/
		sensHoraire.setBorder(null);
		sensHoraire.setIcon(new ImageIcon(getClass().getResource("/sensHoraire.png")));
		getZoneAffichage().add(sensHoraire,0);
		sensHoraire.setBounds(320,220,80,120);
		sensAntihoraire.setBorder(null);
		sensAntihoraire.setIcon(new ImageIcon(getClass().getResource("/sensAntihoraire.png")));
		getZoneAffichage().add(sensAntihoraire,0);
		sensAntihoraire.setVisible(false);
		sensAntihoraire.setBounds(800,220,80,120);
		
		/*Initialisation des interfaces de joueurs de la partie*/
    	Iterator<Joueur> it= joueurs.getListe().iterator();
    	while(it.hasNext()){
            getZoneAffichage().add(new JoueurGUI(i+1, it.next(),joueurs.compterJoueurs()),0);
            i++;
    	}
    	
    	partie.nouvelleManche();
    	manche = partie.getMancheCourante();
    	manche.getManche().addObserver(this);
     	
    	
    	panelTalon = new TalonGUI(manche.getManche().getTalon());
    	main = new MainGUI(joueurs, manche);
    	getZoneAffichage().add(panelTalon,0);
    	getZoneAffichage().add(main,0);
    	
    	manche.getManche().getDealer().setActive(true);
    	this.redirigerSortieStandard(); 
    	this.setVisible(true);
    	// On realise l'effect de la premiere carte du talon
    	if(manche.getManche().getTalon().getCarteDessusDuTalon() instanceof CarteSpeciale){
    		manche.tourSuivant(true,1);
    	}
    	
    	
         
    }

    /**
     * Methode definissant l'action lancee lorsque le joueur clique sur la pioche pour piocher une carte ou passer son tour
     * @param evt evenement du clic
     */    
	protected void piocheActionPerformed(ActionEvent evt) {
		Joueur j = main.joueur;
		if(j.isActive()){
			Talon t = manche.getManche().getTalon();
			Pioche p = manche.getManche().getPioche();
			j.passerSonTour(p,t);
			Carte cTireDansPioche = j.getMain().getMain().get(j.getMain().getMain().size()-1);
			
			if(cTireDansPioche.verifier(t.getCarteDessusDuTalon())){
				int conteste = JOptionPane.showConfirmDialog(null, "La carte piochee "+cTireDansPioche+" est jouable. Voulez-vous la jouer?","Se defausser?", JOptionPane.YES_NO_OPTION);
				switch(conteste){
					case JOptionPane.YES_OPTION: 
						j.poserEtRetirer(t, cTireDansPioche);
						manche.tourSuivant(true,2);
						break;
					case JOptionPane.NO_OPTION: 
						manche.tourSuivant(false,2);
						break;
					default : System.out.println("Choix Incorrecte");
				}
			}
			else{
				manche.tourSuivant(false,2);
			}
			
		}
	
	}

    /**
     * Methode definissant l'action lancee lorsque le joueur clique sur le bouton Uno
     * @param evt evenement du clic
     */	
	protected void direUnoActionPerformed(ActionEvent evt) {
		
		Joueur j = main.joueur;
		if(j.isActive()){
			j.setUno(true);
		}
		
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg0 instanceof Manche && ((String) arg1).equals("Fin Manche")){
			
			Manche m = (Manche) arg0;
			joueurs.compterScoreVainqueur(m.getVainqueur());
			Object[] options = {"OK"};
			JOptionPane.showOptionDialog(null, "Le vainqueur est "+m.getVainqueur().getPseudonyme()+"\nManche Suivante","Fin de la manche", JOptionPane.PLAIN_MESSAGE,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
			
			if(joueurs.joueurMeneur().getScore()>500){
				Joueur gagnant = joueurs.joueurMeneur();
				JOptionPane.showOptionDialog(null, "La partie est terminee! Bravo a "+gagnant.getPseudonyme(),"Fin de la Partie", JOptionPane.PLAIN_MESSAGE,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
				// Sauvegarde du score du gagnant
				try {
					Sauvegarde.sauvegarderScore(gagnant);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new Accueil().setVisible(true);
				this.setVisible(false);
				this.dispose();
			}
			else{
				nouvelleManche();
			}
			

		}
		
		if(((String) arg1).equals("sens")){
			if(manche.getManche().isSens()){
				sensHoraire.setVisible(true);
				sensAntihoraire.setVisible(false);
			}
			else{
				sensHoraire.setVisible(false);
				sensAntihoraire.setVisible(true);
			}
		}
		
	}
	
	/**
	 * Methode permettant de lancer une nouvelle manche
	 */
	public void nouvelleManche(){
		
		manche.getManche().deleteObserver(this);
		
		partie.nouvelleManche();
    	manche = partie.getMancheCourante();
    	manche.getManche().addObserver(this);
    	
    	affichage.setText("");
    	sensHoraire.setVisible(true);
		sensAntihoraire.setVisible(false);
		
    	panelTalon.setTalon(manche.getManche().getTalon());
    	main.setManche(manche);
    	manche.getManche().getDealer().setActive(true);
    	
    	// On realise l'effect de la premiere carte du talon
    	if(manche.getManche().getTalon().getCarteDessusDuTalon() instanceof CarteSpeciale){
    		manche.tourSuivant(true,1);
    	}
	}

	/**
	 * Methode permettant d'afficher du texte recu de l'outputStream vers un JtextArea
	 * @author Source Web
	 * @param text texte a afficher
	 */  	
	private void afficherDansTextArea(final String text) {
		affichage.setText("");
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	    	  affichage.append(text);
	      }
	    });
	  }

	/**
	 * Methode permettant de diriger la sortie standart vers un composant JtextArea
	 * @author Source Web
	 */	
	  private void redirigerSortieStandard() {
	    OutputStream out = new OutputStream() {
	      @Override
	      public void write(int b) throws IOException {
	    	  afficherDansTextArea(String.valueOf((char) b));
	      }

	      @Override
	      public void write(byte[] b, int off, int len) throws IOException {
	    	  afficherDansTextArea(new String(b, off, len));
	      }

	      @Override
	      public void write(byte[] b) throws IOException {
	        write(b, 0, b.length);
	      }
	    };

	    System.setOut(new PrintStream(out, false));
	  }


}
