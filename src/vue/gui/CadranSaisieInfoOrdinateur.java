package gui;



import javax.swing.*;

/**
 * Classe modelisant le formulaire de choix de la strategie d'un joueur virtuel lors de l'initialisation de la partie
 * @author ikounga_marvel
 *
 */
public class CadranSaisieInfoOrdinateur extends JPanel{

	private static final long serialVersionUID = 1L;
    private JLabel imageCadran = new JLabel();
    public JComboBox strategie = new JComboBox();
    
    /**
     * Constructeur de la classe
     * @param numJoueur rang du joueur dans la liste des joueurs
     */    
    public CadranSaisieInfoOrdinateur(int numJoueur){
        
	    this.setOpaque(false);
	    this.setLayout(null);

	    imageCadran.setIcon(new ImageIcon(getClass().getResource("/CadranJoueur"+numJoueur+".png")));
	    this.add(imageCadran);
	    imageCadran.setBounds(0, 0, 160, 150);
	    
	    strategie.setOpaque(false);
	    strategie.setFont(new java.awt.Font("Wawati SC", 0, 13));
	    strategie.setBounds(3, 112, 150, 33);
	    this.add(strategie, 0);
	    
	    strategie.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Agressif", "Agressif avec Bluff", "Normal", "Passif" }));
    
    } 

}
