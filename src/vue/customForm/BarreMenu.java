package customForm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import gui.Accueil;
import gui.Help;
import gui.Scores;
import gui.Table;
import guiControleur.PartieGUIControleur;

import javax.swing.*;

import sauvegarde.Sauvegarde;

import joueur.ListeJoueur;

/**
 * Classe modelisant la barre de menu de l'application
 * @author ikounga_marvel
 *
 */
public class BarreMenu extends JMenuBar{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu menuAbout;
    private JMenu menuAide;
    private JMenu menuJeu;
    private JMenuItem quitter;
    private JMenuItem sousMenuAboutUno;
    private JMenuItem sousMenuCopyright;
    private JMenuItem sousMenuPrincipal;
    private JMenuItem sousMenuRecommencer;
    private JMenuItem sousMenuRegle;
    private JMenuItem sousMenuScore;
    private ListeJoueur joueurs;
    
	/**
	 * Constructeur de la classe
	 */
	public BarreMenu() {
		initComponents();
	}
	
	/**
	 * Methode permettant d'initialiser l'ensemble des composants de la barre de menu
	 * @return void
	 */
	private void initComponents() {

        menuJeu = new JMenu();
        sousMenuRecommencer = new JMenuItem();
        sousMenuPrincipal = new JMenuItem();
        sousMenuScore = new JMenuItem();
        quitter = new JMenuItem();
        menuAide = new JMenu();
        sousMenuRegle = new JMenuItem();
        menuAbout = new JMenu();
        sousMenuAboutUno = new JMenuItem();
        sousMenuCopyright = new JMenuItem();


        menuJeu.setText("Jeu");

        sousMenuRecommencer.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.META_MASK));
        sousMenuRecommencer.setText("Recommencer");
        sousMenuRecommencer.setEnabled(false);
        sousMenuRecommencer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sousMenuRecommencerActionPerformed(evt);
            }
        });
        
        menuJeu.add(sousMenuRecommencer);

        sousMenuPrincipal.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        sousMenuPrincipal.setText("Menu Principal");
        sousMenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	sousMenuPrincipalActionPerformed(evt);
            }
        });
        menuJeu.add(sousMenuPrincipal);

        sousMenuScore.setText("Consulter les Scores");
        menuJeu.add(sousMenuScore);    
        sousMenuScore.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		sousMenuScoresActionPerformed(evt);
        	}
        });

        quitter.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.META_MASK));
        quitter.setText("Quitter");
        quitter.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		quitterActionPerformed(evt);
        	}
        });
        menuJeu.add(quitter);

        this.add(menuJeu);

        menuAide.setText("Aide");

        sousMenuRegle.setText("Regles de Jeu");
        sousMenuRegle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sousMenuRegleActionPerformed(evt);
            }
        });
        menuAide.add(sousMenuRegle);

        this.add(menuAide);

        menuAbout.setText("A Propos");

        sousMenuAboutUno.setText("Uno!");
        menuAbout.add(sousMenuAboutUno);
        menuAbout.add(sousMenuAboutUno);
        sousMenuAboutUno.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		sousMenuAboutUnoActionPerformed(evt);
        	}
        });  
        

        menuAbout.add(sousMenuCopyright);
        sousMenuCopyright.setText("Copyright");
        sousMenuCopyright.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		sousMenuCopyrightActionPerformed(evt);
        	}
        });       

        this.add(menuAbout);

    }
	
    /**
     * Methode definissant l'action lancee lors du choix du sous menu Quitter
     * @param evt evenement du clic
     */
    private void quitterActionPerformed(ActionEvent evt) {
    	int conteste = JOptionPane.showConfirmDialog(null, "Etes vous sur?","Quitter Jeu", JOptionPane.YES_NO_OPTION);
		switch(conteste){
			case JOptionPane.YES_OPTION:
				JFrame fenetre = ((JFrame) SwingUtilities.getRoot(this));
				if(fenetre instanceof Table){
					int save = JOptionPane.showConfirmDialog(null, "Sauvegarder la Partie?","Quitter Jeu", JOptionPane.YES_NO_OPTION);
					switch(save){
						case JOptionPane.YES_OPTION: 
						try {
							Sauvegarde.sauvegarderPartie(joueurs);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							break;
						case JOptionPane.NO_OPTION: break;
						default : System.out.println("Choix Incorrecte");
					}
				}
				System.exit(0);
				break;
			case JOptionPane.NO_OPTION: break;
			default : System.out.println("Choix Incorrecte");
		}
		
	}
    /**
     * Methode definissant l'action lancee lors du choix du sous menu Menu Principale
     * @param evt evenement du clic
     */
	private void sousMenuPrincipalActionPerformed(ActionEvent evt) {
		JFrame fenetre = ((JFrame) SwingUtilities.getRoot(this));
		if(fenetre instanceof Table){
			int conteste = JOptionPane.showConfirmDialog(null, "Sauvegarder la Partie?","Quitter Jeu", JOptionPane.YES_NO_OPTION);
			switch(conteste){
				case JOptionPane.YES_OPTION: 
				try {
					Sauvegarde.sauvegarderPartie(joueurs);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
				case JOptionPane.NO_OPTION: break;
				default : System.out.println("Choix Incorrecte");
			}
		}
			((JFrame) SwingUtilities.getRoot(this)).setVisible(false);
			((JFrame) SwingUtilities.getRoot(this)).dispose();
			new Accueil().setVisible(true);
    	
		
		
	}
	
    /**
     * Methode definissant l'action lancee lors du choix du sous menu recommencer
     * @param evt evenement du clic
     */
	private void sousMenuRecommencerActionPerformed(java.awt.event.ActionEvent evt) {
    	int conteste = JOptionPane.showConfirmDialog(null, "Etes vous sur?","Recommencer partie", JOptionPane.YES_NO_OPTION);
		switch(conteste){
			case JOptionPane.YES_OPTION: 
				joueurs.remiseAZero();
		    	PartieGUIControleur partie = new PartieGUIControleur(joueurs);
				new Table(partie).setVisible(true);
				((JFrame) SwingUtilities.getRoot(this)).setVisible(false);
				((JFrame) SwingUtilities.getRoot(this)).dispose();
				break;
			case JOptionPane.NO_OPTION: break;
			default : System.out.println("Choix Incorrecte");
		}
    	
    }
	
    /**
     * Methode definissant l'action lancee lors du choix du sous menu copyright
     * @param evt evenement du clic
     */
    private void sousMenuCopyrightActionPerformed(java.awt.event.ActionEvent evt) {
    	JFrame fenetreCopyright = new JFrame();
    	
    	fenetreCopyright.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    	fenetreCopyright.setSize(400,200);
    	fenetreCopyright.setVisible(true);
    	fenetreCopyright.setLayout(new FlowLayout());
    	JLabel label = new JLabel("Andre Marvell IKOUNGA & Aurelien Goulon, Copyright \u00a9 2013");
    	label.setBounds(10, 10, 380, 180);
    	fenetreCopyright.add(label);
    	
    	fenetreCopyright.pack();
    	fenetreCopyright.setLocationRelativeTo(null);
    }
    
    /**
     * Methode definissant l'action lancee lors du choix du sous menu score
     * @param evt evenement du clic
     */    
    private void sousMenuScoresActionPerformed(java.awt.event.ActionEvent evt) {
    	Fenetre score = new Scores(); 
    	score.setVisible(true);
    	score.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    /**
     * Methode definissant l'action lancee lors du choix du sous menu about
     * @param evt evenement du clic
     */    
    private void sousMenuAboutUnoActionPerformed(java.awt.event.ActionEvent evt) {
    	JFrame fenetreUno = new JFrame();

    	fenetreUno.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    	fenetreUno.setSize(400,200);
    	fenetreUno.setVisible(true);
    	fenetreUno.setLayout(new FlowLayout());
    	JLabel label = new JLabel("Projet de LO02 modelisant le jeu de Uno a l'aide de Java et UML");
    	label.setBounds(10, 10, 380, 180);
    	fenetreUno.add(label);
    	fenetreUno.pack();
    	fenetreUno.setLocationRelativeTo(null);
    }
    
    /**
     * Methode definissant l'action lancee lors du choix du sous menu regle
     * @param evt evenement du clic
     */
    private void sousMenuRegleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sousMenuRegleActionPerformed
        JFrame fenetreRegle = new Help();
        fenetreRegle.setVisible(true);
        // Permet de fermer la fenêtre sans quitter le programme
        fenetreRegle.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


	/**
	 * Setter de la liste de joueurs dans la barre de menu
	 * @param joueurs the joueurs to set
	 */
	public void setJoueurs(ListeJoueur joueurs) {
		this.joueurs = joueurs;
	}

	/**
	 * Getter du sous menu recommencer
	 * @return the sousMenuRecommencer
	 */
	public JMenuItem getSousMenuRecommencer() {
		return sousMenuRecommencer;
	}
	
	
		   
}
