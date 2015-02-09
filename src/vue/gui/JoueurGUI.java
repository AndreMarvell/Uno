package gui;


import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import joueur.*;

/**
 * Classe representant graphiquement un joueur dans l'interface
 * @author ikounga_marvel
 *
 */
public class JoueurGUI extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private Joueur joueur;
    private JLabel imageCadran = new JLabel();
    private JLabel pseudo = new JLabel();
    private JLabel score = new JLabel();
    private JLabel uno = new JLabel();
    private JLabel active = new JLabel();
    private JLabel cartes = new JLabel();
    int[][] emplacement;
    
    
    /**
     * Constructeur de la partie
     * @param numJoueur numero du joueur
     * @param j joueur a ajouter a l'interface
     * @param nbJoueur nombre de joueurs de la partie
     */
    public JoueurGUI(int numJoueur, Joueur j, int nbJoueur){
        

    	joueur=j;
    	j.addObserver(this);
    	
	    this.setOpaque(false);
	    this.setLayout(null);
	 
	    imageCadran.setIcon(new ImageIcon(getClass().getResource("/CadranJoueur"+numJoueur+".png")));
	    this.add(imageCadran);
	    imageCadran.setBounds(0, 0, 160, 150);
	    
	    pseudo.setOpaque(false);
	    pseudo.setFont(new Font("Wawati SC", 0, 13));
	    pseudo.setText(joueur.getPseudonyme());
	    pseudo.setHorizontalAlignment(JLabel.CENTER);
	    pseudo.setBounds(5, 115, 115, 25);
	    this.add(pseudo, 0);
	    
	    uno.setOpaque(false);
	    uno.setIcon(new ImageIcon(getClass().getResource("/uno.png")));
	    uno.setHorizontalAlignment(JLabel.CENTER);
	    uno.setBounds(100, 0, 40, 30);
	    uno.setVisible(false);
	    this.add(uno, 0);
	    
	    active.setOpaque(false);
	    active.setIcon(new ImageIcon(getClass().getResource("/active.png")));
	    active.setHorizontalAlignment(JLabel.CENTER);
	    active.setBounds(115, 33, 40, 30);
	    active.setVisible(false);
	    this.add(active, 0);
	    
	    cartes.setOpaque(false);
	    cartes.setIcon(new ImageIcon(getClass().getResource("/cartes.png")));
	    cartes.setHorizontalAlignment(JLabel.CENTER);
	    cartes.setBounds(115, 70, 35, 40);
	    this.add(cartes, 0);
	    

	    score.setOpaque(false);
	    score.setFont(new Font(null, 1, 12));
	    score.setHorizontalAlignment(JLabel.CENTER);
	    score.setText(joueur.getScore()+"");
	    score.setForeground(Color.WHITE);
	    score.setBounds(119, 115, 30, 25);
	    this.add(score, 0);
	    
		
	    gestionEmplacement(nbJoueur);
	    
	    int x = emplacement[0][numJoueur-1];
	    int y = emplacement[1][numJoueur-1];
	    
	    this.setBounds(x, y, 150, 150);
	    
		
    
    }
    
    /**
     * Methode permettant de gerer dynamiquement l'emplacement des joueurs sur la table de jeu 
     * en fonction du nombre totale de joueurs de la partie
     * @param nbJoueur nombre de joueur de la partie
     */
    public void gestionEmplacement(int nbJoueur){
    	switch(nbJoueur){
    		case 2 :  
    			emplacement = new int[][]{{150,910},{40,40}};
    			break;
    		case 3 : 
    			emplacement = new int[][]{{150,540,910},{40,0,40}};
    			break;
    		case 4 :
    			emplacement = new int[][]{{150,150,910,910},{450,40,40,450}};
    			break;
    		case 5 : 
    			emplacement = new int[][]{{150,150,540,910,910},{450,40,0,40,450}};
    			break;
    		case 6 : 
    			emplacement = new int[][]{{150,150,150,910,910,910},{450,240,40,40,240,450}};
    			break;
    		case 7 : 
    			emplacement = new int[][]{{150,150,150,540,910,910,910},{450,240,40,0,40,240,450}};
    			break;
    		case 8 : 
    			emplacement = new int[][]{{150,150,150,340,750,910,910,910},{450,240,40,0,0,40,240,450}};
    			break;
    		case 9 : 
    			emplacement = new int[][]{{150,150,150,340,540,750,910,910,910},{450,240,40,0,0,0,40,240,450}};
    			break;
    		case 10 : 
    			emplacement = new int[][]{{150,150,150,340,540,750,910,910,910,540},{450,240,40,0,0,0,40,0,240,450,500}};
    			break;
    	}
    	
    }


	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		
		score.setText(joueur.getScore()+"");
		
		if(joueur.isActive()){
			active.setVisible(true);
		}else{
			active.setVisible(false);
		}
		
		if(joueur.isUno()){
			cartes.setIcon(new ImageIcon(getClass().getResource("/carte.png")));
			uno.setVisible(true);
		}else{
			cartes.setIcon(new ImageIcon(getClass().getResource("/cartes.png")));
			uno.setVisible(false);
		}
		
		if(arg1 instanceof String){
			if(((String) arg1).equals("contestation")){
				int conteste = JOptionPane.showConfirmDialog(null, "Voulez-vous Contester la +4 posee?","Contestation de +4", JOptionPane.YES_NO_OPTION);
				switch(conteste){
					case JOptionPane.YES_OPTION: joueur.setContestation(true);break;
					case JOptionPane.NO_OPTION: joueur.setContestation(false);break;
					default : System.out.println("Choix Incorrecte");
				}	
			}
			else{
				if(((String) arg1).equals("choix couleur")){
					String[] couleurPossible ={"multi","jaune","rouge","vert","bleu"};
					String couleur =(String) JOptionPane.showInputDialog(null, "Quelle couleur choisissez vous?","Choix de couleur", JOptionPane.OK_OPTION, null,couleurPossible,couleurPossible[0] );
					if(couleur==null){
						joueur.setCouleurChoisie("multi");
					}
					else{
						joueur.setCouleurChoisie(couleur);
					}
				}	
			}
			
		}
		
	} 

}
