package gui;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;

/**
 *Classe reprensentant la fenetre d'aide du jeu
 * @author ikounga_marvel
 */
public class Help extends JFrame {

    private JLabel rules;
    private JScrollPane scrollPaneRegle;
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructeur
     */
    public Help() {
        initComponents();
    }

	/**
	 * Methode permettant d'initialiser l'ensemble des composants de la fenetre
	 * @return void
	 */
    private void initComponents() {

        scrollPaneRegle = new JScrollPane();
        rules = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 252, 245));
        setPreferredSize(new java.awt.Dimension(675, 472));
        setResizable(false);

        scrollPaneRegle.setHorizontalScrollBar(null);

        rules.setIcon(new ImageIcon(getClass().getResource("/Rules.jpg"))); // NOI18N
        rules.setMaximumSize(new java.awt.Dimension(674, 942));
        scrollPaneRegle.setViewportView(rules);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(scrollPaneRegle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(scrollPaneRegle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                .add(6, 6, 6))
        );

        pack();
        setLocationRelativeTo(null);
    }

}
