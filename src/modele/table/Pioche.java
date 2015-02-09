package table;

import java.util.ArrayList;
import java.util.Collections;

import table.Main;
import carte.Carte;

/**
 *Classe representant la pioche d'une manche
 *
 * @author aureliengoulon
 *
 */
public class Pioche {
	
	private ArrayList<Carte> cartesPioche;

	/**
	 * Constructeur de la classe
	 * @param cartesPioche les cartes constituant la pioche
	 */
	public Pioche(ArrayList<Carte> cartesPioche) {
		super();
		this.cartesPioche = cartesPioche;
	}

	/**
	 * getter des cartes dans la pioche
	 * @return les cartes dans la pioche
	 */
	public ArrayList<Carte> getCartesPioche() {
		return cartesPioche;
	}

	/**
	 * setter des cartes du talon
	 * @param cartesPioche carte a affecter a la pioche
	 * @return void
	 */
	public void setCartesPioche(ArrayList<Carte> cartesPioche) {
		this.cartesPioche = cartesPioche;
	}
	
	/**
	 * Methode permettant de piocher une carte dans la pioche
	 * @param m main du joueur qui sera allourdie  
	 * @param nbCarte nombre de carte a piocher
	 */
	public void piocher(Main m, int nbCarte, Talon t) {

			for (int i = 0; i < nbCarte; i++) {
				if (cartesPioche.size() == 0) {
					System.out.println("Reinitialisation de la pioche et du talon");
					t.reinitialiserTalon(this);
				}
				
				// Recupere la premiere carte sur le dessus de la pioche
				m.ajouter(cartesPioche.get(0));
				// A chaque fois que l'on retire la premiere carte, la deuxieme carte devient la premiere
				cartesPioche.remove(0);

			}		
	}
	
	/**
	 * Methode permettant de reinitialiser la pioche a partir des cartes recuperees au talon
	 * @param  cartesTalon les cartes du talon a rajouter a la nouvelle pioche 
	 */
	public void reinitialiserPioche(ArrayList<Carte> cartesTalon) {
		cartesPioche.addAll(cartesTalon);
		Collections.shuffle(this.cartesPioche);
	}
}