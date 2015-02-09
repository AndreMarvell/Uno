package carte;

import joueur.Joueur;
import joueur.ListeJoueur;
import partie.Manche;

/**
 * Classe modelisant la carte Joker
 * @author ikounga_marvel
 *
 */
public class Joker extends CarteSpeciale {

	/**
	 * Constructeur de la classe
	 */
	public Joker() {
		super("multi", 50, "joker", "wild.png");
		
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
			joueurSuivant = joueurs.joueurSuivant(joueurActuel,sens);
		} else {
			joueurSuivant = joueurActuel;
		}
		
		String couleurVoulue = joueurActuel.choisirCouleur();
		System.out.println(joueurActuel.getPseudonyme()+" a choisi de changer la couleur de la carte au "+couleurVoulue);
		this.setCouleur(couleurVoulue);
		return joueurSuivant;
	}

	/* (non-Javadoc)
	 * @see carte.CarteSpeciale#verifier(carte.Carte)
	 */
	@Override
	public boolean verifier(Carte c) {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see carte.Carte#setCouleur(java.lang.String)
	 */
	@Override
	public void setCouleur(String couleur) {
		if (couleur.equals("multi")) {
			super.setImage("wild.png");
		} else {
			super.setImage("wild_"+couleur.charAt(0)+".png");
		}
		super.setCouleur(couleur);
		setChanged();
		notifyObservers();
	}
}