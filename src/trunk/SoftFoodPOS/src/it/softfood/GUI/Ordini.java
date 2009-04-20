/*
 * Ordini.java
 *
 * Created on 19 aprile 2009, 18.05
 */

package it.softfood.GUI;

/**
 *
 * @author  marcograsso
 */
public class Ordini extends javax.swing.JPanel {

    private MainView frame;
    /** Creates new form Ordini */
    public Ordini(MainView frame) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setMaximumSize(new java.awt.Dimension(600, 500));
        setMinimumSize(new java.awt.Dimension(600, 500));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 500));
        setLayout(new java.awt.GridLayout(1, 1, 5, 5));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Ordini.class);
        jList1.setFont(resourceMap.getFont("jList1.font")); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "LASAGNE ALLA BOLOGNESE" };
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
    frame.getActualPanel().setVisible(false);
    Ingredienti ingr=new Ingredienti(frame);
    frame.setActualPanel(ingr);
    frame.setComponent(ingr);
}//GEN-LAST:event_jList1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
