package joueur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import consoleControleur.InteractionAvecJoueur;

import carte.Carte;
import table.*;

/**
 * Classe modelisant le joueur
 * @author ikounga_marvel
 *
 */
public class Joueur extends Observable implements Serializable  {
		

	private static final long serialVersionUID = 9108064886843724137L;
	private int score = 0;
	private transient Main main;
	private String pseudonyme;
	private boolean uno = false;
	private boolean contestation = false;
	private boolean active = false;
	private String couleurChoisie = "multi";
	
	/**
	 * Constructeur de la classe
	 * @param score score du joueur
	 * @param main main du joueur
	 * @param pseudonyme pseudonyme du joueur
	 */
	public Joueur(int score, Main main, String pseudonyme) {
		super();
		this.score = score;
		this.main = main;
		this.pseudonyme = pseudonyme;
	}
	
	/**
	 * Constructeur de la classe
	 * @param score score du joueur
	 * @param main main du joueur
	 * @param pseudonyme pseudonyme du joueur
	 */
	public Joueur(String pseudonyme) {
		super();
		this.score = 0;
		this.main = null;
		this.pseudonyme = pseudonyme;
	}
	
	/**
	 * getter du score
	 * @return le score du joueur
	 */
	public int getScore() {
		return score;
	}

	/**
	 * setter du score
	 * @param score le score a affecter
	 * @return void
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * getter de la main
	 * @return la main du joueur
	 */
	public Main getMain() {
		return main;
	}

	/**
	 * setter de la main
	 * @param main main a affecter
	 * @return void
	 */
	public void setMain(Main main) {
		this.main = main;
	}

	/**
	 * getter du pseudonyme
	 * @return le pseudonyme du joueur
	 */
	public String getPseudonyme() {
		return pseudonyme;
	}

	/**
	 * setter du pseudonyme
	 * @param pseudonyme pseudonyme a affecter
	 * @return void
	 */
	public void setPseudonyme(String pseudonyme) {
		this.pseudonyme = pseudonyme;
		/* Pas vraiment besoin car cette methode n'est appele qu'une seule fois, le pseudo ne change pas*/
		setChanged();
		notifyObservers();
	}
	
	/**
	 * getter de la contestation
	 * @return la valeur de l'attribut contestation
	 */
	public boolean isContestation() {
		return contestation;
	}

	/**
	 * setter de la contestation
	 * @param contestation la contestation a affecter
	 * @return void
	 */
	public void setContestation(boolean contestation) {
		this.contestation = contestation;
	}

	/**
	 * getter de Uno
	 * @return la valeur de l'attribut
	 */
	public boolean isUno() {
		return uno;
	}

	/**
	 * setter de Uno
	 * @param uno la valeur de l'attribut a affecter
	 * @return void
	 */
	public void setUno(boolean uno) {
		if (uno==false) {
			this.uno = uno;
		} else {
			if (main.compterCarte() == 1) {
				this.uno = true;
			} else{
				System.out.println("Vous ne pouvez pas dire uno tout de suite");
			}
		}
		setChanged();
		notifyObservers();
	}	

	/**
	 * Methode permettant de passer son tour de jeu
	 * @param p pioche de la manche en cours
	 * @param t talon de la manche
	 * @return void
	 */
	public void passerSonTour(Pioche p, Talon t) {
		p.piocher(main, 1,t);// argument modifié: de piocher(joueur, nbCarte) à piocher(main, nbCarte), se reporter à Pioche
		System.out.println(pseudonyme+" a pioche 1 carte");
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Methode permettant de penaliser un jour
	 * @param  p pioche de la manche en cours
	 * @param  nbCarte nombre de carte de penalite
	 * @param t TODO
	 * @return void
	 */
	public void penaliser(Pioche p, int nbCarte, Talon t) {
		p.piocher(main, nbCarte, t);
		System.out.println(pseudonyme+" pioche "+nbCarte+" carte(s)");
	}
	
	/**
	 * Methode indiquant au joueur concerne de jouer
	 * @param  p pioche de la manche en cour
	 * @param  t Talon de la manche en cour 
	 * @return boolean determinant si le joueur actuel a pose une carte sur le talon ou non
	 */
	public boolean jouerSonTour(Pioche p, Talon t) {
		int choix = InteractionAvecJoueur.demanderAction();
				
		//On verifie si il a decider de passer ou de jouer
		if (choix == 1) {
			
			this.passerSonTour(p, t);
			return false;
			
		} else {
			 Carte carteTalon = t.getCarteDessusDuTalon();
			 ArrayList<Carte> candidates = this.main.cartesCandidates(carteTalon);
			 
			// On verifie qu'il peut bien jouer et donc qu'il a des cartes candidates
			if (candidates.size() == 0) {
				System.out.println("Vous ne pouvez pas jouer, car vous n'avez aucune carte pouvant etre jouee");
				Carte cartePossible = p.getCartesPioche().get(0);
				this.passerSonTour(p, t);
								
				if (candidates.size() == 1) {
					int autreChoix = InteractionAvecJoueur.redemanderAction();
					if (autreChoix == 2) {
						poserEtRetirer(t,cartePossible);
						System.out.println("La carte pioche par le joueur a pu etre jouee");
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				
				int indexCarteChoisie = InteractionAvecJoueur.demanderIndexCarte(this.main);
				
				while (!this.jouerCarte(t, indexCarteChoisie)) {
					indexCarteChoisie = InteractionAvecJoueur.demanderIndexCarte(this.main);
				}
				
				if (choix == 3) {
					setUno(true);
				}
				return true;				
			}	
		}
	}
	
	/**
	 * Methode permettant au joueur de jouer une carte
	 * @param  index index de la carte dans la main
	 * @param  t Talon de la manche en cours
	 * @return boulean
	 */
	public boolean jouerCarte(Talon t, int index) {
		
		Carte carteTalon = t.getCarteDessusDuTalon();
		Carte c = this.main.recupererCarte(carteTalon, index);
		
		if (c != null) {
			c.jouer(t);
			setChanged();
			notifyObservers();
			return true;
		} else {
			System.out.println("Vous ne pouvez pas jouer cette carte");
			return false;
		}
	}

	// METHODE UTILISEE PAR TOUS LES TYPES DE JOUEURS, Y COMPRIS VIRTUELS, POUR POSER UNE CARTE
	// UTILISEE PAR JOUEUR SI CARTE PIOCHEE CANDIDATE
	// UTILISEE PAR JOUEUR VIRTUEL POUR JOUER

	/**
	 * Methode permettant de poser la carte au dessus du talon et de la retirer de la main
	 * @param t le talon
	 * @param carteAPoser la carte concernée
	 * @return void
	 */
	public void poserEtRetirer(Talon t,Carte carteAPoser) {
		carteAPoser.jouer(t);
		this.getMain().getMain().remove(carteAPoser);
	}
	
	/**
	 * Methode permettant de choisir une couleur pour la carte au dessus du talon
	 * @param couleur la couleur choisie
	 * @param t talon de la manche en cours
	 * @return la couleur
	 */
	public String choisirCouleur() {
		setChanged();
		notifyObservers("choix couleur");
		return couleurChoisie;
	}
	
	/**
	 * Methode permettant de savoir si un joueur a encore des cartes dans sa main
	 * @return true ou false
	 */
	public boolean aTermineSesCartes(){
		if (main.compterCarte() == 0 ){
			return true;
		} else {
			return false;
		}
		
	}

	/**
	 * Methode permettant d'afficher un joueur
	 * @return void
	 */
	public void afficher() {
		System.out.println("[" + pseudonyme + ", " + main +" ]");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return pseudonyme + ", Score:" + this.getScore() +" points, ";
	}
	
	/**
	 * Methode pour savoir si un joueur est virtuel ou physique
	 * @return
	 */
	public boolean isVirtuel(){
		return false;
	}
	
	/**
	 * Methode permettant au joueur de contester
	 * @return
	 */
	public boolean contester() {
		setChanged();
		notifyObservers("contestation");
		return contestation;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
		setChanged();
		notifyObservers("active");
	}

	/**
	 * @return the couleurChoisie
	 */
	public String getCouleurChoisie() {
		return couleurChoisie;
	}

	/**
	 * @param couleurChoisie the couleurChoisie to set
	 */
	public void setCouleurChoisie(String couleurChoisie) {
		this.couleurChoisie = couleurChoisie;
	}
}