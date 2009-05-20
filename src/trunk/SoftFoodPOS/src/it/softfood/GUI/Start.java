package it.softfood.GUI;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Start extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
	private MainView frame;
    
    public Start(MainView frame) {
        this.frame=frame;
        initComponents();
    }

    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(600, 500));
        setMinimumSize(new java.awt.Dimension(600, 500));
        setName("Form");
        setPreferredSize(new java.awt.Dimension(600, 500));
        setLayout(new java.awt.GridLayout(2, 1, 5, 15));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Start.class);
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

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon"));
        jButton2.setText(resourceMap.getString("jButton2.text"));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setName("jButton2");
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	frame.getActualPanel().setVisible(false);
    	Gestione gestione=new Gestione(frame);
    	frame.setActualPanel(gestione);
    	frame.setComponent(gestione);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    	frame.getActualPanel().setVisible(false);
    	Ordini ordini=new Ordini(frame);
    	frame.setActualPanel(ordini);
    	frame.setComponent(ordini);

    }
  
}
