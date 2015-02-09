package gui;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;

import customForm.Bouton;
import customForm.Fenetre;


/**
 * Classe modelisant la fenetre d'accueil de l'application
 * @author ikounga_marvel
 */
public class Accueil extends Fenetre {

	private JButton boutonCharger;
    private JButton boutonJouer;
    private JButton boutonScore;
	private static final long serialVersionUID = 1L;

	/**
     * Constructeur de la classe
     */
    public Accueil() {
    	super("backgroundAccueil.jpg");
        initComponents();
    }

	/**
	 * Methode permettant d'initialiser l'ensemble des composants de la fenetre
	 * @return void
	 */
    private void initComponents() {

        boutonJouer = new Bouton();
        boutonCharger = new Bouton();
        boutonScore = new Bouton();


        boutonJouer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonJouerActionPerformed(evt);
            }
        });
        getZoneAffichage().add(boutonJouer,0);
        boutonJouer.setBounds(313, 395, 110, 105);

        getZoneAffichage().add(boutonCharger,0);
        boutonCharger.setBounds(551, 395, 110, 105);

        
        boutonScore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonScoreActionPerformed(evt);
            }
        });
        getZoneAffichage().add(boutonScore,0);
        boutonScore.setBounds(780, 395, 110, 105);

    }

    /**
     * Methode definissant l'action lancee lors du clic du bouton Jouer
     * @param evt evenement du clic
     */
    private void boutonJouerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonJouerActionPerformed
    	Fenetre initialisation=new InitialisationJeu(); 
    	initialisation.setVisible(true);
    	initialisation.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    	this.setVisible(false);
		this.dispose();
    }

    /**
     * Methode definissant l'action lancee lors du clic du bouton Score
     * @param evt evenement du clic
     */    
    private void boutonScoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonScoreActionPerformed
        // TODO add your handling code here:
    	Fenetre score = new Scores(); 
    	score.setVisible(true);
    	score.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }



    /**
     * Methode main, permettant de lancer l'application
     * @param args the command line arguments
     * @return void
     */
    public static void main(String args[]) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Accueil().setVisible(true);
            }
        });
    }
    

}
