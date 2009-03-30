package it.softfood.GUI;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Variante;
import it.softfood.enumeration.TipoVariante;
import it.softfood.facade.articolomenu.ArticoloMenuFacadeRemote;
import it.softfood.facade.ordinazione.OrdinazioneFacadeRemote;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultListModel;
import org.jdesktop.application.FrameView;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */
public class Pietanza extends javax.swing.JPanel {

    private ArticoloMenuFacadeRemote articolo;
    private OrdinazioneFacadeRemote ordinazioneFacade;

    private void initFacade(Hashtable hash) {
        try {
            InitialContext initial = new InitialContext(hash);
            articolo = (ArticoloMenuFacadeRemote) initial.lookup("it.softfood.facade.articolomenu.ArticoloMenuFacade");
            ordinazioneFacade = (OrdinazioneFacadeRemote) initial.lookup("it.softfood.facade.ordinazione.OrdinazioneFacade");
        } catch (NamingException e) {
            System.err.println("Errore binding: ArticoloMenuFacade - OrdinazioneFacade");
        }
    }

    public Pietanza(FrameView frame, Long tavolo, Long id, String tipo) {
        this.tavolo = tavolo;
        this.tipo = tipo;

        initComponents();
        initFacade(null);

        this.frame = frame;
        this.id = id;

        ArrayList<Ingrediente> ingredienti = (ArrayList<Ingrediente>) articolo.selezionaIngredientiPietanza(id);

        String ingr[] = new String[ingredienti.size()];
        int i = 0;
        for (Ingrediente ingrediente : ingredienti) {
            ingr[i++] = ingrediente.getNome();
        }
        listaIngredienti.setListData(ingr);

        int disp;
        if (tipo.equalsIgnoreCase("bibite")) {
            disp = articolo.selezionaDisponibilitaBevanda(id);
            jPanel1.setVisible(false);
            cancella.setVisible(false);
        } else {
            disp = (Integer) articolo.selezionaDisponibilitaPietanza(id);
        }
        disponibilita.setText(disponibilita.getText() + disp);

        for (int idisp = 1; idisp <= disp; idisp++) {
            jComboBox1.addItem(idisp);
        }
        if (!tipo.equalsIgnoreCase("bibite")) {
            ArrayList<Ingrediente> ing = (ArrayList<Ingrediente>) ordinazioneFacade.selezionaIngredientiPerVariante();

            for (Ingrediente ingrediente : ing) {
                jComboBox3.addItem(ingrediente.getNome());
            }
        }

        jListModel = new DefaultListModel();
        jList1.setModel(jListModel);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ingredienti = new javax.swing.JPanel();
        disponibilita = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaIngredienti = new javax.swing.JList();
        quantita = new javax.swing.JPanel();
        combo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        cancella = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        pannello_bottoni = new javax.swing.JPanel();
        OK = new javax.swing.JButton();
        annulla = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(225, 450));
        setMinimumSize(new java.awt.Dimension(225, 450));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(225, 450));
        setLayout(new java.awt.BorderLayout(5, 5));

        ingredienti.setName("ingredienti"); // NOI18N
        ingredienti.setLayout(new java.awt.BorderLayout(0, 2));

        disponibilita.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Pietanza.class);
        disponibilita.setText(resourceMap.getString("disponibilita.text")); // NOI18N
        disponibilita.setName("disponibilita"); // NOI18N
        ingredienti.add(disponibilita, java.awt.BorderLayout.SOUTH);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        ingredienti.add(jLabel1, java.awt.BorderLayout.NORTH);

        jScrollPane1.setName("ingredienti"); // NOI18N

        listaIngredienti.setName("listaIngredienti"); // NOI18N
        jScrollPane1.setViewportView(listaIngredienti);

        ingredienti.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(ingredienti, java.awt.BorderLayout.NORTH);

        quantita.setName("quantita"); // NOI18N
        quantita.setLayout(new java.awt.BorderLayout(0, 2));

        combo.setName("combo"); // NOI18N
        combo.setPreferredSize(new java.awt.Dimension(20, 20));
        combo.setLayout(new java.awt.BorderLayout());

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        combo.add(jLabel2, java.awt.BorderLayout.WEST);

        jComboBox1.setName("jComboBox1"); // NOI18N
        combo.add(jComboBox1, java.awt.BorderLayout.CENTER);

        quantita.add(combo, java.awt.BorderLayout.NORTH);

        if (!tipo.equalsIgnoreCase("bibite")) {
            cancella.setText(resourceMap.getString("cancella.text")); // NOI18N
            cancella.setEnabled(false);
            cancella.setName("cancella"); // NOI18N
        }
        cancella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellaActionPerformed(evt);
            }
        });
        if (!tipo.equalsIgnoreCase("bibite")) {
            quantita.add(cancella, java.awt.BorderLayout.SOUTH);
        }

        if (!tipo.equalsIgnoreCase("bibite")) {
            jPanel1.setName("jPanel1"); // NOI18N
        }
        jPanel1.setLayout(new java.awt.BorderLayout());
        if (!tipo.equalsIgnoreCase("bibite")) {

            if (!tipo.equalsIgnoreCase("bibite")) {
                jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
                jLabel3.setName("jLabel3"); // NOI18N
            }
            jPanel1.add(jLabel3, java.awt.BorderLayout.NORTH);

            jPanel2.setMinimumSize(new java.awt.Dimension(155, 150));
            jPanel2.setName("jPanel2"); // NOI18N
            jPanel2.setPreferredSize(new java.awt.Dimension(100, 150));
            jPanel2.setLayout(new java.awt.BorderLayout());

            if (!tipo.equalsIgnoreCase("bibite")) {
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
                jComboBox2.setName("jComboBox2"); // NOI18N
            }
            jComboBox2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jComboBox2ActionPerformed(evt);
                }
            });
            jPanel2.add(jComboBox2, java.awt.BorderLayout.WEST);

            jComboBox3.setName("jComboBox3"); // NOI18N
            jPanel2.add(jComboBox3, java.awt.BorderLayout.CENTER);

            jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
            jButton1.setName("jButton1"); // NOI18N
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });
            jPanel2.add(jButton1, java.awt.BorderLayout.PAGE_END);

            jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

            jScrollPane2.setName("jScrollPane2"); // NOI18N
            jScrollPane2.setPreferredSize(new java.awt.Dimension(260, 100));

            jList1.setName("jList1"); // NOI18N
            jList1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jList1MouseClicked(evt);
                }
            });
            jScrollPane2.setViewportView(jList1);

            jPanel1.add(jScrollPane2, java.awt.BorderLayout.SOUTH);

        }

        if (!tipo.equalsIgnoreCase("bibite")) {
            quantita.add(jPanel1, java.awt.BorderLayout.CENTER);
        }

        add(quantita, java.awt.BorderLayout.CENTER);

        pannello_bottoni.setName("pannello_bottoni"); // NOI18N
        pannello_bottoni.setLayout(new java.awt.GridLayout(1, 2));

        OK.setText(resourceMap.getString("OK.text")); // NOI18N
        OK.setName("OK"); // NOI18N
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });
        pannello_bottoni.add(OK);

        annulla.setText(resourceMap.getString("annulla.text")); // NOI18N
        annulla.setName("annulla"); // NOI18N
        annulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annullaActionPerformed(evt);
            }
        });
        pannello_bottoni.add(annulla);

        add(pannello_bottoni, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

private void cancellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancellaActionPerformed
    jListModel.removeElementAt(jList1.getSelectedIndex());
    cancella.setEnabled(false);
}//GEN-LAST:event_cancellaActionPerformed

private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
    this.setVisible(false);
    LineaOrdinazione linea = new LineaOrdinazione();
    linea.setOrdinazione(ordinazioneFacade.selezionaOrdinazionePerId(tavolo));
    linea.setArticolo(articolo.selezionaArticoloMenuPerId(id));
    linea.setQuantita((Integer) jComboBox1.getSelectedItem());
    linea = ordinazioneFacade.inserisciLineaOrdinazione(linea);

    Variante variante = new Variante();

    if (!jListModel.isEmpty()) {
        Enumeration enumeration = jListModel.elements();
        while (enumeration.hasMoreElements()) {
            String var = (String) enumeration.nextElement();
            variante.setLineaOrdinazione(linea);
            if (var.substring(0, 1).equalsIgnoreCase("+")) {
                //variante.setTipoVariazione(TipoVariante.AGGIUNTA);
            } else {
                //variante.setTipoVariazione(TipoVariante.RIMOZIONE);
            }

            variante.setIngrediente(ordinazioneFacade.selezionaIngredientePerNome(var.substring(2)));

            ordinazioneFacade.inserisciVariante(variante);
            variante = new Variante();
        }
    }

    if (tipo.equalsIgnoreCase("bibite")) {
        Bibite pannello = new Bibite(frame, tavolo);
        frame.setComponent(pannello);
    } else {
        Pannello_ordinazioni pannello = new Pannello_ordinazioni(frame, tavolo, tipo);
        frame.setComponent(pannello);
    }

}//GEN-LAST:event_OKActionPerformed

private void annullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annullaActionPerformed
    this.setVisible(false);
    if (tipo.equalsIgnoreCase("bibite")) {
        Bibite pannello = new Bibite(frame, tavolo);
        frame.setComponent(pannello);
    } else {
        Pannello_ordinazioni pannello = new Pannello_ordinazioni(frame, tavolo, tipo);
        frame.setComponent(pannello);
    }
}//GEN-LAST:event_annullaActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String data = (String) jComboBox2.getSelectedItem() + " " + (String) jComboBox3.getSelectedItem();
    jListModel.addElement(data);
}//GEN-LAST:event_jButton1ActionPerformed

private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
    String opt = (String) jComboBox2.getSelectedItem();
    jComboBox3.removeAllItems();
    ArrayList<Ingrediente> ingredienti;
    if (opt.equalsIgnoreCase("-")) {
        ingredienti = (ArrayList<Ingrediente>) articolo.selezionaIngredientiPietanza(id);
    } else {
        ingredienti = (ArrayList<Ingrediente>) ordinazioneFacade.selezionaIngredientiPerVariante();
    }

    for (Ingrediente ingrediente : ingredienti) {
        jComboBox3.addItem(ingrediente.getNome());
    }
}//GEN-LAST:event_jComboBox2ActionPerformed

private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
// TODO add your handling code here:
    int i = jList1.getFirstVisibleIndex();
    if (i == -1) {
        cancella.setEnabled(false);
    } else {
        cancella.setEnabled(true);
    }
}//GEN-LAST:event_jList1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OK;
    private javax.swing.JButton annulla;
    private javax.swing.JButton cancella;
    private javax.swing.JPanel combo;
    private javax.swing.JLabel disponibilita;
    private javax.swing.JPanel ingredienti;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList listaIngredienti;
    private javax.swing.JPanel pannello_bottoni;
    private javax.swing.JPanel quantita;
    // End of variables declaration//GEN-END:variables
    private FrameView frame;
    private Long tavolo;
    private Long id;
    private String tipo;
    private DefaultListModel jListModel;
}