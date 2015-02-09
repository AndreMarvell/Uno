package joueur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import carte.Carte;
import table.Main;
import uno.JeuUno;

/**
 * Classe modelisant la liste des joueurs
 * @author ikounga_marvel
 *
 */
public class ListeJoueur extends Observable implements Serializable {
	

	private static final long serialVersionUID = 7045827251139148201L;
	private ArrayList<Joueur> liste;

	/**
	 * Constructeur de la classe
	 */
	public ListeJoueur() {
		super();
		liste = new ArrayList<Joueur>();
	}

	/**
	 * getter de la liste de joueur
	 * @return la liste
	 */
	public ArrayList<Joueur> getListe() {
		return liste;
	}

	/**
	 * setter de la liste de joueur
	 * @param liste liste a affecter
	 * @return void
	 */
	public void setListe(ArrayList<Joueur> liste) {
		this.liste = liste;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Methode permettant la distribution des cartes aux joueurs
	 * @param jeu Jeu de Carte a partir de quoi seront distribuees les cartes
	 * @return void
	 */
	public void distribuer(JeuUno jeu) {
		int i;
		
		for (i = 0; i < 7; i++) {
			for (Joueur j: liste) {
				if (j.getMain() == null) {
					Main m = new Main(new ArrayList<Carte>());
					j.setMain(m);
				}
				j.getMain().ajouter(jeu.getFirst());
			}
		}
	}

	/**
	 * Methode permettant d'afficher une liste de joueur
	 * @return void
	 */
	public void afficher() {
		System.out.println("liste de joueurs  :\n" + liste +" ");
	}
	
	/**
	 * Methode permettant de compter le nombre de joueur dans une liste
	 * @return nombre de joueur  
	 */
	public int compterJoueurs() {
		
		return this.liste.size();
	}
	
	/**
	 * Methode permettant d'ajouter un nouveau joueur a la liste
	 * @param j joueur qui sera ajouter
	 * @return void  
	 */
	public void ajouterJoueur(Joueur j) {
		if(liste==null){
			liste = new ArrayList<Joueur>();
		}
		this.liste.add(j);
	}

	/**
	 * Methode permettant de recuperer le joueur suivant 
	 * @param joueurActuel joueur actuel
	 * @param sens sens de la partie en cours
	 * @return Joueur  le joueur suivant
	 */
	public Joueur joueurSuivant(Joueur joueurActuel, boolean sens) {
		int index = this.getListe().indexOf(joueurActuel);
		
		if (sens) {
			if (index != -1) {
				if (index==this.compterJoueurs()-1) {
					return this.getListe().get(0);
				} else {
					return this.getListe().get(index+1);
				}
			} else {
				return null;
			}
		} else {
			if(index != -1){
				if(index==0){
					return this.getListe().get(this.compterJoueurs()-1);
				} else {
					return this.getListe().get(index-1);
				}
			} else {
				return null;
			}
		}
	}
	
	/**
	 * Méthode permettant de recuperer le joueur precedent 
	 * @param joueurActuel joueur actuel
	 * @param sens sens de la partie en cours
	 * @return Joueur  le joueur precedent
	 */
	public Joueur joueurPrecedent(Joueur joueurActuel, boolean sens) {
		
		int index = this.getListe().indexOf(joueurActuel);
		if (sens) {
			if (index != -1) {
				if (index==0) {
					return this.getListe().get(this.compterJoueurs()-1);
				} else {
					return this.getListe().get(index-1);
				}
				
			} else{
				return null;
			}
		} else {
			if (index != -1) {
				if (index == this.compterJoueurs()-1) {
					return this.getListe().get(0);
				} else {
					return this.getListe().get(index+1);
				}
			} else {
				return null;
			}
		}
			
	}
	
	/**
	 * Methode permettant de recuperer le joueur apres le joueur suivant 
	 * @param joueurActuel joueur actuel
	 * @param sens sens de la partie en cours
	 * @return Joueur  le joueur suivant le joueur suivant
	 */
	public Joueur joueurApresSuivant(Joueur joueurActuel, boolean sens) {
				
			return this.joueurSuivant(this.joueurSuivant(joueurActuel,sens),sens);
	}
	
	/**
	 * Methode permettant de établir les scores à la fond d'une partie
	 * @param vainqueur joueur ayant remporté la manche
	 */
	public void compterScoreVainqueur(Joueur vainqueur) {
		int score=0;
		for (Joueur j: liste) {
			if (j!=vainqueur) {
				score+=j.getMain().compterPoint();
			}
		}
		
		for (Joueur j: liste) {
			j.setMain(null);
		}
		vainqueur.setScore(vainqueur.getScore()+score);
	}
	
	/**
	 * Methode permettant de recuperer le joueur ayant le score le plus élevé
	 * @return joueur menant la partie
	 */
	public Joueur joueurMeneur() {
		Joueur meneur = null;
		
		for (Joueur j: liste) {
			if (meneur == null || j.getScore() > meneur.getScore()) {
				meneur = j;
			}
		} 
		return meneur;
	}
	
	/**
	 * Methode permettant de remettre a zero tous les scores des joueurs
	 */
	public void remiseAZero() {
		
		for (Joueur j: liste) {
			j.setScore(0);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Liste de Joueur :  "+liste.size()+" Joueurs\n"+liste;
	}
	
	
}