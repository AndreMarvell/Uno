package execution;

import consoleControleur.InteractionAvecJoueur;
import consoleControleur.PartieControleur;

import joueur.ListeJoueur;

/**
 * Classe executant le jeu en mode console
 * @author ikounga_marvel
 *
 */
public class UnoConsole {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("\t\t***************************************************");	
		System.out.println("\t\t\t\tPROJET LO02 : Jeu de Uno");
		System.out.println("\t\t Etudiants: Aurelien GOULON & IKOUNGA Andre Marvell\n");
		System.out.println("\t\t\t\tBienvenu Dans Le jeu de Uno");
		System.out.println("\t\t***************************************************\n\n");
			
		ListeJoueur joueurs = InteractionAvecJoueur.demanderJoueurs();
		 PartieControleur partie = new PartieControleur(joueurs);
		 partie.start();
		 
		 System.out.println("\n\t-----------------------Merci d'avoir utilise ce programme-------------------");
		 System.out.println("\t                         Copyright 2013 IKounga & Goulon");
	}
}