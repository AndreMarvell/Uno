package carte;

import joueur.Joueur;
import joueur.ListeJoueur;
import partie.Manche;

/**
 * Classe modelisant la carte Passe
 * @author ikounga_marvel
 *
 */
public class Passe extends CarteSpeciale {

	/**
	 * Constructeur de la classe
	 * @param couleur couleur de la carte
	 * @param image image de la carte
	 */
	public Passe(String couleur, String image) {
		super(couleur, 20, "passe", image);
	}

	/* (non-Javadoc)
	 * @see carte.CarteSpeciale#agir(partie.Manche, joueur.Joueur, int)
	 */
	@Override
	public Joueur agir(Manche manche, Joueur joueurActuel, int tourDeJeu) {
		ListeJoueur joueurs = manche.getJoueurs();
		Joueur joueurSuivant;
		boolean sens = manche.isSens();
		
		if (tourDeJeu != 1) {
			joueurSuivant = joueurs.joueurApresSuivant(joueurActuel,sens);
			System.out.println("Carte Passe sur le talon c'est a  "+joueurSuivant.getPseudonyme()+" de jouer");
		} else {
			joueurSuivant = joueurs.joueurSuivant(joueurActuel,sens);
			System.out.println("Carte Passe, le dealer est saute, c'est a  "+joueurSuivant.getPseudonyme()+" de jouer");
		}
		return joueurSuivant;
	}
}