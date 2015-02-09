package carte;

import java.util.Observable;

import table.Talon;


/**
 * Classe modelisant la carte de jeu
 * @author ikounga_marvel
 *
 */
public abstract class Carte extends Observable {
	
	private String couleur;
	private int points;
	private String image;

	/**
	 * Constructeur de la Carte
	 * @param couleur couleur de la carte
	 * @param points nombre de points rapportes par la carte
	 * @param image image de la carte
	 */
	public Carte(String couleur, int points, String image) {
		super();
		this.couleur = couleur;
		this.points = points;
		this.image = image;
	}

	/**
	 * getters de la couleur
	 * @return la couleur
	 */
	public String getCouleur() {
		return couleur;
	}
	
	/**
	 * getter du nombre de points
	 * @return points nombre de points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * setter du nombre de points
	 * @param points nombre de points a affecter
	 * @return void
	 */
	public void setPoints(int points) {
		this.points = points;

	}
	
	/**
	 * setter de la couleur
	 * @param couleur couleur a affecter
	 * return void
	 */
	public void setCouleur(String couleur) {
		this.couleur = couleur;

	}

	/**
	 * Methode permettant de jouer une carte sur le talon
	 * @param  c carte au dessus du talon 
	 * @return void
	 */
	public void jouer(Talon t) {
		t.ajouter(this);
	}
	
	/**
	 * Methode permettant de verifier la compatibilite entre la carte et celle au dessus du talon
	 * @param  c carte au dessus du talon 
	 * @return boolean
	 */
	public abstract boolean verifier(Carte c);

	/**
	 * Methode permettant de savoir si une carte est speciale 
	 * @return boolean
	 */
	public abstract boolean isSpecial();
	
	/**
	 * Methode permettant de connaitre le numero ou la specialite de la carte 
	 * Equivalent de getNumero et getSpecialite
	 * @return String
	 */
	public abstract String getTypeCarte();

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
}