package gui;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;


import javax.swing.*;

import sauvegarde.Sauvegarde;

import customForm.Fenetre;



/**
 * Classe representant la fenetre d'affichage des scores
 * @author ikounga_marvel
 */
public class Scores extends Fenetre {


	private static final long serialVersionUID = 1L;
	private JTextArea affichage;
	
	/**
     * Constructeur
     */
    public Scores() {
    	super("Score.jpg");
    	this.redirigerSortieStandard();
        initComponents();
        
    }

	/**
	 * Methode permettant d'initialiser l'ensemble des composants de la fenetre
	 * @return void
	 */
    private void initComponents() {
    	
    	affichage = new JTextArea();
    	affichage.setFont(new java.awt.Font("Wawati SC", 1, 15));
		affichage.setForeground(new java.awt.Color(0,0,0));
		affichage.setBounds(393, 210, 380, 238);
		affichage.setEditable(false);
		getZoneAffichage().add(affichage,0);

		// On fait appel a la fonction lireScore() pour afficher les 10 meilleurs scores du jeu
		try {
			Sauvegarde.lireScore();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }


	/**
	 * Methode permettant d'afficher du texte recu de l'outputStream vers un JtextArea
	 * @author Source Web
	 * @param text texte a afficher
	 */    
	private void printInTextArea(final String text) {
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
	    	  printInTextArea(String.valueOf((char) b));
	      }

	      @Override
	      public void write(byte[] b, int off, int len) throws IOException {
	    	  printInTextArea(new String(b, off, len));
	      }

	      @Override
	      public void write(byte[] b) throws IOException {
	        write(b, 0, b.length);
	      }
	    };

	    System.setOut(new PrintStream(out, false));
	  }

}
