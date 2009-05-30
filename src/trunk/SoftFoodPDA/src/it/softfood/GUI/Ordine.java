package it.softfood.GUI;

import it.softfood.entity.User;

import org.jdesktop.application.FrameView;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Ordine extends javax.swing.JPanel {
	
	private static final long serialVersionUID = 1L;
	private User role;
	
    public Ordine(User role, FrameView frame) {
        initComponents();
        this.frame = frame;
        this.role = role;
    }
    
    private void initComponents() {
        Inserimento = new javax.swing.JButton();
        Gestione = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(225, 450));
        setMinimumSize(new java.awt.Dimension(225, 450));
        setName("Form");
        setPreferredSize(new java.awt.Dimension(225, 450));
        setLayout(new java.awt.GridLayout(2, 1, 5, 5));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Ordine.class);
        Inserimento.setIcon(resourceMap.getIcon("Inserimento.icon")); 
        Inserimento.setText(resourceMap.getString("Inserimento.text")); 
        Inserimento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Inserimento.setName("Inserimento"); 
        Inserimento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Inserimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserimentoActionPerformed(evt);
            }
        });
        add(Inserimento);

        Gestione.setIcon(resourceMap.getIcon("Gestione.icon"));
        Gestione.setText(resourceMap.getString("Gestione.text")); 
        Gestione.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Gestione.setName("Gestione"); 
        Gestione.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Gestione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GestioneActionPerformed(evt);
            }
        });
        add(Gestione);
    }

    private void InserimentoActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        frame.setComponent(new Tavoli(role,frame,true));
    }

    private void GestioneActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        frame.setComponent(new Tavoli(role,frame,false));
    }

    private javax.swing.JButton Gestione;
    private javax.swing.JButton Inserimento;
    private FrameView frame;
    
}
