package partie;

import java.util.Observable;

import joueur.ListeJoueur;

/**
 *Classe reprŽsentant une partie de jeu
 *
 * @author aureliengoulon
 *
 */
public class Partie extends Observable {

	private int nbManche;
	private int nbJoueur;
	private String difficulte;
	private ListeJoueur joueurs;

	private boolean sauvegardee;
	private boolean chargee;


	/**
	 * Constructeur de la classe
	 * @param joueurs liste de joueurs de la partie
	 */
	public Partie(ListeJoueur joueurs) {
		super();
		this.joueurs = joueurs;
	}
	
	/**
	 * @return the joueurs
	 */
	public ListeJoueur getJoueurs() {
		return joueurs;
	}

	/**
	 * @param joueurs the joueurs to set
	 */
	public void setJoueurs(ListeJoueur joueurs) {
		this.joueurs = joueurs;
		setChanged();
		notifyObservers();
	}

	/**
	 * @param sauvegardee the sauvegardee to set
	 */
	public void setSauvegardee(boolean sauvegardee) {
		this.sauvegardee = sauvegardee;
	}

	/**
	 * getter du nombre de manches
	 * @return nbManches le nombre de manches ecoulees
	 */
	public int getNbManche() {
		return nbManche;
	}

	/**
	 * setter du nombre de manche
	 * @param nbManches le nombre de manches ecoulees
	 * @return void
	 */
	public void setNbManche(int nbManche) {
		this.nbManche = nbManche;
		setChanged();
		notifyObservers();
	}

	/**
	 * getter du nombre de joueurs dans la partie
	 * @return nbJoueurs le nombre de joueurs dans la partie
	 */
	public int getNbJoueur() {
		return nbJoueur;
	}

	/**
	 * setter du nombre de joueurs
	 * @param nbJoueurs le nombre de joueurs a ajouter a la partie
	 * @return void
	 */
	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}

	/**
	 * getter de la difficulte de la partie
	 * @return difficulte la difficulte de la partie
	 */
	public String getDifficulte() {
		return difficulte;
	}

	/**
	 * setter de la difficulte
	 * @param difficulte la difficulte a affecter a la partie
	 * @return void
	 */
	public void setDifficulte(String difficulte) {
		this.difficulte = difficulte;
	}

	/**
	 * setter de la sauvegarde de la partie
	 * @param sauvegardee la sauvegarde a enregistrer
	 * @return void
	 */
	public void setSauvegarde(boolean sauvegardee) {
		this.sauvegardee = sauvegardee;
	}

	/**
	 * Methode permettant de savoir si la partie a ete sauvegardee
	 * @return boolean
	 */
	public boolean isSauvegardee() {
		return sauvegardee;
	}

	/**
	 * setter du chargement de la partie
	 * @param chargee le chargement a effectuer
	 * @return void
	 */	
	public void setChargee(boolean chargee) {
		this.chargee = chargee;
	}

	/**
	 * Methode permettant de savoir si la partie a ete chargee a partir d'une sauvegarde
	 * @return boolean
	 */
	public boolean isChargee() {
		return chargee;
	}	

	/**
	 * Methode permettant d'afficher le statut d'une partie
	 * @return void
	 */
	public void afficher() {
		if (isChargee() == true) {
				System.out.println("Partie chargee");
		}
		if (isSauvegardee() == true) {
				System.out.println("Partie sauvegardee");
		}
		
	}
	
	
}