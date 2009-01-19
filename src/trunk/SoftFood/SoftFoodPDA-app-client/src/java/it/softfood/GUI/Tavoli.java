/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Tavoli.java
 *
 * Created on 14-gen-2009, 11.16.59
 */

package it.softfood.GUI;

import it.softfood.entity.Tavolo;
import it.softfood.facade.tavolo.TavoloFacadeRemote;
import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jdesktop.application.FrameView;
/**
 *
 * @author mary
 */
public class Tavoli extends javax.swing.JPanel {

    private TavoloFacadeRemote tavoloFacade;
    private ArrayList<Tavolo> tavoli;
    private String[] listaTavoli;

    private void initFacade(){
        try{
            InitialContext initial=new InitialContext();
            tavoloFacade = (TavoloFacadeRemote) initial.lookup("it.softfood.facade.tavolo.TavoloFacade");
        }catch(NamingException e){
            System.err.println("Errore binding TavoloFacade");
        }
    }
    /** Creates new form Tavoli */
    public Tavoli(FrameView frame, boolean vuoti) {
        initComponents();
        initFacade();
        this.frame=frame;
        if(vuoti){
            SelezionaTavoli.setText(SelezionaTavoli.getText()+" un tavolo vuoto:");
            tavoli=(ArrayList<Tavolo>) tavoloFacade.selezionaTavoliLiberi();
            System.out.println("dim: " +tavoli.size());
        }else{
            SelezionaTavoli.setText(SelezionaTavoli.getText()+" un tavolo:");
            tavoli= (ArrayList<Tavolo>) tavoloFacade.selezionaTavoliOccupati();
        }
        int i=0;
        for(Tavolo tavolo:tavoli){
            listaTavoli[i]=tavolo.getRiferimento();
        }
        Tavoli.setListData(listaTavoli);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SelezionaTavoli = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tavoli = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        Ok = new javax.swing.JButton();
        Annulla = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(225, 450));
        setMinimumSize(new java.awt.Dimension(225, 450));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(225, 450));
        setLayout(new java.awt.BorderLayout(5, 5));

        SelezionaTavoli.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Tavoli.class);
        SelezionaTavoli.setText(resourceMap.getString("SelezionaTavoli.text")); // NOI18N
        SelezionaTavoli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SelezionaTavoli.setName("SelezionaTavoli"); // NOI18N
        add(SelezionaTavoli, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        Tavoli.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Tavoli.setName("ListaTavoli"); // NOI18N
        Tavoli.setVisibleRowCount(10);
        Tavoli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TavoliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tavoli);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        Ok.setText(resourceMap.getString("Ok.text")); // NOI18N
        Ok.setEnabled(false);
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

        add(jPanel1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void TavoliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TavoliMouseClicked
        // TODO add your handling code here:
        Ok.setEnabled(true);
    }//GEN-LAST:event_TavoliMouseClicked

    private void OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Menu menu=new Menu(frame);
        frame.setComponent(menu);
    }//GEN-LAST:event_OkActionPerformed

    private void AnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnullaActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Ordine ordine=new Ordine(frame);
        frame.setComponent(ordine);
    }//GEN-LAST:event_AnnullaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annulla;
    private javax.swing.JButton Ok;
    private javax.swing.JLabel SelezionaTavoli;
    private javax.swing.JList Tavoli;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    private FrameView frame;
}