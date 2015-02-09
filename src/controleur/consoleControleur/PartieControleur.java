package consoleControleur;

import joueur.ListeJoueur;

/**
 * Classe modelisant le controleur de la partie en console,
 * Cette classe n'applique pas vraiment l'ensemble des principes MVC, 
 * mais permet juste la separation de l'execution en console du modele
 * @author ikounga_marvel
 *
 */
public class PartieControleur {

	private ListeJoueur joueurs;
	
	
	
	/**
	 * Constructeur du controleur
	 * @param joueurs Liste de joueurs
	 */
	public PartieControleur(ListeJoueur joueurs) {
		super();
		this.joueurs = joueurs;
	}



	/**
	 * Methode permettant de demarrer une nouvelle partie
	 */
	public void start(){
		
		int nbManche =0;
		while(joueurs.joueurMeneur().getScore()<500){
			nbManche++;
			System.out.println("\n\t******************************************************************************");
			System.out.println("\t\t\t\tManche N¡"+nbManche+". Are you Ready? ");
			System.out.println("\t******************************************************************************");
			MancheControleur manche = new MancheControleur(joueurs);
			manche.start();
			
		}
		
		System.out.println("\n\t******************************************************************************");
		System.out.println("\tPartie terminee, Le vainqueur est "+joueurs.joueurMeneur() +" en "+nbManche+" manches");
		System.out.println("\t******************************************************************************");
			
	}
}
