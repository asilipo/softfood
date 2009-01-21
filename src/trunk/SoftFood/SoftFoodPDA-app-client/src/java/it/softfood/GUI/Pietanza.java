package it.softfood.GUI;

import it.softfood.entity.Ingrediente;
import it.softfood.facade.articolomenu.ArticoloMenuFacadeRemote;
import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;
import org.jdesktop.application.FrameView;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */
public class Pietanza extends javax.swing.JPanel {

    private ArticoloMenuFacadeRemote articolo;

    private void initFacade() {
        try {
            InitialContext initial = new InitialContext();
            articolo = (ArticoloMenuFacadeRemote) initial.lookup("it.softfood.facade.articolomenu.ArticoloMenuFacade");
        } catch (NamingException e) {
            System.err.println("Errore binding: ArticoloMenuFacade");
        }
    }

    public Pietanza(FrameView frame, Long tavolo, Long id, String tipo) {
        this.tavolo = tavolo;
        this.tipo = tipo;

        initComponents();
        initFacade();

        this.frame = frame;
        this.id = id;

        ArrayList<Ingrediente> ingredienti = (ArrayList<Ingrediente>) articolo.selezionaIngredientiPietanza(id);

        String ingr[] = new String[ingredienti.size()];
        int i = 0;
        for (Ingrediente ingrediente : ingredienti) {
            ingr[i++] = ingrediente.getNome();
        }
        listaIngredienti.setListData(ingr);

        piu = new JComboBox();
        piu.addItem("+");
        piu.addItem("-");

        TableColumn sport = jTable1.getColumnModel().getColumn(0);
        sport.setCellEditor(new DefaultCellEditor(piu));

        int disp;
        if (tipo.equalsIgnoreCase("bibite")) 
            disp = articolo.selezionaDisponibilitaBevanda(id);
        else 
            disp = (Integer) articolo.selezionaDisponibilitaPietanza(id);

        disponibilita.setText(disponibilita.getText() + disp);

        for (int idisp = 1; idisp <= disp; idisp++) {
            jComboBox1.addItem(idisp);
        }
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
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        combo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        cancella = new javax.swing.JButton();
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

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        quantita.add(jLabel3, java.awt.BorderLayout.CENTER);

        jScrollPane2.setMaximumSize(new java.awt.Dimension(225, 60));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(225, 60));
        jScrollPane2.setName("jScrollPane2"); // NOI18N
        jScrollPane2.setPreferredSize(new java.awt.Dimension(225, 60));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                " ", "Variante"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setMaximumSize(new java.awt.Dimension(225, 60));
        jTable1.setMinimumSize(new java.awt.Dimension(226, 60));
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setPreferredSize(new java.awt.Dimension(226, 60));
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N

        quantita.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        combo.setName("combo"); // NOI18N
        combo.setPreferredSize(new java.awt.Dimension(20, 20));
        combo.setLayout(new java.awt.BorderLayout());

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        combo.add(jLabel2, java.awt.BorderLayout.WEST);

        jComboBox1.setName("jComboBox1"); // NOI18N
        combo.add(jComboBox1, java.awt.BorderLayout.CENTER);

        quantita.add(combo, java.awt.BorderLayout.NORTH);

        cancella.setText(resourceMap.getString("cancella.text")); // NOI18N
        cancella.setName("cancella"); // NOI18N
        cancella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellaActionPerformed(evt);
            }
        });
        quantita.add(cancella, java.awt.BorderLayout.SOUTH);

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
// TODO add your handling code here:
    jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String[]{
                " ", "Variante"
            }) {

        Class[] types = new Class[]{
            java.lang.Object.class, java.lang.String.class
        };

        public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
        }
    });

    TableColumn sport = jTable1.getColumnModel().getColumn(0);
    sport.setCellEditor(new DefaultCellEditor(piu));


}//GEN-LAST:event_cancellaActionPerformed

private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
    if (tipo.equalsIgnoreCase("bibite")) {
        Bibite pannello = new Bibite(frame, tavolo);
        frame.setComponent(pannello);
    } else {
        Pannello_ordinazioni pannello = new Pannello_ordinazioni(frame, tavolo, tipo);
        frame.setComponent(pannello);
    }
}//GEN-LAST:event_OKActionPerformed

private void annullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annullaActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
    if (tipo.equalsIgnoreCase("bibite")) {
        Bibite pannello = new Bibite(frame, tavolo);
        frame.setComponent(pannello);
    } else {
        Pannello_ordinazioni pannello = new Pannello_ordinazioni(frame, tavolo, tipo);
        frame.setComponent(pannello);
    }
}//GEN-LAST:event_annullaActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OK;
    private javax.swing.JButton annulla;
    private javax.swing.JButton cancella;
    private javax.swing.JPanel combo;
    private javax.swing.JLabel disponibilita;
    private javax.swing.JPanel ingredienti;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JList listaIngredienti;
    private javax.swing.JPanel pannello_bottoni;
    private javax.swing.JPanel quantita;
    // End of variables declaration//GEN-END:variables
    private FrameView frame;
    private Long tavolo;
    private Long id;
    private String tipo;
    private JComboBox piu;
}
