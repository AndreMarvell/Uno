package customForm;


import javax.swing.*;

/**
 * Classe modelisant l'ensemble des boutons utilises dans l'application
 * @author ikounga_marvel
 *
 */
public class Bouton extends JButton {


	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de la classe
	 */
	public Bouton() {
		
        setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        setBorderPainted(false);
        setContentAreaFilled(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	}

}
