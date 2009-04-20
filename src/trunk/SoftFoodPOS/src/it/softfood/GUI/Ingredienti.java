/*
 * Ingredienti.java
 *
 * Created on 19 aprile 2009, 18.41
 */

package it.softfood.GUI;

/**
 *
 * @author  marcograsso
 */
public class Ingredienti extends javax.swing.JPanel {

    private MainView frame;
    /** Creates new form Ingredienti */
    public Ingredienti(MainView frame) {
        this.frame=frame;
        initComponents();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(600, 500));
        setMinimumSize(new java.awt.Dimension(600, 500));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 500));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Ingredienti.class);
        jList1.setFont(resourceMap.getFont("jList1.font")); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "PASTA", "SUGO" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setName("jList1"); // NOI18N
        jScrollPane1.setViewportView(jList1);

        jPanel1.add(jScrollPane1);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setMaximumSize(new java.awt.Dimension(600, 100));
        jPanel2.setMinimumSize(new java.awt.Dimension(600, 100));
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 100));
        jPanel2.setLayout(new java.awt.GridLayout(1, 3, 5, 5));

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        add(jPanel2, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    frame.getActualPanel().setVisible(false);
    Ordini ordini=new Ordini(frame);
    frame.setActualPanel(ordini);
    frame.setComponent(ordini);
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    
    //SETTARE ORDINE COME EVASO
    frame.getActualPanel().setVisible(false);
    Ordini ordini=new Ordini(frame);
    frame.setActualPanel(ordini);
    frame.setComponent(ordini);
}//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
