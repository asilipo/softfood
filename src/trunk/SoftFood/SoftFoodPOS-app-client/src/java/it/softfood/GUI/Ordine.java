/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Ordine.java
 *
 * Created on 14-gen-2009, 19.38.46
 */

package it.softfood.GUI;

import org.jdesktop.application.FrameView;

/**
 *
 * @author mary
 */
public class Ordine extends javax.swing.JPanel {

    /** Creates new form Ordine */
    public Ordine(FrameView frame) {
        initComponents();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setMaximumSize(new java.awt.Dimension(400, 275));
        setMinimumSize(new java.awt.Dimension(400, 275));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(400, 275));
        setLayout(new java.awt.GridLayout(1, 1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setName("jList1"); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        //SELEZIONARE L'ELEMENTO DELLA LISTA
        Tavolo tavolo=new Tavolo(frame);
        frame.setComponent(tavolo);
    }//GEN-LAST:event_jList1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    private FrameView frame;
}