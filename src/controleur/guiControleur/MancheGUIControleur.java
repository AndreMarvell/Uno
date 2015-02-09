package guiControleur;

import partie.Manche;
import joueur.Joueur;
import joueur.ListeJoueur;
import table.Pioche;
import table.Talon;
import uno.JeuUno;
import carte.CarteSpeciale;

/**
 * Methode modelisant le controleur de la manche
 * @author ikounga_marvel
 *
 */
public class MancheGUIControleur {
	
	Manche manche;
	Joueur joueurActuel;


	/**
	 * Constructeur 
	 * @param j Liste de Joueur de la partie
	 */
	public MancheGUIControleur(ListeJoueur j){
		
		Joueur dealer = j.getListe().get(0);
		manche = new Manche(j);
		manche.setDealer(dealer);
		
		
		JeuUno jeu=JeuUno.getInstance();
		jeu.battre(manche.getJoueurs());
		
		// On initialise le talon et la pioche
		manche.setTalon(new Talon(jeu.getFirst()));
		manche.setPioche(new Pioche(jeu.getJeu()));
		
		joueurActuel = dealer;
				
	}
	
	/**
	 * Methode permettant de lancer le tour de jeu suivant et donc de lancer les effets de la carte jouee sur le talon
	 * @param aJoue Bouleen determinant si le joueur precedent a jouer ou passer son tour
	 * @param tourDeJeu numero du tout de jeu
	 */
	public void tourSuivant(boolean aJoue, int tourDeJeu){
		
			joueurActuel.setActive(false);
		// On verifie que le joueur ayant joue a dit Uno si il lui reste une carte dans sa main
		if(joueurActuel.getMain().compterCarte()==1){
			if(!joueurActuel.isUno()){
				System.out.println(joueurActuel.getPseudonyme()+" a oublie de dire Uno! Il ne sortira pas au prochain tour");
				joueurActuel.penaliser(manche.getPioche(), 1, manche.getTalon());
			}
		}
		else{
			joueurActuel.setUno(false);
		}
		
		if(!joueurActuel.aTermineSesCartes()){						
			if(aJoue && manche.getTalon().getCarteDessusDuTalon().isSpecial()){
				CarteSpeciale c = (CarteSpeciale) manche.getTalon().getCarteDessusDuTalon();
				joueurActuel = c.agir(manche, joueurActuel, tourDeJeu);
			} else {
				
				joueurActuel = manche.getJoueurs().joueurSuivant(joueurActuel, manche.isSens());
			}			
			
			joueurActuel.setActive(true);
			
		}else{
			manche.setVainqueur(joueurActuel);
			
		}
						
	}
	
	/* Setter & Getters de la Classe*/
	
	/**
	 * @return the manche
	 */
	public Manche getManche() {
		return manche;
	}

	/**
	 * @param manche the manche to set
	 */
	public void setManche(Manche manche) {
		this.manche = manche;
	}

	/**
	 * @return the joueurActuel
	 */
	public Joueur getJoueurActuel() {
		return joueurActuel;
	}

	/**
	 * @param joueurActuel the joueurActuel to set
	 */
	public void setJoueurActuel(Joueur joueurActuel) {
		this.joueurActuel = joueurActuel;
	}

	
}
