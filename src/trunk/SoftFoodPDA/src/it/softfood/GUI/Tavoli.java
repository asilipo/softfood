package it.softfood.GUI;


import it.softfood.aspect.ExecuteFacade;
import it.softfood.entity.Tavolo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultListModel;
import org.jdesktop.application.FrameView;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */
public class Tavoli extends javax.swing.JPanel {

   private ExecuteFacade tavoloFacade;
    private ArrayList<Tavolo> tavoli;
    private String[] listaTavoli;
    private int numeroPosti = 0;

    private void initFacade() {
        System.out.println("PPPPPRRRRRROOOOOVVVVVVAAAAAAAAA INIT");
        try {
            //Hashtable hash=new Hashtable();
            //hash.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
            //hash.put("java.naming.provider.url","jnp://localhost:1099");
            tavoloFacade=new ExecuteFacade();
            
            //ordinazioneFacade = (OrdinazioneFacadeRemote) initial.lookup("OrdinazioneFacade");
        
        } catch (NullPointerException ex) {
            System.err.println("Errore null pointer");
        }
    }

    public Tavoli(FrameView frame, boolean vuoti) {
        this.frame = frame;
        this.vuoti = vuoti;

        System.out.println("PPPPPRRRRRROOOOOVVVVVVAAAAAAAAA CALLCLASS");
        initComponents();
        initFacade();

        if (vuoti) {
            SelezionaTavoli.setText(SelezionaTavoli.getText() + " un tavolo vuoto:");
            tavoli = (ArrayList<Tavolo>) tavoloFacade.selezionaTavoliLiberi();
        } else {
            SelezionaTavoli.setText(SelezionaTavoli.getText() + " un tavolo:");
            //tavoli = (ArrayList<Tavolo>) tavoloFacade.selezionaTavoliOccupati();

            jComboBox2.setVisible(false);
            jLabel1.setVisible(false);

        }

        int i = 0;
        int size = tavoli.size();
        model = new DefaultListModel();

        if (size == 0) {
            add.setEnabled(false);
            jComboBox1.setEnabled(false);
            jComboBox2.setEnabled(false);
            model.addElement("Nessun tavolo disponibile per l'operazione! ");
        }

        listaTavoli = new String[size];
        for (Tavolo tavolo : tavoli) {
            listaTavoli[i] = tavolo.getRiferimento();
            jComboBox1.addItem(tavolo.getRiferimento());
            i++;
        }

        jList1.setModel(model);
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
        pannello_tavolo = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        add = new javax.swing.JButton();
        pannello_aggiunta = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        pannello_posti = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Ok = new javax.swing.JButton();
        Annulla = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(225, 450));
        setMinimumSize(new java.awt.Dimension(225, 450));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(225, 450));
        setLayout(new java.awt.GridLayout(6, 1, 5, 5));

        SelezionaTavoli.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Tavoli.class);
        SelezionaTavoli.setText(resourceMap.getString("SelezionaTavoli.text")); // NOI18N
        SelezionaTavoli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SelezionaTavoli.setName("SelezionaTavoli"); // NOI18N
        add(SelezionaTavoli);

        pannello_tavolo.setName("pannello_tavolo"); // NOI18N
        pannello_tavolo.setLayout(new java.awt.BorderLayout());

        jComboBox1.setName("jComboBox1"); // NOI18N
        pannello_tavolo.add(jComboBox1, java.awt.BorderLayout.CENTER);

        add.setFont(resourceMap.getFont("add.font")); // NOI18N
        add.setText(resourceMap.getString("add.text")); // NOI18N
        add.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add.setMaximumSize(new java.awt.Dimension(75, 10));
        add.setMinimumSize(new java.awt.Dimension(75, 10));
        add.setName("add"); // NOI18N
        add.setPreferredSize(new java.awt.Dimension(75, 10));
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        pannello_tavolo.add(add, java.awt.BorderLayout.EAST);

        add(pannello_tavolo);

        pannello_aggiunta.setName("pannello_aggiunta"); // NOI18N
        pannello_aggiunta.setLayout(new java.awt.GridLayout(1, 1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setName("jList1"); // NOI18N
        jScrollPane1.setViewportView(jList1);

        pannello_aggiunta.add(jScrollPane1);

        add(pannello_aggiunta);

        pannello_posti.setName("pannello_posti"); // NOI18N
        pannello_posti.setLayout(new java.awt.BorderLayout());

        jComboBox2.setName("jComboBox2"); // NOI18N
        pannello_posti.add(jComboBox2, java.awt.BorderLayout.CENTER);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setName("jLabel1"); // NOI18N
        pannello_posti.add(jLabel1, java.awt.BorderLayout.NORTH);

        add(pannello_posti);

        jPanel2.setName("jPanel2"); // NOI18N
        add(jPanel2);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        Ok.setText(resourceMap.getString("Ok.text")); // NOI18N
        Ok.setEnabled(false);
        Ok.setMaximumSize(new java.awt.Dimension(100, 29));
        Ok.setMinimumSize(new java.awt.Dimension(100, 29));
        Ok.setName("Ok"); // NOI18N
        Ok.setPreferredSize(new java.awt.Dimension(100, 29));
        Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkActionPerformed(evt);
            }
        });
        jPanel1.add(Ok);

        Annulla.setText(resourceMap.getString("Annulla.text")); // NOI18N
        Annulla.setMaximumSize(new java.awt.Dimension(100, 29));
        Annulla.setMinimumSize(new java.awt.Dimension(100, 29));
        Annulla.setName("Annulla"); // NOI18N
        Annulla.setPreferredSize(new java.awt.Dimension(100, 50));
        Annulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnullaActionPerformed(evt);
            }
        });
        jPanel1.add(Annulla);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkActionPerformed
        /*Enumeration enumeration = model.elements();
        ArrayList<String> tav = new ArrayList<String>();
        while (enumeration.hasMoreElements()) {
            tav.add((String) enumeration.nextElement());
        }
        Ordinazione ordine = null;

        if (vuoti) {
            //Inserimento
            Long tavoloSelezionato = tavoloFacade.occupaTavoli(tav);

            ordine = new Ordinazione();
            ordine.setTavolo(tavoloFacade.selezionaTavolo(tavoloSelezionato));
            ordine.setCoperti(Integer.parseInt((String) jComboBox2.getSelectedItem()));
            ordine.setTerminato(false);

            try {
                ordine = ordinazioneFacade.inserisciOrdinazione(ordine);
            } catch (NullPointerException e) {
                this.setVisible(false);
                Tavoli pannello_tavoli = new Tavoli(frame, vuoti);
                frame.setComponent(pannello_tavoli);
            }
        } else {
            ordine = ordinazioneFacade.selezionaOrdinazioneGiornalieraPerTavolo(tav.get(0), new Boolean("false"));
        }*/

        this.setVisible(false);

        frame.setComponent(new Menu(frame, /*ordine.getId()*/null));
    }//GEN-LAST:event_OkActionPerformed

    private void AnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnullaActionPerformed
        this.setVisible(false);

        frame.setComponent(new Ordine(frame));
    }//GEN-LAST:event_AnnullaActionPerformed

private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
    Ok.setEnabled(true);

    int dim = 20;
    String[] posti = new String[dim];
    String riferimento = (String) jComboBox1.getSelectedItem();
    model.addElement(riferimento);
    jComboBox1.removeItemAt(jComboBox1.getSelectedIndex());

    for (Tavolo tavolo : tavoli) {
        if (tavolo.getRiferimento().equals(riferimento)) {
            numeroPosti = numeroPosti + tavolo.getNumeroPosti();
        }
    }

    for (int i = 0; i < numeroPosti && i < dim; i++) {
        posti[i] = ((Integer) (i + 1)).toString();
    }
    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(posti));

    if (!vuoti) {
        add.setEnabled(false);
        jComboBox1.setEnabled(false);
    }
}//GEN-LAST:event_addActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annulla;
    private javax.swing.JButton Ok;
    private javax.swing.JLabel SelezionaTavoli;
    private javax.swing.JButton add;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pannello_aggiunta;
    private javax.swing.JPanel pannello_posti;
    private javax.swing.JPanel pannello_tavolo;
    // End of variables declaration//GEN-END:variables
    private FrameView frame;
    private DefaultListModel model;
    private boolean vuoti;
}