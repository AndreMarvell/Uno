package strategie;

import java.io.Serializable;
import java.util.ArrayList;

import table.*;
import carte.*;
import joueur.JoueurVirtuel;

/**
 *Classe representant une strategie agressive
 *
 * @author aureliengoulon
 *
 */
public class JouerAgressif implements StrategieDeJeu, Serializable  {

	private static final long serialVersionUID = 2615927184018382474L;
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

		if (candidatesNormales.size() == 0 && candidatesSpeciales.size() == 0) {
			if (p.getCartesPioche().size() == 0) {
				joueurV.passerSonTour(p, t);
				return false;
			} else {
				// Un joueur agressif cherche a se defausser de ses cartes rapidement
				return joueurV.seDefausserQuelconque(p,t);
			}
			
		} else {
			// Choix aleatoire d'une carte speciale si le joueurV en a
			// Sinon choix aleatoire d'une carte normale
			// getMain() sur un Joueur renvoie une Main, un getMain() sur une Main renvoie un ArrayList<Carte>
			Carte carteAJouer;

			if(candidatesSpeciales.size()!=0){
				carteAJouer = joueurV.donnerSpeciale(candidatesSpeciales);
			} else {	
				carteAJouer = joueurV.donnerNormale(candidatesNormales);
			}
			joueurV.poserEtRetirer(t,carteAJouer);
		
			if (joueurV.getMain().compterCarte() == 1) {
				joueurV.setUno(true);
			}
			return true;
		}
	}

	/**
	 * Constructeur de la strategie agressive
	 */
	public JouerAgressif (JoueurVirtuel joueurV) {
		// Pour l'instant super() suffit pour instancier les strategies
		super();
		this.joueurV = joueurV;
	}
}