package carte;

import partie.Manche;
import joueur.Joueur;


/**
 * Classe modelisant la carte speciale
 * @author ikounga_marvel
 *
 */
public abstract class CarteSpeciale extends Carte{
	
	private String specialite;

	/**
	 * @param couleur couleur de la carte
	 * @param points points que rapportent la carte
	 * @param specialite specialite de la carte
	 * @param image image de la carte
	 */
	public CarteSpeciale(String couleur, int points, String specialite, String image) {
		super(couleur, points, image);
		this.specialite = specialite;
	}

	/**
	 * getter de la specialite
	 * @return the specialite
	 */
	public String getSpecialite() {
		return specialite;
	}
	
	/**
	 * Methode permettant de savoir si une carte est spŽciale
	 * @return boolean true
	 */
	@Override
	public boolean isSpecial() {
		return true;
	}

	
	/**
	 * Methode permettant d'afficher une carte speciale
	 * @return void
	 */
	public void afficher() {
		System.out.print(" (" + specialite +", "+this.getCouleur()+")");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " (" + specialite +", "+this.getCouleur()+")";
	}

	/**
	 * Methode permettant de verifier la compatibilite entre la carte et celle au dessus du talon
	 * @param  c carte au dessus du talon 
	 * @return boolean
	 */
	@Override
	public boolean verifier(Carte c) {
		
		if (c.isSpecial() == false) {
			if (c.getCouleur() == this.getCouleur()) {
				return true;
			} else {
				return false;
			}
			
		} else {
			if (c.getCouleur() == this.getCouleur()|| c.getCouleur() == "multi" || this.getSpecialite() == c.getTypeCarte()) {
				return true;
			}
			else {
				return false;
			}
		}
	} 
	
	/**
	 * Methode permettant de connaitre le numero ou la specialite de la carte 
	 * Equivalent et getSpecialite
	 * @return String
	 */
	public String getTypeCarte() {
		return this.specialite;
	}
	
	/**
	 * Methode permettant de realiser l'effet de la carte speciale sur le jeu
	 * @param joueurs Liste de joueur de la partie
	 * @param joueurActuel joueur actuel qui vient d' effectuer son tour de jeu
	 * @param tourDeJeu numero de tour de jeu
	 * @return
	 */
	public abstract Joueur agir(Manche manche, Joueur joueurActuel, int tourDeJeu);
}
