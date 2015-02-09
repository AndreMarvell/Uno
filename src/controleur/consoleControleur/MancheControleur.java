package consoleControleur;

import partie.Manche;
import joueur.Joueur;
import joueur.ListeJoueur;
import table.Pioche;
import table.Talon;
import uno.JeuUno;
import carte.Carte;
import carte.CarteSpeciale;

/**
 * Controleur de la classe Manche en mode console
 * @author ikounga_marvel
 *
 */
public class MancheControleur {
	
	private Manche mc;
	private ListeJoueur joueurs;
	
	
	/**
	 * Constructeur de la classe 
	 * @param joueurs Liste de Joueurs de la partie
	 */
	public MancheControleur(ListeJoueur joueurs) {
		super();
		this.mc = new Manche(joueurs);
		this.joueurs = joueurs;
		// TODO Auto-generated constructor stub
	}


	/**
	 * Methode permettant de demarrer une manche
	 * @return void
	 */
	public void start() {
		
		mc.setDealer(joueurs.getListe().get(0));
		Joueur joueurActuel = mc.getDealer();
		JeuUno jeu=JeuUno.getInstance();
		jeu.battre(joueurs);
		// On initialise le talon et la pioche
		mc.setTalon(new Talon(jeu.getFirst()));
		mc.setPioche(new Pioche(jeu.getJeu()));
		Carte carteTalon;
		CarteSpeciale speciale;
		boolean joueurActuelAJoue=true;
		int tourDeJeu=1;
		System.out.println("Le dealer est "+mc.getDealer().getPseudonyme()+" c'est a lui de jouer");
		
		while (!mc.isFinPartie()) {
			carteTalon = mc.getTalon().getCarteDessusDuTalon();
			System.out.println("Carte du talon"+carteTalon);
			
			// On realise d'abord le traitement de l'effet de la carte du talon avant de passer au tour suivant
			if(carteTalon.isSpecial() && joueurActuelAJoue){
					// On cast la carte talon pour pouvoir utiliser la methode agir qui n'est pas une methode de carte
					speciale = (CarteSpeciale) carteTalon;
					//On recupere le joueur suivant
					joueurActuel = speciale.agir(mc, joueurActuel, tourDeJeu);
			} else {
				if (tourDeJeu != 1) {
					//On recupere le joueur suivant
					joueurActuel = joueurs.joueurSuivant(joueurActuel, mc.isSens());
				}
			}
			
			if (tourDeJeu != 1) {
				System.out.println("Tour Suivant : c'est à "+joueurActuel.getPseudonyme()+" de jouer");
			}
			
			// Apres que le joueur suivant ait ete recupere, il devient le joueur actuel
			// On recupere ici le boolean specifiant si il a joue une carte sur le talon, 
			// car si il n'a pas joue et que la carte du talon est speciale il faut veiller a ne pas affecter le joueur suivant une nouvelle fois
			joueurActuel.getMain().afficher();
			// Affichage du statut du joueur
			joueurActuelAJoue = joueurActuel.jouerSonTour(mc.getPioche(), mc.getTalon());
			if (joueurActuelAJoue) {
				System.out.println(joueurActuel.getPseudonyme() + " a joue "+ mc.getTalon().getCarteDessusDuTalon());
			} else {
				System.out.println(joueurActuel.getPseudonyme() + " n'a pas pu joue");
			}
			
			// On verifie qu'il a dit Uno si il lui reste une carte dans sa main
			if(joueurActuel.getMain().compterCarte() == 1){
				if(!joueurActuel.isUno()){
					System.out.println("Vous etes gentil mais vous gagnerez le mois prochain, N'oublie pas de dire Uno! cette fois la");
					joueurActuel.penaliser(mc.getPioche(), 1,mc.getTalon());
					
				}
			} else {
				joueurActuel.setUno(false);
			}
			// On verifie si il y a un vainqueur
			if (joueurActuel.aTermineSesCartes()) {
				mc.setVainqueur(joueurActuel);
				mc.setFinPartie(true);
				joueurs.compterScoreVainqueur(joueurActuel);
				System.out.println("\n\t******************************************************************************");
				System.out.println("\t\tManche terminee, Le vainqueur est "+mc.getVainqueur());
				System.out.println("\t******************************************************************************");
			}
			
			tourDeJeu++;
			System.out.println("\n");
		}		
	}

}
