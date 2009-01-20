/*
 * Antipasto.java
 *
 * Created on 19 gennaio 2009, 16.59
 */

package it.softfood.GUI;


import it.softfood.entity.Pietanza;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.facade.articolomenu.ArticoloMenuFacadeRemote;
import it.softfood.facade.ordinazione.OrdinazioneFacadeRemote;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.jdesktop.application.FrameView;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */
public class Secondi extends javax.swing.JPanel {

    private OrdinazioneFacadeRemote ordinazioneFacade;
    private ArticoloMenuFacadeRemote articolo;
    
    private void initFacade(){
        try{
            InitialContext initial=new InitialContext();
            ordinazioneFacade = (OrdinazioneFacadeRemote) initial.lookup("it.softfood.facade.ordinazione.OrdinazioneFacade");
            articolo = (ArticoloMenuFacadeRemote) initial.lookup("it.softfood.facade.articolomenu.ArticoloMenuFacade");
        }catch(NamingException e){
            System.err.println("Errore binding TavoloFacade");
        }
    }
    /** Creates new form Antipasto */
    public Secondi(FrameView frame, String tavolo) {
        initComponents();
        initFacade();
        this.frame=frame;
        this.tavolo=tavolo;
        ArrayList<it.softfood.entity.Pietanza> secondi = (ArrayList<it.softfood.entity.Pietanza>) articolo.selezionaPietanzeDisponibiliPerTipo(TipoPietanza.SECONDO_PIATTO);
        int i=0;
        int j=0;

        
        JComboBox combo= new JComboBox();
        combo.addItem(1);
        combo.addItem(2);
        combo.addItem(3);
        combo.addItem(4);
        combo.addItem(5);
        
        TableColumn sportColumn = tabella_secondi.getColumnModel().getColumn(2);
        sportColumn.setCellEditor(new DefaultCellEditor(combo));
        
        id_antipasto=new XTableColumnModel();
        
        tabella_secondi.setColumnModel(id_antipasto);
        tabella_secondi.createDefaultColumnsFromModel();
        
        id=id_antipasto.getColumnByModelIndex(0);
        
        for(it.softfood.entity.Pietanza pietanza:secondi){
            //antipasto_model.insertRow(tabella_antipasti.getRowCount(), new Object[]{/*pietanza.getId(),pietanza.getNome()*/"PROVA","UNO"});
            tabella_secondi.setValueAt(pietanza.getId(), i, j);
            j++;
            tabella_secondi.setValueAt(pietanza.getNome(), i, j);
            j=0;
            i++;
        }
        
        id_antipasto.setColumnVisible(id, false);
             
        linea_ordine=new XTableColumnModel();
        
        tabella_ordini.setColumnModel(linea_ordine);
        tabella_ordini.createDefaultColumnsFromModel();
        
        id_ordini=linea_ordine.getColumnByModelIndex(0);
        
        linea_ordine.setColumnVisible(id_ordini, false);
        
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pannello_secondi = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabella_secondi = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        add = new javax.swing.JButton();
        visuallizza = new javax.swing.JButton();
        pannello_ordini = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabella_ordini = new javax.swing.JTable();
        bottoni = new javax.swing.JPanel();
        OK = new javax.swing.JButton();
        Annulla = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(225, 450));
        setMinimumSize(new java.awt.Dimension(225, 450));
        setName("Form"); // NOI18N
        setLayout(new java.awt.BorderLayout(5, 0));

        pannello_secondi.setMaximumSize(new java.awt.Dimension(100, 200));
        pannello_secondi.setMinimumSize(new java.awt.Dimension(100, 200));
        pannello_secondi.setName("pannello_secondi"); // NOI18N
        pannello_secondi.setPreferredSize(new java.awt.Dimension(100, 200));
        pannello_secondi.setLayout(new java.awt.BorderLayout(5, 5));

        jScrollPane1.setName("jScrollPane1"); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 200));

        tabella_secondi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Pietanza", "Quantita'"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabella_secondi.setName("tabella_secondi"); // NOI18N
        jScrollPane1.setViewportView(tabella_secondi);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Secondi.class);
        tabella_secondi.getColumnModel().getColumn(0).setResizable(false);
        tabella_secondi.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabella_secondi.getColumnModel().getColumn(1).setResizable(false);
        tabella_secondi.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tabella_antipasti.columnModel.title0")); // NOI18N
        tabella_secondi.getColumnModel().getColumn(2).setResizable(false);
        tabella_secondi.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tabella_antipasti.columnModel.title1")); // NOI18N

        pannello_secondi.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 0, 5));

        add.setText(resourceMap.getString("add.text")); // NOI18N
        add.setName("add"); // NOI18N
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel1.add(add);

        visuallizza.setText(resourceMap.getString("visuallizza.text")); // NOI18N
        visuallizza.setName("visuallizza"); // NOI18N
        visuallizza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visuallizzaActionPerformed(evt);
            }
        });
        jPanel1.add(visuallizza);

        pannello_secondi.add(jPanel1, java.awt.BorderLayout.SOUTH);

        add(pannello_secondi, java.awt.BorderLayout.NORTH);

        pannello_ordini.setName("pannello_ordini"); // NOI18N
        pannello_ordini.setLayout(new java.awt.BorderLayout(5, 5));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(resourceMap.getString("label_ordini.text")); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setName("label_ordini"); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(225, 13));
        pannello_ordini.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jScrollPane2.setName("jScrollPane2"); // NOI18N
        jScrollPane2.setPreferredSize(new java.awt.Dimension(100, 200));

        tabella_ordini.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Pietanza", "Quantita'", "Elimina"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabella_ordini.setName("tabella_ordini"); // NOI18N
        jScrollPane2.setViewportView(tabella_ordini);
        tabella_ordini.getColumnModel().getColumn(0).setResizable(false);
        tabella_ordini.getColumnModel().getColumn(1).setResizable(false);
        tabella_ordini.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tabella_ordini.columnModel.title0")); // NOI18N
        tabella_ordini.getColumnModel().getColumn(2).setResizable(false);
        tabella_ordini.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tabella_ordini.columnModel.title1")); // NOI18N
        tabella_ordini.getColumnModel().getColumn(3).setResizable(false);

        pannello_ordini.add(jScrollPane2, java.awt.BorderLayout.SOUTH);

        add(pannello_ordini, java.awt.BorderLayout.CENTER);

        bottoni.setName("bottoni"); // NOI18N
        bottoni.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        OK.setText(resourceMap.getString("OK.text")); // NOI18N
        OK.setName("OK"); // NOI18N
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });
        bottoni.add(OK);

        Annulla.setText(resourceMap.getString("Annulla.text")); // NOI18N
        Annulla.setName("Annulla"); // NOI18N
        Annulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnullaActionPerformed(evt);
            }
        });
        bottoni.add(Annulla);

        add(bottoni, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
    Menu menu=new Menu(frame,tavolo);
    frame.setComponent(menu);
}//GEN-LAST:event_OKActionPerformed

private void AnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnullaActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
    Menu menu=new Menu(frame,tavolo);
    frame.setComponent(menu);
}//GEN-LAST:event_AnnullaActionPerformed

private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
// TODO add your handling code here:
    int row=tabella_secondi.getSelectedRow();
    id_antipasto.setColumnVisible(id, true);
    long id_long=((Long)tabella_secondi.getValueAt(row, 0)).longValue();
    System.out.println(id_long);
    id_antipasto.setColumnVisible(id, false);
}//GEN-LAST:event_addActionPerformed

private void visuallizzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visuallizzaActionPerformed
// TODO add your handling code here:
    int row=tabella_secondi.getSelectedRow();
    id_antipasto.setColumnVisible(id, true);
    long id_long=((Long)tabella_secondi.getValueAt(row, 0)).longValue();
    System.out.println("ID pietanza "+id_long);
    id_antipasto.setColumnVisible(id, false);
   this.setVisible(false);
   it.softfood.GUI.Pietanza pietanza = new it.softfood.GUI.Pietanza(frame,tavolo,id_long);
   frame.setComponent(pietanza);  
}//GEN-LAST:event_visuallizzaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annulla;
    private javax.swing.JButton OK;
    private javax.swing.JButton add;
    private javax.swing.JPanel bottoni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pannello_ordini;
    private javax.swing.JPanel pannello_secondi;
    private javax.swing.JTable tabella_ordini;
    private javax.swing.JTable tabella_secondi;
    private javax.swing.JButton visuallizza;
    // End of variables declaration//GEN-END:variables

    private FrameView frame;
    private String tavolo;
    private XTableColumnModel id_antipasto;
    private XTableColumnModel linea_ordine;
    private TableColumn id_ordini;
    private TableColumn id;
}
