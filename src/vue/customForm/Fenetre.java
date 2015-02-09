package customForm;


import javax.swing.*;

/**
 * Classe modelisant l'ensemble des fentres de l'application
 * @author ikounga_marvel
 *
 */
public class Fenetre extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private BarreMenu barredeMenu;
    private JLabel imageDeFond;
    private JPanel zoneAffichage;
    

    /**
     * Constructeur de la fenetre
     * @param urlFond path de l'image qui sera utilise comme background sur la fenetre
     */
    public Fenetre(String urlFond) {
        zoneAffichage = new JPanel();
        imageDeFond = new JLabel();
        barredeMenu = new BarreMenu();


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        zoneAffichage.setBackground(new java.awt.Color(255, 255, 255));
        zoneAffichage.setPreferredSize(new java.awt.Dimension(1200, 700));
        zoneAffichage.setLayout(null);

        
        imageDeFond.setIcon(new ImageIcon(getClass().getResource("/"+urlFond)));
        zoneAffichage.add(imageDeFond);       
        imageDeFond.setBounds(0, 0, 1200, 670);

        getContentPane().add(zoneAffichage);
        zoneAffichage.setBounds(0, 0, 1200, 670);

        setJMenuBar(barredeMenu);
        
        
        
        pack();
        setLocationRelativeTo(null);
        
    }

	/**
	 * Getter du panel zoneAffichage
	 * @return the zoneAffichage
	 */
	public JPanel getZoneAffichage() {
		return zoneAffichage;
	}

	/**
	 * Setter du panel zoneAffichage
	 * @param zoneAffichage the zoneAffichage to set
	 */
	public void setZoneAffichage(JPanel zoneAffichage) {
		this.zoneAffichage = zoneAffichage;
	}

	/**
	 * Getter de la barre de menu
	 * @return the barredeMenu
	 */
	public BarreMenu getBarredeMenu() {
		return barredeMenu;
	}
	
	/**
	 * Methode permettant d'activer le sous menu recommencer
	 * @return void
	 */
	public void activerMenuRecommencer() {
		barredeMenu.getSousMenuRecommencer().setEnabled(true);
	}

}
  