package strategie;

import java.io.Serializable;
import java.util.ArrayList;

import table.*;
import carte.*;
import joueur.JoueurVirtuel;

/**
 *Classe representant une strategie agressive avec bluff
 *
 * @author aureliengoulon
 *
 */
public class JouerAgressifAvecBluff implements StrategieDeJeu, Serializable  {

	private static final long serialVersionUID = 153945496990421449L;
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
			// Choix du +4 s'il y en a un
			// Sinon choix aleatoire d'une autre carte speciale si le joueurV en a
			// Ou alors choix aleatoire d'une carte normale
			// getMain() sur un Joueur renvoie une Main, un getMain() sur une Main renvoie un ArrayList<Carte>
			Carte carteAJouer = null;

			if (candidatesSpeciales.size() != 0) {
				boolean vaJouer = false;
				while (vaJouer==false) {
					for (CarteSpeciale carteRecherchee : candidatesSpeciales) {
						// Utilisation d'une methode definie par Oracle pour l'egalite
						if(carteRecherchee.getSpecialite().equals("+4")) {
							carteAJouer = carteRecherchee;
							vaJouer=true;
						}
					}
					if (vaJouer==false) {
						carteAJouer = joueurV.donnerSpeciale(candidatesSpeciales);
						vaJouer=true;
					}
				}
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
	 * Constructeur de la strategie agressive avec bluff
	 */
	public JouerAgressifAvecBluff (JoueurVirtuel joueurV) {
		// Pour l'instant super() suffit pour instancier les strategies
		super();
		this.joueurV = joueurV;
	}
}