package table;
import java.util.ArrayList;
import java.util.Observable;

import carte.Carte;

/**
 *Classe representant le talon d'une manche
 *
 * @author aureliengoulon
 *
 */
public class Talon extends Observable {
	
	private ArrayList<Carte> cartesTalon;

	/**
	 * Constructeur de la classe
	 * @param cartesTalon les cartes constituant le talon
	 */
	public Talon(ArrayList<Carte> cartesTalon) {
		super();
		this.cartesTalon = cartesTalon;

	}
	
	/**
	 * Constructeur de la classe
	 * @param carte Premiere qui constituera le talon
	 */
	public Talon(Carte carte) {
		super();
		this.cartesTalon = new ArrayList<Carte>();
		cartesTalon.add(carte);
	}
	
	/**
	 * Constructeur de la classe
	 */
	public Talon() {
		super();
	}
	
	/**
	 * Methode permettant de recuperer la carte au dessus du talon
	 * @return la carte au dessus
	 */
	public Carte getCarteDessusDuTalon(){
		return cartesTalon.get(cartesTalon.size()-1);
	}
	
	/**
	 * getter des cartes au talon
	 * @return les cartes dans le talon
	 */
	public ArrayList<Carte> getCartesTalon() {
		return cartesTalon;
	}

	/**
	 * setter des cartes du talon
	 * @param cartesTalon a affecter au talon
	 * @return void
	 */
	public void setCartesTalon(ArrayList<Carte> cartesTalon) {
		this.cartesTalon = cartesTalon;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Methode permettant l'ajout d'une carte valide sur le talon
	 * @param  c carte qui sera ajoutee 
	 */
	public void ajouter(Carte c) {
		cartesTalon.add(c);
		setChanged();
		notifyObservers(c);
	}

	/**
	 * Methode permettant d'afficher la derniere carte posee sur le talon
	 * @return void
	 */
	public void afficher() {
		System.out.println(getCarteDessusDuTalon());
	}
	
	/**
	 * Methode permettant de reinitialiser le talon en envoyant les cartes a la pioche
	 * @param  p les cartes de type Pioche que le talon retournera a la pioche 
	 * @return void
	 */
	public void reinitialiserTalon(Pioche p) {
		Carte carteDuDessus = getCarteDessusDuTalon();
		cartesTalon.remove(cartesTalon.size()-1);
		// Les cartes du talon sont envoyees a la pioche sous forme d'une type Pioche
		p.reinitialiserPioche(cartesTalon);
		// Creation d'un nouveau talon à partir de la carte du dessus determine avant d'avoir "vide" le talon
		cartesTalon = new ArrayList<Carte>();
		cartesTalon.add(carteDuDessus);
		setChanged();
		notifyObservers();
	}
}
