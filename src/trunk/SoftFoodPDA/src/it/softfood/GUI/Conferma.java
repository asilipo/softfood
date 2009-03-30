package it.softfood.GUI;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.facade.ordinazione.OrdinazioneFacadeRemote;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jdesktop.application.FrameView;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Conferma extends javax.swing.JPanel {

    private OrdinazioneFacadeRemote ordinazioneFacade;

    private void initFacade(Hashtable hash) {
        try {
            InitialContext initial=new InitialContext(hash);
            ordinazioneFacade = (OrdinazioneFacadeRemote) initial.lookup("it.softfood.facade.ordinazione.OrdinazioneFacade");
        } catch(NamingException e) {
            System.err.println("Errore binding: OrdinazioneFacade");
        }
    }
    
    public Conferma(FrameView frame,Long tavolo) {
        this.frame = frame;
        this.tavolo = tavolo;

        initComponents();
        initFacade(null);
        
        ArrayList<LineaOrdinazione> ordini=(ArrayList<LineaOrdinazione>) ordinazioneFacade.selezionaLineeOrdinazionePerOrdinazione(ordinazioneFacade.selezionaOrdinazionePerId(tavolo));
        
        String data[] = new String[ordini.size()];
        
        int i = 0;
        for(LineaOrdinazione linea : ordini)
            data[i++] = linea.getArticolo().getNome() + " - " + linea.getQuantita();
        
        menu.setListData(data);
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
        jPanel2 = new javax.swing.JPanel();
        Cancella = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        OK = new javax.swing.JButton();
        Annulla = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(225, 450));
        setMinimumSize(new java.awt.Dimension(225, 450));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(225, 450));
        setLayout(new java.awt.BorderLayout(5, 5));

        jScrollPane1.setMaximumSize(new java.awt.Dimension(225, 350));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(225, 350));
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(225, 350));

        menu.setName("menu"); // NOI18N
        jScrollPane1.setViewportView(menu);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(1, 3));

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new java.awt.BorderLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Conferma.class);
        Cancella.setText(resourceMap.getString("Cancella.text")); // NOI18N
        Cancella.setName("Cancella"); // NOI18N
        Cancella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancellaActionPerformed(evt);
            }
        });
        jPanel2.add(Cancella, java.awt.BorderLayout.NORTH);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        OK.setText(resourceMap.getString("OK.text")); // NOI18N
        OK.setName("OK"); // NOI18N
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });
        jPanel3.add(OK);

        Annulla.setText(resourceMap.getString("Annulla.text")); // NOI18N
        Annulla.setName("Annulla"); // NOI18N
        Annulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnullaActionPerformed(evt);
            }
        });
        jPanel3.add(Annulla);

        jPanel2.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanel2);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void CancellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancellaActionPerformed
        this.setVisible(false);

        ordinazioneFacade.rimuoviOrdinazione(tavolo, true);

        Ordine ordine = new Ordine(frame);
        frame.setComponent(ordine);   
    }//GEN-LAST:event_CancellaActionPerformed

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
        this.setVisible(false);

        Ordine ordine = new Ordine(frame);
        frame.setComponent(ordine);
    }//GEN-LAST:event_OKActionPerformed

    private void AnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnullaActionPerformed
        this.setVisible(false);
        
        frame.setComponent(new Menu(frame,tavolo));
    }//GEN-LAST:event_AnnullaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annulla;
    private javax.swing.JButton Cancella;
    private javax.swing.JButton OK;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList menu;
    // End of variables declaration//GEN-END:variables
    private FrameView frame;
    private Long tavolo;
    
}
