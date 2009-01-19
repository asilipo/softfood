/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Conferma.java
 *
 * Created on 14-gen-2009, 20.22.51
 */

package it.softfood.GUI;

import javax.swing.JPanel;
import org.jdesktop.application.FrameView;

/**
 *
 * @author mary
 */
public class Conferma extends javax.swing.JDialog {

    /** Creates new form Conferma */
    public Conferma(java.awt.Frame parent, boolean modal, FrameView frame, JPanel tavolo) {
        super(parent, modal);
        initComponents();
        this.tavolo = tavolo;
        this.parent=parent;
        this.frame=frame;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Ok = new javax.swing.JButton();
        Annulla = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Conferma.class);
        Ok.setText(resourceMap.getString("Ok.text")); // NOI18N
        Ok.setName("Ok"); // NOI18N
        Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkActionPerformed(evt);
            }
        });
        jPanel1.add(Ok);

        Annulla.setText(resourceMap.getString("Annulla.text")); // NOI18N
        Annulla.setName("Annulla"); // NOI18N
        Annulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnullaActionPerformed(evt);
            }
        });
        jPanel1.add(Annulla);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        getContentPane().add(jTextField1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkActionPerformed
        // TODO add your handling code here:
        this.dispose();
        tavolo.setVisible(false);
        //parent.setVisible(false);
        //CONFERMA L'ORDINE IN DB
        Tavoli tavoli = new Tavoli(frame);
        //frame.setComponent(ordine);
        parent.add(tavoli);
        
        
    }//GEN-LAST:event_OkActionPerformed

    private void AnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnullaActionPerformed
        // TODO add your handling code here:
        this.dispose();
        tavolo.setVisible(false);
        Ordine ordine = new Ordine(frame);
        parent.add(ordine);
    }//GEN-LAST:event_AnnullaActionPerformed

    /**
    * @param args the command line arguments
    */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                   FrameView frame = Main.getApplication().getMainView();
                Conferma dialog = new Conferma(new javax.swing.JFrame(), true, tavolo);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annulla;
    private javax.swing.JButton Ok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    private JPanel tavolo;
    private java.awt.Frame parent;
    private FrameView frame;
}
