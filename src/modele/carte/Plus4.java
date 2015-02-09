package carte;

import joueur.Joueur;
import joueur.ListeJoueur;
import partie.Manche;
import table.Pioche;
import table.Talon;

/**
 * Classe modelisant la carte +4
 * @author ikounga_marvel
 *
 */
public class Plus4 extends CarteSpeciale {

	/**
	 * Constructeur de la classe
	 */
	public Plus4() {
		super("multi", 50, "+4", "wild4.png");
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
			// La serie de test sur le joueur suivant est faite "par anticipation"
			if (joueurs.joueurSuivant(joueurActuel,sens).contester()) {
				//System.out.println(joueurActuel.getPseudonyme()+" conteste la +4 pose");
				System.out.println(joueurs.joueurSuivant(joueurActuel,sens).getPseudonyme()+" conteste la +4 pose");

				// verification(c) de la main avec l'avant-dernieere carte posee (avant la +4)
				// il faut que le nombre de carte en main soit egal au nombre de +4 
				if (!joueurActuel.getMain().verifier(t.getCartesTalon().get(t.getCartesTalon().size()-2))) {
					//System.out.println("La contestation de "+joueurActuel.getPseudonyme()+" est justifiee");
					System.out.println("La contestation de "+joueurs.joueurSuivant(joueurActuel,sens).getPseudonyme()+" est justifiee");
					joueurActuel.penaliser(pioche, 4, t);
					joueurSuivant = joueurs.joueurSuivant(joueurActuel,sens);
				} else {
					//System.out.println("La contestation de "+joueurActuel.getPseudonyme()+" est injustifiee, il pioche ah ah");
					System.out.println("La contestation de "+ joueurs.joueurSuivant(joueurActuel,sens).getPseudonyme()+" est injustifiee, il pioche ah ah");
					joueurs.joueurSuivant(joueurActuel,sens).penaliser(pioche, 6, t);
					joueurSuivant = joueurs.joueurApresSuivant(joueurActuel,sens);
				}
			} else {
				joueurs.joueurSuivant(joueurActuel,sens).penaliser(pioche, 4, t);
				joueurSuivant = joueurs.joueurApresSuivant(joueurActuel,sens);
			}
		} else {
			// Si la premiere carte du talon est une +4, on en tire une autre
			// Remove renvoi la carte retiree
			t.ajouter(pioche.getCartesPioche().remove(0));
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
			super.setImage("wild4.png");
		} else {
			super.setImage("wild4_"+couleur.charAt(0)+".png");
		}
		super.setCouleur(couleur);
		setChanged();
		notifyObservers();
	}
}