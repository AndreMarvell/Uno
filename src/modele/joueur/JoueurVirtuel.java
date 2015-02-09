package joueur;

import java.util.ArrayList;
import java.util.Random;

import strategie.StrategieDeJeu;
import table.*;
import carte.*;

/**
 * Classe modelisant le Joueur Virtuel
 * @author ikounga_marvel
 *
 */
public class JoueurVirtuel extends Joueur {
	

	private static final long serialVersionUID = 5808994865311198076L;
	private StrategieDeJeu strategie;


	/**
	 * Constructeur de la classe
	 * @param score score du joueur
	 * @param main main du joueur
	 * @param pseudonyme pseudonyme du joueur
	 * @param strategie strategie de jeu du joueur
	 */
	public JoueurVirtuel(int score, Main main, String pseudonyme, StrategieDeJeu strategie) {
		super(score, main, pseudonyme);
		this.strategie = strategie;
	}
	
	/**
	 * Constructeur de la classe
	 * @param pseudonyme pseudonyme du joueur
	 */
	public JoueurVirtuel(String pseudonyme) {
		super(pseudonyme);
	}
	
	/**
	 * Constructeur de la classe
	 * @param score score du joueur
	 * @param main main du joueur
	 * @param pseudonyme pseudonyme du joueur
	 */
	public JoueurVirtuel(int score, Main main, String pseudonyme) {
		super(score, main, pseudonyme);
	}
	
	/**
	 * Methode pour savoir si un joueur est virtuel ou physique
	 * @return
	 */
	@Override
	public boolean isVirtuel() {
		return true;
	}

	/**
	 * getter de la strategie
	 * @return la strategie de jeu
	 */
	public StrategieDeJeu getStrategie() {
		return strategie;
	}

	/**
	 * setter de la strategie
	 * @param strategie strategie a affecter
	 * @return void
	 */
	public void setStrategie(StrategieDeJeu strategie) {
		this.strategie = strategie;
	}
	
	/**
	 * Methode permettant d'afficher un joueur
	 * @return void
	 */
	public void afficher() {
		System.out.println("[" + this.getPseudonyme() + ", Score:" + this.getScore() +" points, "+strategie+" ]");
	}
	
	/* (non-Javadoc)
	 * @see joueur.Joueur#jouerSonTour(table.Pioche, table.Talon)
	 */
	public boolean jouerSonTour(Pioche p, Talon t){
		return strategie.jouer(p, t);
	}
	
	/**
	 * Methode pour savoir si un joueur est virtuel ou physique
	 * @param candidatesSpeciales les cartes candidates avec celle sur le dessus du talon
	 * @return carteAJouer la carte speciale qui sera jouee
	 */
	public CarteSpeciale donnerSpeciale(ArrayList<CarteSpeciale> candidatesSpeciales) {
		Random rand = new Random();
		
		if (candidatesSpeciales.size() != 0) {
				int numeroCarteAJouer = rand.nextInt(candidatesSpeciales.size());
				CarteSpeciale carteAJouer = candidatesSpeciales.get(numeroCarteAJouer);
				return carteAJouer;
		} else {
			return null;
		}
	}

	/**
	 * Methode pour savoir si un joueur est virtuel ou physique
	 * @param candidatesNormales les cartes candidates avec celle sur le dessus du talon
	 * @return carteAJouer la carte normale qui sera jouee
	 */
	public CarteNormale donnerNormale(ArrayList<CarteNormale> candidatesNormales) {
		Random rand = new Random();
		
		if (candidatesNormales.size() != 0 ) {
			int numeroCarteAJouer = rand.nextInt(candidatesNormales.size());
			CarteNormale carteAJouer = candidatesNormales.get(numeroCarteAJouer);
			return carteAJouer;
		} else {
			return null;
		} 
	}

	/**
	 * Methode pour savoir si un joueur passe son tour ou non
	 * @param p la pioche
	 * @param t le talon
	 * @return boolean True si le joueur virtuel a joue
	 */
	public boolean seDefausserQuelconque(Pioche p, Talon t) {
		// Recuperation de la carte eventuellement jouable avant de piocher

		Carte cartePossible = p.getCartesPioche().get(0);
		this.passerSonTour(p, t);
		
		Carte carteTalon = t.getCarteDessusDuTalon();
		ArrayList<Carte> candidates = this.getMain().cartesCandidates(carteTalon);

		if (candidates.size() == 1) {
			// La seule carte jouable est celle que le joueur vient de piocher, alors il la joue
			poserEtRetirer(t,cartePossible);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Methode pour savoir si un joueur va choisir la couleur de la carte posee
	 * @param couleur la couleur
	 * @return String la couleur choisie par le joueur virtuel
	 */
	public String choisirCouleur() {
		Random rand = new Random();
		int vaChoisirCouleur = rand.nextInt(2);

		if (vaChoisirCouleur != 0) {
			return this.getMain().couleurDominante();
		} else {
			return "multi";
		}
	}

	/**
	 * Methode pour savoir si un joueur va contester l'action du joueur precedant
	 * @param couleur la couleur
	 * @return boolean True si le joueur virtuel va contester
	 */
	@Override
	public boolean contester() {
		Random rand = new Random();
		int vaContester = rand.nextInt(3);

		if (vaContester != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Methode pour savoir si un joueur va dire Uno apres son tour
	 * @param void
	 * @return boolean True si le joueur virtuel va dire Uno
	 */
	public boolean vaDireUno() {
		
		Random rand = new Random();
		int vaDireUno = rand.nextInt(3);
			if (this.getMain().compterCarte() == 1) {
				if (vaDireUno==0) {
				this.setUno(false);
				} else {
					this.setUno(true);
				}
				return true;
			} else {
				return false;
			}
	}
}