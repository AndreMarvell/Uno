package partie;

import java.util.Observable;


import table.Pioche;
import table.Talon;
import joueur.Joueur;
import joueur.ListeJoueur;

/**
 *Classe representant la manche d'une partie
 *
 * @author aureliengoulon
 *
 */
public class Manche extends Observable {

	private boolean sens;
	private Joueur vainqueur;
	private Joueur dealer;
	private Talon talon = new Talon();
	private Pioche pioche;
	private ListeJoueur joueurs;
	private boolean finPartie = false;
	

	/**
	 * Constructeur de la classe
	 * @param sens le sens dans lequel les joueurs jouent
	 * @param vainqueur le joueur vainqueur de la manche
	 * @param dealer le joueur dealer de la manche
	 */
	public Manche(Joueur vainqueur, Joueur dealer) {
		super();
		this.vainqueur = vainqueur;
		this.dealer = dealer;
	}
	
	public Manche(ListeJoueur j) {
		super();
		this.sens = true;
		this.joueurs = j;
	}
	
	/**
	 * setter des cartes du talon
	 * @param sens sens de la manche
	 * @return void
	 */
	public void setSens(boolean sens) {
		this.sens = sens;
		setChanged();
		notifyObservers();
	}

	/**
	 * Methode permettant de savoir si le sens de la manche
	 * @return boolean
	 */
	public boolean isSens() {
		return sens;
	}

	/**
	 * getter du vainqueur de la manche
	 * @return vainqueur le vainqueur de la manche
	 */
	public Joueur getVainqueur() {
		return vainqueur;
	}

	/**
	 * setter du vainqueur de la manche
	 * @param vainqueur le joueur a affecter comme vainqueur
	 * @return void
	 */
	public void setVainqueur(Joueur vainqueur) {
		this.vainqueur = vainqueur;
		setChanged();
		notifyObservers("Fin Manche");
	}

	/**
	 * getter du dealer de la manche
	 * @return dealer le dealer de la mnche
	 */
	public Joueur getDealer() {
		return dealer;
	}

	/**
	 * setter du dealer de la manche
	 * @param dealer le joueur a nommer comme dealer
	 * @return void
	 */
	public void setDealer(Joueur dealer) {
		this.dealer = dealer;
		setChanged();
		notifyObservers();
	}

	/**
	 * getter du talon de la manche
	 * @return talon le talon actuel de la manche
	 */
	public Talon getTalon() {
		return talon;
	}

	/**
	 * setter du talon
	 * @param talon le talon a affecter a la manche
	 * @return void
	 */
	public void setTalon(Talon talon) {
		this.talon = talon;
		setChanged();
		notifyObservers();
	}

	/**
	 * getter de la pioche de la manche
	 * @return pioche la pioche actuelle de la manche
	 */
	public Pioche getPioche() {
		return pioche;
	}

	/**
	 * setter de la pioche
	 * @param pioche la pioche a affecter a la manche
	 * @return void
	 */
	public void setPioche(Pioche pioche) {
		this.pioche = pioche;
	}

	/**
	 * getter de la liste de joueurs
	 * @return pioche la pioche actuelle de la manche
	 */
	public ListeJoueur getJoueurs() {
		return joueurs;
	}

	/**
	 * setter de la pioche
	 * @param joueurs les joeurs a affecter a la liste de joeurs
	 * @return void
	 */
	public void setJoueurs(ListeJoueur joueurs) {
		this.joueurs = joueurs;

	}

	/**
	 * Methode permettant d'afficher le dealer d'une manche
	 * @return void
	 */
	public void afficher() {
		System.out.println("Le dealer de cette manche est " + dealer + ".");
		
	}
	

	
	/**
	 * Methode permettant de changer le sens d'une manche
	 * @return void
	 */
	public void changerSens(){
		if (sens) {
			sens=false;
		} else {
			sens=true;
		}
		setChanged();
		notifyObservers("sens");
	}

	/**
	 * @return the finPartie
	 */
	public boolean isFinPartie() {
		return finPartie;
	}

	/**
	 * @param finPartie the finPartie to set
	 */
	public void setFinPartie(boolean finPartie) {
		this.finPartie = finPartie;
	}	
}