package gui;


import guiControleur.MancheGUIControleur;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import joueur.*;

import carte.Carte;

import table.Main;
import table.Talon;

/**
 * Classe representant graphiquement la main d'un joueur dans l'interface
 * @author ikounga_marvel
 *
 */
public class MainGUI extends JScrollPane implements Observer{

	ListeJoueur joueurs;
	MancheGUIControleur manche;
	Main main;
	Talon talon;
	Joueur joueur;
	private static final long serialVersionUID = 1L;
	private JPanel gridMain = new JPanel();
	



	/**
	 * Constructeur
	 * @param liste Liste de Joueur
	 * @param manche Controleur de la manche
	 */
	public MainGUI(ListeJoueur liste, MancheGUIControleur manche){
		
		/* Recuperation du talon*/
		this.manche = manche;
		talon = manche.getManche().getTalon();
		joueurs = liste;
		
		/* Ajout de l'obervers main a tous les joueurs*/
		Iterator<Joueur> it = liste.getListe().iterator();
		while(it.hasNext()){
			Joueur j = it.next();
			j.addObserver(this);
		}
		
		/* Initialisation de la structure graphique de la main*/
		
        this.getViewport().setOpaque(false);
        this.setViewportBorder(null);
        this.setBorder(null);
        gridMain.setOpaque(false);
        gridMain.setLayout(new java.awt.GridLayout(1, 10));
        this.setViewportView(gridMain);
        this.setBounds(380, 395, 450, 150);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        

	}
	
	/**
	 * Setter du controleur de la manche en cas de nouvelle manche
	 * @param manche the manche to set
	 */
	public void setManche(MancheGUIControleur manche) {
		this.manche = manche;
		talon = manche.getManche().getTalon();
	}
	

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		
		joueur = (Joueur) arg0;
		main = joueur.getMain();
		
		if(joueur.isActive() ){
			if(!joueur.isVirtuel()){
				gridMain.removeAll();
				gridMain.revalidate();
				gridMain.repaint();
				for(Carte c : main.getMain()){
					CarteGUI cGui = new CarteGUI(c);
					gridMain.add(cGui);
					cGui.addMouseListener(new MouseAdapter(){
						@Override
						public void mouseReleased(MouseEvent arg0) {
							carteMouseReleased(arg0);
						}
				    });					
				}
				
			}
			else{
				if(arg1 instanceof String && ((String)arg1).equals("active")){
					this.setEnabled(false);
					SwingUtilities.invokeLater(new Runnable() {
			            public void run() {
			            	if(joueur instanceof JoueurVirtuel){
			            		manche.tourSuivant(joueur.jouerSonTour(manche.getManche().getPioche(), talon),2);
			            	}
			            	try {
		        				Thread.sleep(500);
		        			} catch (InterruptedException e) {
		        				// TODO Auto-generated catch block
		        				e.printStackTrace();
		        			}
			            }
			        });
	            			
				}
				
			}
							
		}
		
		
	}
	
	/**
	 * Methode gerant l'action lancee lorsqu'un joueur clic sur une carte a jouer
	 * @param arg0 evenement du clic 
	 */
	protected void carteMouseReleased(MouseEvent arg0) {
		
		Carte c= ((CarteGUI) arg0.getSource()).getCarte();
		
		if(c.verifier(talon.getCarteDessusDuTalon())){
			int indexDansMain = main.getMain().indexOf(c);
			joueur.jouerCarte(talon, indexDansMain);
			
			if(joueur.getMain().compterCarte()==1){
				SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		            	try {
	        				Thread.sleep(3000);
	        				
	        			} catch (InterruptedException e) {
	        				e.printStackTrace();
	        			}
		            	SwingUtilities.invokeLater(new Runnable() {
				            public void run() {
				            	manche.tourSuivant(true,2);
				            }
				        });
		            	
		            }
		        });
			}
			else{
				manche.tourSuivant(true, 2);
			}
			
			
		}

	}
}
