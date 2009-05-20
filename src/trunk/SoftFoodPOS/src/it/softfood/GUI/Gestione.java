package it.softfood.GUI;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Gestione extends javax.swing.JPanel {
    
	private static final long serialVersionUID = 1L;
	private MainView frame;
    
    public Gestione(MainView frame) {
        this.frame = frame;
        initComponents();
    }

    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(600, 500));
        setMinimumSize(new java.awt.Dimension(600, 500));
        setName("Form");
        setPreferredSize(new java.awt.Dimension(600, 500));
        setLayout(new java.awt.GridLayout(3, 1, 5, 5));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Gestione.class);
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon"));
        jButton1.setText(resourceMap.getString("jButton1.text")); 
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName("jButton1"); 
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setName("jButton2");
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);

        jButton3.setIcon(resourceMap.getIcon("jButton3.icon"));
        jButton3.setText(resourceMap.getString("jButton3.text")); 
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setName("jButton3"); 
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
    }

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
	    String tipo = "Pietanza";
	    frame.getActualPanel().setVisible(false);
	    Visualizza visualizza = new Visualizza(frame, tipo);
	    frame.setActualPanel(visualizza);
	    frame.setComponent(visualizza);
	}
	
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
	    String tipo = "Bevanda";
	    frame.getActualPanel().setVisible(false);
	    Visualizza visualizza = new Visualizza(frame, tipo);
	    frame.setActualPanel(visualizza);
	    frame.setComponent(visualizza);
	}
	
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
	    String tipo = "Ingrediente";
	    frame.getActualPanel().setVisible(false);
	    Visualizza visualizza = new Visualizza(frame, tipo);
	    frame.setActualPanel(visualizza);
	    frame.setComponent(visualizza);
	}

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;

}
