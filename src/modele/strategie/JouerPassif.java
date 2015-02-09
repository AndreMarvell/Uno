package strategie;

import java.io.Serializable;
import java.util.ArrayList;

import table.*;
import carte.*;
import joueur.JoueurVirtuel;

/**
 *Classe representant une strategie passive
 *
 * @author aureliengoulon
 *
 */
public class JouerPassif implements StrategieDeJeu, Serializable  {


	private static final long serialVersionUID = -300273119621407255L;
	private JoueurVirtuel joueurV;

	/**
	 * Methode permettant au joueur virtuel de jouer une carte
	 * @param  t Talon de la manche en cours
	 * @param  p Pioche du joueur virtuel
	 * @return boolean True si le joueur a pu pose une carte sur le talon
	 */
	@Override
	public boolean jouer(Pioche p, Talon t) {
		// TODO Auto-generated method stub

		Carte carteTalon = t.getCarteDessusDuTalon();
		ArrayList<CarteNormale> candidatesNormales = joueurV.getMain().cartesCandidatesNormales(carteTalon);
		ArrayList<CarteSpeciale> candidatesSpeciales = joueurV.getMain().cartesCandidatesSpeciales(carteTalon);

		if (candidatesNormales.size() == 0 && candidatesSpeciales.size() == 0){
			// Un joueur passif ne cherche pas a se defausser de ses cartes même s'il en pioche une
			joueurV.passerSonTour(p, t);
			return false;
		} else {
			// Choix aleatoire d'une carte normale si le joueurV en a
			// Sinon choix aleatoire d'une carte speciale
			// getMain() sur un Joueur renvoie une Main, un getMain() sur une Main renvoie un ArrayList<Carte>
			Carte carteAJouer;

			if (candidatesNormales.size() != 0) {
				carteAJouer = joueurV.donnerNormale(candidatesNormales);
			} else {	
				carteAJouer = joueurV.donnerSpeciale(candidatesSpeciales);
			}
			joueurV.poserEtRetirer(t,carteAJouer);

			joueurV.setUno(joueurV.vaDireUno());
			
			return true;
		}
	}

	/**
	 * Constructeur de la strategie passive
	 */
	public JouerPassif (JoueurVirtuel joueurV) {
		// Pour l'instant super() suffit pour instancier les strategies
		super();
		this.joueurV = joueurV;
	}
}