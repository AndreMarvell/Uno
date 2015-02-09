package guiControleur;

import joueur.ListeJoueur;
import partie.*;

/**
 * Methode modelisant le controleur de la partie
 * @author ikounga_marvel
 *
 */
public class PartieGUIControleur {
	
	private Partie partie;
	private ListeJoueur joueurs;
	private MancheGUIControleur mancheCourante;
	
	/**
	 * COnstructeur
	 * @param joueurs Liste de joueurs de la partie
	 */
	public PartieGUIControleur(ListeJoueur joueurs){
		
		this.joueurs = joueurs;
	}
	
	/**
	 * Methode permettant de lancer une nouvelle manche
	 */
	public void nouvelleManche(){
		
		mancheCourante = new MancheGUIControleur(joueurs);

	}

	/**
	 * @return the joueurs
	 */
	public ListeJoueur getJoueurs() {
		return joueurs;
	}

	/**
	 * @param joueurs the joueurs to set
	 */
	public void setJoueurs(ListeJoueur joueurs) {
		this.joueurs = joueurs;
	}

	/**
	 * @return the mancheCourante
	 */
	public MancheGUIControleur getMancheCourante() {
		return mancheCourante;
	}

	/**
	 * @param mancheCourante the mancheCourante to set
	 */
	public void setMancheCourante(MancheGUIControleur mancheCourante) {
		this.mancheCourante = mancheCourante;
	}

	/**
	 * @return the partie
	 */
	public Partie getPartie() {
		return partie;
	}

	/**
	 * @param partie the partie to set
	 */
	public void setPartie(Partie partie) {
		this.partie = partie;
	}
	
}
