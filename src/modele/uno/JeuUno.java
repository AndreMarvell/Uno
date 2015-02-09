/**
 * 
 */
package uno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

import joueur.ListeJoueur;

import carte.*;


/**
 * Classe modelisant le paquet de carte
 * @author ikounga_marvel
 *
 */
public class JeuUno extends Observable {
	
	private static JeuUno instance = null;
	private ArrayList<Carte> jeu = null;

	// Autant implementer la methode initialiser (avec un commentaire) dans le constructeur plutôt que d'y faire appel
	/**
	 * Constructeur de la classe 
	 */
	private JeuUno() {
		initialiser();
	}
	
	/**
	 * Methode getInstance pour creer le jeu
	 * @return le jeu de Uno
	 */
	public static JeuUno getInstance(){
		if (instance==null) {
			instance = new JeuUno();
		} else {
			instance.initialiser();
		}
		return instance;
	}

	/**
	 * getter du jeu
	 * @return le jeu de carte
	 */
	public ArrayList<Carte> getJeu() {
		return jeu;
	}

	/**
	 * setter du jeu
	 * @param jeu jeu a affecter
	 */
	public void setJeu(ArrayList<Carte> jeu) {
		this.jeu = jeu;
	}
	
	/**
	 * Methode permettant le melange des cartes
	 * @param joueurs liste de joueurs de la partie
	 * @return void
	 */
	public void battre(ListeJoueur joueurs) {
		
		// On bats les cartes
		Collections.shuffle(this.jeu);
		
		// On appelle la methode distribuer pour lancer la distribution des cartes aux joueurs
		joueurs.distribuer(this);
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Methode permettant d'afficher le paquet de carte
	 * @return void
	 */
	public void afficher() {
		System.out.println(" jeu=[" + jeu + "]");
	}
	
	/**
	 * Methode permettant de compter le nombre de carte dans le paquet de carte
	 * @return nombre de carte  
	 */
	public int compterCarte() {
		
		return this.jeu.size();
	}
	
	/**
	 * Methode permettant d'obtenir la carte au dessus du jeu en la supprimant du lot
	 * @return la carte au dessus
	 */
	public Carte getFirst() {
		Carte c = jeu.get(0);
		jeu.remove(0);
		
		return c;
	}
	
	/**
	 * Methode permettant de vider le paquet de carte
	 * 
	 */
	public void vider() {
		jeu = new ArrayList<Carte>();
	}
	
	/**
	 * Methode permettant d'instancier/d'initialiser le paquet de carte
	 */
	public void initialiser() {
		String couleur[] = {"bleu", "vert", "jaune", "rouge"};
		
		// On vide le paquet
		this.vider();
		
		for(String col : couleur){
			jeu.add(new CarteNormale(col,0,0,col.charAt(0)+"0.png"));
		}
		
		for (int i = 0; i < 2; i++) {
			for (int num = 1; num < 10; num++) {
				for (String col : couleur) {
					jeu.add(new CarteNormale(col,num,num,col.charAt(0)+""+num+".png"));
				}
			}
			
		for (String col : couleur) {
			jeu.add(new Plus2(col,col.charAt(0)+"draw2.png"));
			jeu.add(new Inversion(col, col.charAt(0)+"reverse.png"));
			jeu.add(new Passe(col,col.charAt(0)+"skip.png"));
		}
			jeu.add(new Joker());
			jeu.add(new Joker());
			jeu.add(new Plus4());
			jeu.add(new Plus4());
		}		
	}
}