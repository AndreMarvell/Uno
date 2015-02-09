package consoleControleur;

import java.util.Scanner;

import joueur.Joueur;
import joueur.JoueurVirtuel;
import joueur.ListeJoueur;

import strategie.JouerAgressif;
import strategie.JouerAgressifAvecBluff;
import strategie.JouerNormal;
import strategie.JouerPassif;
import table.Main;

/**
 * Classe permettant de gerer les interactions avec l'utilisateur
 * @author ikounga_marvel
 *
 */
public class InteractionAvecJoueur {
	
	public static Scanner clavier = new Scanner(System.in);
	
	/**
	 * Methode permettant de demander l'action a realiser au joueur
	 * @return l'action a realiser
	 */
	public static int demanderAction() {
		System.out.println("Que voulez vous faire?");
		System.out.println("\t1-Passer votre tour");
		System.out.println("\t2-Jouer");
		System.out.println("\t3-Dire Uno apres ce tour de jeu\n");
		
		int choix=clavier.nextInt();

		while(choix<1 || choix>3) {
			System.out.println("Le choix est compris entre 1 et 3");
			System.out.println("Retapez votre choix");
			choix = clavier.nextInt();
		}
		return choix;
	}
	
	/**
	 * Methode permettant de demander l'action a realiser au joueur sans cartes candidates
	 * lorsqu'après avoir pioche une carte, celle-ci est un cartecandidate il peut la jouer
	 * @return l'action a realiser
	 */
	public static int redemanderAction() {
		System.out.println("La carte piochee est jouable. Que voulez vous faire?");
		System.out.println("\t1-La laisser dans la main");
		System.out.println("\t2-Jouer");

		int choix=clavier.nextInt();
		
		while (choix < 1 || choix > 2) {
			System.out.println("Le choix est compris entre 1 et 2");
			System.out.println("Retapez votre choix");
			choix = clavier.nextInt();
		}
		return choix;
	}

	/**
	 * Methode permettant de demander l'action a realiser au joueur
	 * @param m main du joueur
	 * @return l'index de la carte dans la main
	 */
	public static int demanderIndexCarte(Main m) {
		
		System.out.println("Quelle carte voulez vous jouer? (Donnez l'index)");
		
		int choix=clavier.nextInt();
		
		while (choix < 1 || choix > m.compterCarte()) {
			System.out.println("la carte d'index "+choix+" n'existe pas.");
			System.out.print(" Retapez votre choix");
			m.afficher();
			choix = clavier.nextInt();
		}
		return choix-1;
	}
	
	/**
	 * Methode permettant de demander une couleur au joueur
	 * @return la couleur choisie
	 */
	public static String demanderCouleur() {
		
		System.out.println("Voulez vous changer de couleur?");
		System.out.println("\t1-Oui");
		System.out.println("\t2-Non\n");
		
		int choix=clavier.nextInt();
		
		while (choix != 1 && choix != 2) {
			System.out.println("Le choix est compris entre 1 et 2");
			System.out.println("Retapez votre choix");
			choix = clavier.nextInt();
		}
		
		if (choix == 2) {
			return "multi";
		} else {
			System.out.println("Quelle couleur choisissez vous?");
			System.out.println("\t1-Vert");
			System.out.println("\t2-Jaune");
			System.out.println("\t3-Rouge");
			System.out.println("\t4-Bleu\n");
			
			int choixCouleur=clavier.nextInt();
			String couleur= new String();
			
			while (choixCouleur < 1 || choixCouleur > 4) {
				System.out.println("Le choix est compris entre 1 et 4");
				System.out.println("Retapez votre choix");
				choix = clavier.nextInt();
			}
			switch (choixCouleur)
			{
			  case 1:
				  couleur= "vert";
				  break;
			  case 2:
				  couleur= "jaune";
				  break;
			  case 3:
				  couleur= "rouge";
				  break;
			  case 4:
				  couleur= "bleu";
				  break;
			  default:
			    System.out.println("Erreur");
			}
			return couleur;
		}
	}
	
	/**
	 * Methode permettant au joueur de contester ou non
	 * @return boolean
	 */
	public static boolean contester() {
		System.out.println("Voulez vous contester la +4 posee?");
		System.out.println("\t1-Oui");
		System.out.println("\t2-Non\n");
		
		int choix=clavier.nextInt();
		
		while (choix != 1 && choix != 2) {
			System.out.println("Le choix est compris entre 1 et 2");
			System.out.println("Retapez votre choix");
			choix = clavier.nextInt();
		}
		
		if (choix == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Methode permettant au joueur de contester ou non
	 * @return boolean
	 */
	public static ListeJoueur demanderJoueurs() {
		
		ListeJoueur joueurs = new ListeJoueur();
		
		System.out.print("Combien de joueurs reels voulez vous pour cette partie? ");
		int jReel=clavier.nextInt();		
		
		while (jReel < 0 || jReel > 10) {
			System.out.println("Le maximum est de 10 joueurs");
			System.out.println("Retapez votre choix");
			jReel = clavier.nextInt();
		}
		
		System.out.print("Combien de joueurs virtuels voulez vous pour cette partie? ");
		int jVirtuel=clavier.nextInt();		
		
		while (jVirtuel < 0 || jVirtuel > (10-jReel) || (jVirtuel+jReel) < 2) {
			System.out.println("Le maximum est de "+(10-jReel)+" joueurs virtuels pour cette partie");
			System.out.println("Retapez votre choix");
			jVirtuel = clavier.nextInt();
		}
		
		if (jReel > 0) {
			System.out.println("Donnez Les noms de vos joueurs Reels");
		}
		for (int i = 1; i <= jReel; i++) {
			System.out.println("Joueurs Reel N¡"+i);
			String pseudo = clavier.next();
			if (pseudo == "") {
				joueurs.ajouterJoueur(new Joueur("Joueur"+i));
			} else {
				joueurs.ajouterJoueur(new Joueur(pseudo));
			}
			
		}
		
		if (jVirtuel>0) {
			System.out.println("Donnez Les strategies des joueurs virtuels");
		}
		for (int i = 1; i <= jVirtuel; i++) {
			JoueurVirtuel ordinateur = new JoueurVirtuel("Ordinateur"+i);
			System.out.println("Quelle strategie choisissez vous pour le joueur virtuel N¡"+i+" ?");
			System.out.println("\t1-Agressif");
			System.out.println("\t2-Agressif avec Bluff");
			System.out.println("\t3-Normal");
			System.out.println("\t4-Passif");
			
			int choixStrategie=clavier.nextInt();
			
			while (choixStrategie < 1 || choixStrategie > 4) {
				System.out.println("Le choix est compris entre 1 et 4");
				System.out.println("Retapez votre choix");
				choixStrategie = clavier.nextInt();
			}
			
			switch (choixStrategie)
			{
			  case 1:
				  ordinateur.setStrategie(new JouerAgressif(ordinateur));
				  break;
			  case 2:
				  ordinateur.setStrategie(new JouerAgressifAvecBluff(ordinateur));
				  break;
			  case 3:
				  ordinateur.setStrategie(new JouerNormal(ordinateur));
				  break;
			  case 4:
				  ordinateur.setStrategie(new JouerPassif(ordinateur));
				  break;
			  default:
			    System.out.println("Erreur");
			}
			joueurs.ajouterJoueur(ordinateur);	
		}
		return joueurs;	
	}
}
