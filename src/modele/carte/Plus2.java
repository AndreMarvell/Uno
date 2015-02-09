package carte;

import joueur.Joueur;
import joueur.ListeJoueur;
import partie.Manche;
import table.Pioche;
import table.Talon;

/**
 * Classe modelisant la carte +2
 * @author ikounga_marvel
 *
 */
public class Plus2 extends CarteSpeciale {

	/**
	 * Constructeur de la classe
	 * @param couleur couleur de la carte
	 * @param image image de la carte
	 */
	public Plus2(String couleur, String image) {
		super(couleur, 20, "+2", image);
	}

	/* (non-Javadoc)
	 * @see carte.CarteSpeciale#agir(partie.Manche, joueur.Joueur, int)
	 */
	@Override
	public Joueur agir(Manche manche, Joueur joueurActuel, int tourDeJeu) {
		ListeJoueur joueurs = manche.getJoueurs();
		Talon t = manche.getTalon();
		Pioche pioche = manche.getPioche();
		Joueur joueurSuivant;
		boolean sens = manche.isSens();
		
		if (tourDeJeu != 1) {
			joueurs.joueurSuivant(joueurActuel,sens).penaliser(pioche, 2, t);
			joueurSuivant = joueurs.joueurApresSuivant(joueurActuel,sens);
		} else {
			joueurActuel.penaliser(pioche, 2, t);
			joueurSuivant = joueurs.joueurSuivant(joueurActuel,sens);
		}		
		return joueurSuivant;
	}
}