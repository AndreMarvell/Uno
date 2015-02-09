package gui;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.event.ActionEvent;

import guiControleur.PartieGUIControleur;

import javax.swing.*;

import customForm.Bouton;
import customForm.Fenetre;

import sauvegarde.Sauvegarde;
import strategie.*;

import joueur.Joueur;
import joueur.JoueurVirtuel;
import joueur.ListeJoueur;


/**
 * Classe modelisant la fenetre 
 * @author ikounga_marvel
 */
public class InitialisationJeu extends Fenetre {

	private JButton boutonNouvellePartie;
    private JButton boutonChargerPartie;
    private JButton lancerPartie;
	private static final long serialVersionUID = 1L;
	private JSpinner spinnerNbReel = new JSpinner();
	private JLabel nbReel = new JLabel();
	private JSpinner spinnerNbVirtuel = new JSpinner();
	private JLabel nbVirtuel = new JLabel();
	private JButton validerNombreJoueur;
	private JPanel panelSaisieJoueur;
	private JLabel message = new JLabel();

	/**
     * Constructeur de la classe
     */
    public InitialisationJeu() {
    	super("initialisation.jpg");
        initComponents();
    }

	/**
	 * Methode permettant d'initialiser l'ensemble des composants de la fenetre
	 * @return void
	 */
    private void initComponents() {

    	/* Initialisation du panel */
    	panelSaisieJoueur = new JPanel();
    	panelSaisieJoueur.setOpaque(false);
    	panelSaisieJoueur.setLayout(new java.awt.GridLayout(2, 5));
    	panelSaisieJoueur.setVisible(false);
    	panelSaisieJoueur.setBounds(100, 200, 1000, 400);
    	getZoneAffichage().add(panelSaisieJoueur,0); 
    	
    	/* Initialisation des boutons */
    	boutonNouvellePartie = new Bouton();
    	boutonNouvellePartie.setIcon(new ImageIcon(getClass().getResource("/buttonNew.png")));
        boutonNouvellePartie.setBounds(370, 300, 110, 110);
        getZoneAffichage().add(boutonNouvellePartie,0);
    	
    	boutonChargerPartie = new Bouton();
    	boutonChargerPartie.setIcon(new ImageIcon(getClass().getResource("/buttonLoad.png")));
        boutonChargerPartie.setBounds(750, 300, 110, 110);
        getZoneAffichage().add(boutonChargerPartie,0);
    	
    	lancerPartie = new Bouton();
    	lancerPartie.setIcon(new ImageIcon(getClass().getResource("/ok.png")));
    	lancerPartie.setVisible(false);
        lancerPartie.setBounds(1000, 530, 110, 110);
        getZoneAffichage().add(lancerPartie,0);
    	
    	validerNombreJoueur = new Bouton();
    	validerNombreJoueur.setIcon(new ImageIcon(getClass().getResource("/ok.png")));
    	validerNombreJoueur.setVisible(false);
    	validerNombreJoueur.setBounds(550, 300, 110, 110);
    	getZoneAffichage().add(validerNombreJoueur,0);
    	
    	/* Initialisation des labels et Spinner de demande de nombre de joueurs Reels*/
    	nbReel.setFont(new java.awt.Font("Wawati SC", 0, 23));
    	nbReel.setText("Nombre de Joueur Reels");
    	nbReel.setHorizontalAlignment(JLabel.CENTER);
    	nbReel.setBounds(450, 200, 300, 50);
    	nbReel.setVisible(false);
    	getZoneAffichage().add(nbReel,0);
    	
    	spinnerNbReel.setModel(new SpinnerNumberModel(1, 1, 10, 1));
    	spinnerNbReel.setBounds(550, 260, 110, 30);
    	spinnerNbReel.setVisible(false);
    	getZoneAffichage().add(spinnerNbReel,0);
    	
    	/* Initialisation des labels et Spinner de demande de nombre de joueurs Virtuel*/
    	nbVirtuel.setFont(new java.awt.Font("Wawati SC", 0, 23));
    	nbVirtuel.setText("Nombre de Joueur Virtuels");
    	nbVirtuel.setHorizontalAlignment(JLabel.CENTER);
    	nbVirtuel.setBounds(450, 300, 300, 50);
    	nbVirtuel.setVisible(false);
    	getZoneAffichage().add(nbVirtuel,0);
    	
    	
    	spinnerNbVirtuel.setBounds(550, 360, 110, 30);
    	spinnerNbVirtuel.setVisible(false);
    	getZoneAffichage().add(spinnerNbVirtuel,0);
    	

    	/* initialisation du label message */
    	message.setFont(new java.awt.Font("Wawati SC", 0, 23));
    	message.setHorizontalAlignment(JLabel.CENTER);
    	message.setText("Entrez les pseudos et les strategies des joueurs");
    	message.setBounds(300, 175, 600, 30);
    	message.setVisible(false);
    	getZoneAffichage().add(message,0);
    	
        boutonNouvellePartie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	boutonNouvellePartieActionPerformed(evt);
            }
        });
        
        boutonChargerPartie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	boutonChargerPartieActionPerformed(evt);
            }
        });
        
        lancerPartie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	lancerPartieActionPerformed(evt);
            }
        });
        
        validerNombreJoueur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	validerNombreJoueurActionPerformed(evt);
            }
        });
        

    }

    /**
     * Methode definissant l'action lancee lors du clic du bouton Charger Partie
     * @param evt evenement du clic
     */
    private void boutonChargerPartieActionPerformed(ActionEvent evt) {
		
    	ListeJoueur lj = Sauvegarde.chargerPartie().getJoueurs();
    	Object[] options = {"OK"};
		JOptionPane.showOptionDialog(null, "Partie rechargee avec succes\n"+lj,"Chargement Partie", JOptionPane.PLAIN_MESSAGE,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
    	PartieGUIControleur partie = new PartieGUIControleur(lj);
		new Table(partie).setVisible(true);
		this.setVisible(false);
		this.dispose();    	
		
	}

    /**
     * Methode definissant l'action lancee lors du clic du bouton Nouvelle Partie
     * @param evt evenement du clic
     */    
	private void boutonNouvellePartieActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	boutonNouvellePartie.setVisible(false);
    	boutonChargerPartie.setVisible(false);
    	nbReel.setVisible(true);
    	spinnerNbReel.setVisible(true);
    	validerNombreJoueur.setVisible(true);
 
    }
    
    /**
     * Methode definissant l'action lancee lors du clic du bouton de validation du nombre de joueur
     * @param evt evenement du clic
     */	
    private void validerNombreJoueurActionPerformed(java.awt.event.ActionEvent evt) {
    	int nbJoueurReel = (Integer) spinnerNbReel.getValue();
    	
    	if(!spinnerNbVirtuel.isVisible()){
    		nbVirtuel.setVisible(true);
    		if(nbJoueurReel>1){
    			spinnerNbVirtuel.setModel(new SpinnerNumberModel(0, 0, 10-nbJoueurReel, 1));
    		}
    		else{
    			spinnerNbVirtuel.setModel(new SpinnerNumberModel(1, 1, 10-nbJoueurReel, 1));
    		}
    		
    		spinnerNbVirtuel.setVisible(true);
    		spinnerNbReel.setEnabled(false);
        	validerNombreJoueur.setBounds(550, 400, 110, 110);
    	}
    	else{
    		
    		message.setVisible(true);
    		int nbJoueurVirtuel = (Integer) spinnerNbVirtuel.getValue();
    		
    		validerNombreJoueur.setVisible(false);
    		nbVirtuel.setVisible(false);
    		nbReel.setVisible(false);
    		spinnerNbVirtuel.setVisible(false);
    		spinnerNbReel.setVisible(false);
    		lancerPartie.setVisible(true);
    		
    		for(int i=0;i<nbJoueurReel;i++){
    			panelSaisieJoueur.add(new CadranSaisieInfo(i+1));
    		}
    		
    		for(int i=nbJoueurReel;i<(nbJoueurReel+ nbJoueurVirtuel);i++){
    			panelSaisieJoueur.add(new CadranSaisieInfoOrdinateur(i+1));
    		}
    		
    		panelSaisieJoueur.setVisible(true);
    		
    		
    	}
    	
        
    }

    /**
     * Methode definissant l'action lancee lors du lancement de la partie
     * @param evt evenement du clic
     */
    private void lancerPartieActionPerformed(java.awt.event.ActionEvent evt) {
        
    	int nbJoueurReel = (Integer) spinnerNbReel.getValue();
    	int nbJoueurVirtuel = (Integer) spinnerNbVirtuel.getValue();
    	ListeJoueur joueurs = new ListeJoueur();
    	
    	
    	for(int i=0;i<nbJoueurReel;i++){
    		String pseudonyme = ((CadranSaisieInfo) panelSaisieJoueur.getComponent(i)).pseudo.getText();
    		joueurs.ajouterJoueur(new Joueur(pseudonyme));
		}
		
		for(int i=nbJoueurReel;i<(nbJoueurReel+ nbJoueurVirtuel);i++){
			JoueurVirtuel jV = new JoueurVirtuel("Ordinateur"+(i+(1-nbJoueurReel)));
			String strategie = ((CadranSaisieInfoOrdinateur) panelSaisieJoueur.getComponent(i)).strategie.getSelectedItem().toString();
			
			if(strategie=="Agressif"){
				jV.setStrategie( new JouerAgressif(jV));
			}else{
				if(strategie=="AgressifAvecBluff"){
					jV.setStrategie( new JouerAgressifAvecBluff(jV));
				}else{
					if(strategie=="Normal"){
						jV.setStrategie( new JouerNormal(jV));
					}else{
						jV.setStrategie( new JouerPassif(jV));
					}
				}
			}
			
			joueurs.ajouterJoueur(jV);
		}
		
		PartieGUIControleur partie = new PartieGUIControleur(joueurs);
		new Table(partie).setVisible(true);
		this.setVisible(false);
		this.dispose();
    	
    }

}
