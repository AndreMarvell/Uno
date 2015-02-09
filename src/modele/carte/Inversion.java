package carte;

import joueur.Joueur;
import joueur.ListeJoueur;
import partie.Manche;

/**
 * Classe modelisant la carte inversion
 * @author ikounga_marvel
 *
 */
public class Inversion extends CarteSpeciale {

	/**
	 * Constructeur de la classe
	 * @param couleur couleur de la carte
	 * @param image image de la carte
	 */
	public Inversion(String couleur, String image) {
		super(couleur, 20, "inversion", image);
		
	}

	/* (non-Javadoc)
	 * @see carte.CarteSpeciale#agir(partie.Manche, joueur.Joueur, int)
	 */
	@Override
	public Joueur agir(Manche manche, Joueur joueurActuel, int tourDeJeu) {
		ListeJoueur joueurs = manche.getJoueurs();
		Joueur joueurSuivant;
		boolean sens = manche.isSens();
		
		if (joueurs.compterJoueurs() == 2) {
			System.out.println("Mode 2 joueurs c'est toujours a "+joueurActuel.getPseudonyme()+" de jouer");
			joueurSuivant = joueurActuel;
		} else {
			joueurSuivant = joueurs.joueurPrecedent(joueurActuel,sens);
			System.out.println("Changement du sens de la manche. Cest a "+joueurSuivant.getPseudonyme());
		}
		manche.changerSens();
		
		return joueurSuivant;
	}
}