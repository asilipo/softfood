package it.softfood.GUI;

import it.softfood.entity.User;

import org.jdesktop.application.FrameView;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Ordine extends javax.swing.JPanel {
	
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
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(225, 450));
        setLayout(new java.awt.GridLayout(2, 1, 5, 5));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Ordine.class);
        Inserimento.setIcon(resourceMap.getIcon("Inserimento.icon")); // NOI18N
        Inserimento.setText(resourceMap.getString("Inserimento.text")); // NOI18N
        Inserimento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Inserimento.setName("Inserimento"); // NOI18N
        Inserimento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Inserimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserimentoActionPerformed(evt);
            }
        });
        add(Inserimento);

        Gestione.setIcon(resourceMap.getIcon("Gestione.icon")); // NOI18N
        Gestione.setText(resourceMap.getString("Gestione.text")); // NOI18N
        Gestione.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Gestione.setName("Gestione"); // NOI18N
        Gestione.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Gestione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GestioneActionPerformed(evt);
            }
        });
        add(Gestione);
    }// </editor-fold>//GEN-END:initComponents

    private void InserimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InserimentoActionPerformed
        this.setVisible(false);
        frame.setComponent(new Tavoli(role,frame,true));
    }//GEN-LAST:event_InserimentoActionPerformed

    private void GestioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GestioneActionPerformed
        this.setVisible(false);
        frame.setComponent(new Tavoli(role,frame,false));
    }//GEN-LAST:event_GestioneActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Gestione;
    private javax.swing.JButton Inserimento;
    // End of variables declaration//GEN-END:variables
    private FrameView frame;
    
}
