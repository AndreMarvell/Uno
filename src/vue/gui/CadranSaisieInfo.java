package gui;



import javax.swing.*;

/**
 * Classe modelisant le formulaire de saisie du pseudo d'un joueur reel lors de l'initialisation de la partie
 * @author ikounga_marvel
 *
 */
public class CadranSaisieInfo extends JPanel{

	private static final long serialVersionUID = 1L;
    private JLabel imageCadran = new JLabel();
    public JTextField pseudo = new JTextField();
    
    
    /**
     * Constructeur de la classe
     * @param numJoueur rang du joueur dans la liste des joueurs
     */
    public CadranSaisieInfo(int numJoueur){
        
	    this.setOpaque(false);
	    this.setLayout(null);
	
	    imageCadran.setIcon(new ImageIcon(getClass().getResource("/CadranJoueur"+numJoueur+".png")));
	    this.add(imageCadran);
	    imageCadran.setBounds(0, 0, 160, 150);
	    
	    pseudo.setOpaque(false);
	    pseudo.setFont(new java.awt.Font("Wawati SC", 0, 13));
	    pseudo.setText("Joueur"+numJoueur);
	    pseudo.setHorizontalAlignment(JLabel.CENTER);
	    pseudo.setBounds(3, 112, 150, 33);
	    this.add(pseudo, 0);
   
    } 

}
