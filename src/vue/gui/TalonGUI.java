package gui;

import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import carte.*;

import table.Talon;

/**
 * Classe representant graphiquement le talon dans l'interface
 * @author ikounga_marvel
 *
 */
public class TalonGUI extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private JLabel imageTalon = new JLabel();
	Talon talon;
	
	/**
	 * Constructeur
	 * @param t Talon a affecter au Panel
	 */
	public TalonGUI(Talon t){
		
		talon = t;
		talon.addObserver(this);
		Carte c = talon.getCarteDessusDuTalon();
		if(c instanceof Joker || c instanceof Plus4){
			c.addObserver(this);
		}
		talon.getCarteDessusDuTalon().addObserver(this);
		
		imageTalon.setOpaque(false);
		imageTalon.setHorizontalAlignment(JLabel.CENTER);
		imageTalon.setBounds(0, 0, 80, 130);
		
		ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("/"+t.getCarteDessusDuTalon().getImage())).getImage().getScaledInstance(77, 120, Image.SCALE_DEFAULT));
		imageTalon.setIcon(icon);
		
	    this.add(imageTalon, 0);
	    this.setOpaque(false);
		this.setBorder(null);
	    this.setBounds(650, 230, 80, 130);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Carte){
			Carte c = (Carte) arg;
			if(c instanceof Joker || c instanceof Plus4){
				c.addObserver(this);
			}
		}
		repaint();
		ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("/"+talon.getCarteDessusDuTalon().getImage())).getImage().getScaledInstance(77, 120, Image.SCALE_DEFAULT));
		imageTalon.setIcon(icon);
		

	}

	/**
	 * Setter du Talon en cas de nouvelle Manche
	 * @param talon the talon to set
	 */
	public void setTalon(Talon talon) {
		this.talon = talon;
		talon.addObserver(this);
		ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("/"+talon.getCarteDessusDuTalon().getImage())).getImage().getScaledInstance(77, 120, Image.SCALE_DEFAULT));
		imageTalon.setIcon(icon);
	}

}