/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Menu.java
 *
 * Created on 14-gen-2009, 12.07.01
 */

package it.softfood.GUI;

import org.jdesktop.application.FrameView;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */
public class Menu extends javax.swing.JPanel {

    /** Creates new form Menu */
    public Menu(FrameView frame,Long tavolo) {
        initComponents();
        this.frame = frame;
        this.tavolo = tavolo;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Antipasti = new javax.swing.JButton();
        Primi = new javax.swing.JButton();
        Secondi = new javax.swing.JButton();
        Contorni = new javax.swing.JButton();
        Dolci = new javax.swing.JButton();
        Bibite = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Ok = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Annulla = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(225, 450));
        setMinimumSize(new java.awt.Dimension(225, 450));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(225, 450));
        setLayout(new java.awt.GridLayout(4, 2, 5, 5));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Menu.class);
        Antipasti.setIcon(resourceMap.getIcon("Antipasti.icon")); // NOI18N
        Antipasti.setText(resourceMap.getString("Antipasti.text")); // NOI18N
        Antipasti.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Antipasti.setName("Antipasti"); // NOI18N
        Antipasti.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Antipasti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AntipastiActionPerformed(evt);
            }
        });
        add(Antipasti);

        Primi.setIcon(resourceMap.getIcon("Primi.icon")); // NOI18N
        Primi.setText(resourceMap.getString("Primi.text")); // NOI18N
        Primi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Primi.setName("Primi"); // NOI18N
        Primi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Primi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrimiActionPerformed(evt);
            }
        });
        add(Primi);

        Secondi.setIcon(resourceMap.getIcon("Secondi.icon")); // NOI18N
        Secondi.setText(resourceMap.getString("Secondi.text")); // NOI18N
        Secondi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Secondi.setName("Secondi"); // NOI18N
        Secondi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Secondi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SecondiActionPerformed(evt);
            }
        });
        add(Secondi);

        Contorni.setIcon(resourceMap.getIcon("Contorni.icon")); // NOI18N
        Contorni.setText(resourceMap.getString("Contorni.text")); // NOI18N
        Contorni.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Contorni.setName("Contorni"); // NOI18N
        Contorni.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Contorni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContorniActionPerformed(evt);
            }
        });
        add(Contorni);

        Dolci.setIcon(resourceMap.getIcon("Dolci.icon")); // NOI18N
        Dolci.setText(resourceMap.getString("Dolci.text")); // NOI18N
        Dolci.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Dolci.setName("Dolci"); // NOI18N
        Dolci.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Dolci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DolciActionPerformed(evt);
            }
        });
        add(Dolci);

        Bibite.setIcon(resourceMap.getIcon("Bibite.icon")); // NOI18N
        Bibite.setText(resourceMap.getString("Bibite.text")); // NOI18N
        Bibite.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Bibite.setName("Bibite"); // NOI18N
        Bibite.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Bibite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BibiteActionPerformed(evt);
            }
        });
        add(Bibite);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.BorderLayout());

        Ok.setText(resourceMap.getString("Ok.text")); // NOI18N
        Ok.setName("Ok"); // NOI18N
        Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkActionPerformed(evt);
            }
        });
        jPanel1.add(Ok, java.awt.BorderLayout.PAGE_END);

        add(jPanel1);

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new java.awt.BorderLayout());

        Annulla.setText(resourceMap.getString("Annulla.text")); // NOI18N
        Annulla.setMaximumSize(new java.awt.Dimension(75, 29));
        Annulla.setMinimumSize(new java.awt.Dimension(75, 29));
        Annulla.setName("Annulla"); // NOI18N
        Annulla.setPreferredSize(new java.awt.Dimension(75, 29));
        Annulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnullaActionPerformed(evt);
            }
        });
        jPanel2.add(Annulla, java.awt.BorderLayout.PAGE_END);

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    private void OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Conferma conferma = new Conferma(frame);
        frame.setComponent(conferma);

    }//GEN-LAST:event_OkActionPerformed

    private void AnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnullaActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Tavoli tavoli = new Tavoli(frame,true);
        frame.setComponent(tavoli);
    }//GEN-LAST:event_AnnullaActionPerformed

private void AntipastiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AntipastiActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
    Pannello_ordinazioni antipasto = new Pannello_ordinazioni(frame,tavolo,"antipasti");
    frame.setComponent(antipasto);
}//GEN-LAST:event_AntipastiActionPerformed

private void PrimiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrimiActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
    Pannello_ordinazioni primi = new Pannello_ordinazioni(frame,tavolo,"primi");
    frame.setComponent(primi);
}//GEN-LAST:event_PrimiActionPerformed

private void SecondiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SecondiActionPerformed
// TODO add your handling code here:
     this.setVisible(false);
    Pannello_ordinazioni secondi = new Pannello_ordinazioni(frame,tavolo,"secondi");
    frame.setComponent(secondi);
}//GEN-LAST:event_SecondiActionPerformed

private void ContorniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContorniActionPerformed
// TODO add your handling code here:
     this.setVisible(false);
    Pannello_ordinazioni contorni = new Pannello_ordinazioni(frame,tavolo,"contorni");
    frame.setComponent(contorni);
}//GEN-LAST:event_ContorniActionPerformed

private void DolciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DolciActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
    Pannello_ordinazioni dolci = new Pannello_ordinazioni(frame,tavolo,"dolci");
    frame.setComponent(dolci);
}//GEN-LAST:event_DolciActionPerformed

private void BibiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BibiteActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
    Bibite bibite = new Bibite(frame,tavolo);
    frame.setComponent(bibite);
}//GEN-LAST:event_BibiteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annulla;
    private javax.swing.JButton Antipasti;
    private javax.swing.JButton Bibite;
    private javax.swing.JButton Contorni;
    private javax.swing.JButton Dolci;
    private javax.swing.JButton Ok;
    private javax.swing.JButton Primi;
    private javax.swing.JButton Secondi;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
    private FrameView frame;
    private Long tavolo;
}
