package table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import carte.*;

/**
 * Classe modelisant la main
 * @author ikounga_marvel
 *
 */
public class Main {

	private ArrayList<Carte> main;
	
	/**
	 * Constructeur de la classe
	 * @param main 
	 */
	public Main(ArrayList<Carte> main) {
		super();
		this.main = main;
	}

	/**
	 * getter de la main
	 * @return la liste de cartes dans la main du joueur
	 */
	public ArrayList<Carte> getMain() {
		return main;
	}
	
	/**
	 * setter de la main
	 * @param main main du joueur
	 * @return void
	 */
	public void setMain(ArrayList<Carte> main) {
		this.main = main;
	}
	
	/**
	 * Methode permettant l'ajout d'une carte dans une main
	 * @param  c carte qui sera ajoute dans la main 
	 * @return void
	 */
	public void ajouter(Carte c) {
		this.main.add(c);
	}
	
	/**
	 * Methode permettant l'ajout d'une liste de carte dans une main
	 * @param  cartes liste de carte qui sera ajoute dans la main
	 * @return void 
	 */
	public void ajouter(ArrayList<Carte> cartes) {
		this.main.addAll(cartes);
	}
	
	/**
	 * Methode permettant de compter le nombre de carte dans une main
	 * @return le nombre de carte dans la main
	 */
	public int compterCarte() {
		
		return this.main.size();
	}
	
	/**
	 * Methode permettant de compter le nombre d'occurence d'une carte speciale dans une main
	 * @param spec specialite de la carte concerne
	 * @return nombre d'occurence  
	 */
	public int compterOccurence(String spec) {
		int i = 0;
		
		for (Carte carteMain: main) {
			if (carteMain.getTypeCarte() == spec) {
				i++;
			}
		}
		return i;
	}
	
	/**
	 * Methode permettant de verifier l'absence de choix dans la main par rapport a la carte du talon
	 * Dans le cas des contestations de Carte +4
	 * @param  c carte au dessus du talon 
	 * @return boolean 
	 */
	public boolean verifier(Carte c) {
		ArrayList<Carte> candidates = this.cartesCandidates(c);
		
		if(candidates.size()==this.compterOccurence("+4")){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Methode permettant de retourner les cartes de la main compatible avec la carte du talon 
	 * et donc qu'un joueur peut jouer
	 * @param  c carte au dessus du talon 
	 * @return liste de cartes compatible
	 */
	public ArrayList<Carte> cartesCandidates(Carte c) {
		ArrayList<Carte> candidates = new ArrayList<Carte>();
		
		for(Carte carteMain: main){
			// verifier(c) de la carte
			if(carteMain.verifier(c)==true){
				candidates.add(carteMain);
			}
		}
		return candidates;
	}
	
	/**
	 * Methode permettant de retourner les cartes speciales de la main compatible avec la carte du talon 
	 * et donc qu'un joueur peut jouer
	 * @param  c carte au dessus du talon 
	 * @return liste de cartes compatibles
	 */
	public ArrayList<CarteSpeciale> cartesCandidatesSpeciales(Carte c) {
		ArrayList<CarteSpeciale> candidatesSpeciales = new ArrayList<CarteSpeciale>();
		
		for(Carte candidate: this.cartesCandidates(c)){
			if(candidate.isSpecial()){
				candidatesSpeciales.add((CarteSpeciale) candidate);
			}
		}
		return candidatesSpeciales;
	}
	
	/**
	 * Methode permettant de retourner les cartes normales de la main compatible avec la carte du talon 
	 * et donc qu'un joueur peut jouer
	 * @param  c carte au dessus du talon 
	 * @return liste de cartes compatibles
	 */
	public ArrayList<CarteNormale> cartesCandidatesNormales(Carte c) {
		ArrayList<CarteNormale> candidatesNormales = new ArrayList<CarteNormale>();
		
		for(Carte candidate: this.cartesCandidates(c)){
			if(!candidate.isSpecial()){
				candidatesNormales.add((CarteNormale) candidate);
			}
		}
		return candidatesNormales;	
	}

	
	/**
	 * Methode permettant d'afficher la main d'un joueur
	 * @return void
	 */
	public void afficher() {
		System.out.print("main = [");
		for(int i=0;i<main.size();i++){
			System.out.print("  #"+(i+1)+"" + main.get(i) + ", ");
		}
		System.out.print("]\n");
	}
	
	/**
	 * Methode permettant d'afficher les cartes candidates dans la main d'un joueur
	 * @param c Carte au dessus du talon
	 * @return void
	 */
	public void afficherCartesCandidates(Carte c) {
		ArrayList<Carte> candidates = this.cartesCandidates(c);
		
		System.out.println("Cartes Candidates=" + candidates);
	}
	
	/**
	 * Methode permettant de recuperer une carte dans la main et la supprimer
	 * @param ct Carte au dessus du talon
	 * @param index index de la carte dans la main
	 * @return la carte a recuperer
	 */
	public Carte recupererCarte(Carte ct, int index) {
		
		
		//On verifie que l'index de la carte est bon, 
		if (index >= 0 && index < (this.compterCarte())) {
			
			Carte c = this.main.get(index);
			//et que la carte est compatible avec celle du talon
			if (c.verifier(ct) == true) {
				this.main.remove(index);
				return c;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Methode permettant de compter le nombre total de point de l'ensemble des cartes
	 * @return le nombre de point total de l'ensemble des cartes de sa main
	 */
	public int compterPoint(){
		int point=0;
		for (Carte c: main) {
			point+=c.getPoints();
		}
		return point;
	}
	
	/**
	 * Methode permettant de donner la couleur qui revient le plus dans sa main
	 * @return la couleur
	 */
	public String couleurDominante(){

		// Les cles sont les Sring
		Map<String, Integer> couleur = new HashMap<String, Integer>();
		couleur.put("rouge", 0);
		couleur.put("vert", 0);
		couleur.put("bleu", 0);
		couleur.put("jaune", 0);
		Iterator<Carte> it = main.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if(couleur.containsKey(c.getCouleur())){
				couleur.put(c.getCouleur(), couleur.get(c.getCouleur())+1);
			}			
		}
		// Chaque ligne est une entree
		Entry<String,Integer> max = null; 
		for(Entry<String,Integer> entry : couleur.entrySet()) {
		    if (max == null || entry.getValue() > max.getValue()) {
		    	max = entry;
		    }
		}
		return max.getKey();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "main=" + main + "";
	}
}