package sauvegarde;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import partie.Partie;

import joueur.Joueur;
import joueur.ListeJoueur;

/**
 * Classe gerant les sauvegardes de meilleures score et  de partie
 * Classe incomplete a terminer : Sauvegarder Partie et sauvegarder Score
 * @author ikounga_marvel
 */
public class Sauvegarde {
	
	static PrintWriter writer;
	static BufferedReader fichier;
		
	/**
	 * Constructeur de la classe fichier
	 * @param urlFichier
	 */
	public Sauvegarde(){
		super();
		
	}

	/**
	 * Methode permettant de sauvegarder le score du joueur si il fait partie des meilleures du jeu
	 * @param j joueur a sauvegarder
	 * @throws IOException 
	 */
	public static void sauvegarderScore(Joueur j) throws IOException{
		
		Map<String, Integer> scores = new HashMap<String, Integer>();
		
	    scores.put(j.getPseudonyme(), j.getScore());
	    
	    try {
	    	String ligne ;
	    	fichier = new BufferedReader(new InputStreamReader(Sauvegarde.class.getResourceAsStream("score.txt")));
	    	 
	    	while ((ligne = fichier.readLine()) != null) {
	    		String pseudo = ligne.split(":")[0];
	    		int score = Integer.parseInt(ligne.split(":")[1]);
	    		scores.put(pseudo, score);
	    	}
	    	fichier.close();
	    	} catch (Exception e) {
	    	e.printStackTrace();
	    	} 
	    scores = Sauvegarde.sortByValues(scores);

	    try {
		    	writer =  new PrintWriter(new FileWriter(Sauvegarde.class.getResource("score.txt").toURI().getPath()));
		    	 
		    	for (String pseudo: scores.keySet()) {
		    		writer.println(pseudo+":"+scores.get(pseudo));
		    	}
		    	writer.close();
	    	} catch (Exception e) {
	    	e.printStackTrace();
	    	} 
}
	
	/**
	 * Methode permettant de sauvegarder les donnees d'une partie en cours
	 * @param joueurs Liste de joueurs de la partie en cours
	 * @throws IOException 
	 */
	public static void sauvegarderPartie(ListeJoueur joueurs) throws IOException {
		
		try {
			
			ObjectOutputStream os;
			try {
				os = new ObjectOutputStream( new FileOutputStream(Sauvegarde.class.getResource("sauvegarde.bin").toURI().getPath()));
				os.writeObject(joueurs);
				os.close();
				System.out.println("Partie sauvegardee");
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Methode permettant de charger une partie inachevee
	 * @return la partie inachevee
	 */
	public static Partie chargerPartie() {
		ListeJoueur joueurs = new ListeJoueur();
		try {
			ObjectInputStream is = new ObjectInputStream(Sauvegarde.class.getResourceAsStream("sauvegarde.bin"));
			joueurs = (ListeJoueur) is.readObject();
			is.close();

		} catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return new Partie(joueurs);
	}
	
	/**
	 * Methode permettant de lire les meilleures score du jeu a partir d'un fichier texte
	 * @throws IOException 
	 */
	public static void lireScore() throws IOException {
	    
	    try {
	    		String ligneScore ;
	    		fichier = new BufferedReader(new InputStreamReader(Sauvegarde.class.getResourceAsStream("score.txt")));
		    	while ((ligneScore = fichier.readLine()) != null) {
		    		System.out.println(ligneScore);
		    	}
	    	 
	    		fichier.close();
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}     
	}
	
	/**
	 * Methode permettant de trier une HashMap
	 * @param table de hashage qui sera trie
	 * @return une nouvelle table de hashage trie
	 */
	public static <cle extends Comparable<?>,valeur extends Comparable<valeur>> Map<cle, valeur> sortByValues(Map<cle, valeur> map) {
        
		List<Map.Entry<cle, valeur>> entries = new LinkedList<Map.Entry<cle, valeur>>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<cle, valeur>>() {

            @Override
            public int compare(Entry<cle, valeur> scoreA, Entry<cle, valeur> scoreB) {
                return scoreB.getValue().compareTo(scoreA.getValue());
            }
        });
        
        Map<cle, valeur> hashMapTriee = new LinkedHashMap<cle, valeur>();
        
        for(Map.Entry<cle, valeur> entry: entries){
            hashMapTriee.put(entry.getKey(), entry.getValue());
        }
     
        return hashMapTriee;
    }

}