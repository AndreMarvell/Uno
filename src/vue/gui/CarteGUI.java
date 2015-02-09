package gui;


import java.awt.Image;


import javax.swing.*;

import carte.Carte;

/**
 * Classe representant graphiquement une carte de jeu dans l'interface
 * @author ikounga_marvel
 *
 */
public class CarteGUI extends JPanel{

	private JLabel imageCarte = new JLabel();
	private ImageIcon icon ;
	private static final long serialVersionUID = 1L;
	private Carte carte;
	
	/**
	 * Constructeur
	 * @param c Carte du panel
	 */
	public CarteGUI(Carte c) {
		
		this.setCarte(c);
		this.setOpaque(false);
		this.setBorder(null);
		icon = new ImageIcon(new ImageIcon(getClass().getResource("/"+c.getImage())).getImage().getScaledInstance(77, 120, Image.SCALE_DEFAULT));
		imageCarte.setIcon(icon);
	    this.add(imageCarte);
	    setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
	}

	/**
	 * Getter de la carte
	 * @return the carte
	 */
	public Carte getCarte() {
		return carte;
	}
	/**
	 * Setter de la carte
	 * @param carte the carte to set
	 */
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	
	
	
}
