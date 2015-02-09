package strategie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import table.*;
import carte.Carte;
import joueur.JoueurVirtuel;

/**
 *Classe representant une strategie normale
 *
 * @author aureliengoulon
 *
 */
public class JouerNormal implements StrategieDeJeu, Serializable  {


	private static final long serialVersionUID = 6585428229187202784L;
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
		ArrayList<Carte> candidates = joueurV.getMain().cartesCandidates(carteTalon);

		if (candidates.size() == 0) {
			if (p.getCartesPioche().size() == 0 && t.getCartesTalon().size() == 1) {
				// Il n'y a plus de carte dans la pioche et une seul carte sur le talon
				System.out.println("Ce joueur ne peut pas jouer car il n'y a pas assez de carte dans la pioche.");
				joueurV.passerSonTour(p, t);
				return false;
			} else {
				// Un joueur normal cherche lui aussi a se defausser de ses cartes rapidement
				return joueurV.seDefausserQuelconque(p,t);
			}
			
		} else {
			// Choix aleatoire de la carte a jouer parmi les cartes candidates de joueurV
			// getMain() sur un Joueur renvoie une Main, un getMain() sur une Main renvoie un ArrayList<Carte>
			Random rand = new Random();
			int numeroCarteAJouer = rand.nextInt(candidates.size());
			Carte carteAJouer = candidates.get(numeroCarteAJouer);
			
			joueurV.poserEtRetirer(t,carteAJouer);
			
			joueurV.setUno(joueurV.vaDireUno());

			return true;
		}	
	}

	/**
	 * Constructeur de la strategie normale
	 */
	public JouerNormal(JoueurVirtuel joueurV) {
		super();
		this.joueurV = joueurV;
	}
}