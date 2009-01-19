/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Conferma.java
 *
 * Created on 14-gen-2009, 16.19.13
 */

package it.softfood.GUI;

import org.jdesktop.application.FrameView;

/**
 *
 * @author mary
 */
public class Conferma extends javax.swing.JPanel {

    /** Creates new form Conferma */
    public Conferma(FrameView frame) {
        initComponents();
        this.frame = frame;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        menu = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        OK = new javax.swing.JButton();
        Annulla = new javax.swing.JButton();
        Cancella = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(225, 450));
        setMinimumSize(new java.awt.Dimension(225, 450));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(225, 450));
        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        menu.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        menu.setName("menu"); // NOI18N
        jScrollPane1.setViewportView(menu);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(1, 3));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Conferma.class);
        OK.setText(resourceMap.getString("OK.text")); // NOI18N
        OK.setName("OK"); // NOI18N
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });
        jPanel1.add(OK);

        Annulla.setText(resourceMap.getString("Annulla.text")); // NOI18N
        Annulla.setName("Annulla"); // NOI18N
        Annulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnullaActionPerformed(evt);
            }
        });
        jPanel1.add(Annulla);

        Cancella.setText(resourceMap.getString("Cancella.text")); // NOI18N
        Cancella.setName("Cancella"); // NOI18N
        Cancella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancellaActionPerformed(evt);
            }
        });
        jPanel1.add(Cancella);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void CancellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancellaActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Ordine ordine = new Ordine(frame);
        frame.setComponent(ordine);
        
       
    }//GEN-LAST:event_CancellaActionPerformed

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Ordine ordine = new Ordine(frame);
        frame.setComponent(ordine);
    }//GEN-LAST:event_OKActionPerformed

    private void AnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnullaActionPerformed
        // TODO add your handling code here:
          this.setVisible(false);
        Menu menu = new Menu(frame);
        frame.setComponent(menu);
    }//GEN-LAST:event_AnnullaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annulla;
    private javax.swing.JButton Cancella;
    private javax.swing.JButton OK;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList menu;
    // End of variables declaration//GEN-END:variables
    private FrameView frame;
}
