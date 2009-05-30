package it.softfood.GUI;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.Pietanza;
import it.softfood.entity.User;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.facade.PDAArticoloMenuFacade;
import it.softfood.facade.PDAOrdinazioneFacade;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.table.TableColumn;

import org.jdesktop.application.FrameView;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Pannello_ordinazioni extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private PDAOrdinazioneFacade ordinazioneFacade;
	private PDAArticoloMenuFacade articolo;
	private User role;

	@SuppressWarnings("unchecked")
	private void initFacade(Hashtable hash) {
		try {
			ordinazioneFacade = new PDAOrdinazioneFacade();
			articolo = new PDAArticoloMenuFacade();
		} catch (Exception e) {
			System.err.println("Errore binding: OrdinazioneFacade e ArticoloMenuFacade");
		}
	}

	public Pannello_ordinazioni(User role,FrameView frame, Long tavolo, String tipo) {
		this.frame = frame;
		this.tavolo = tavolo;
		this.tipo = tipo;
		this.role = role;

		initComponents();
		initFacade(null);

		TipoPietanza tipo_pietanza;
		if (tipo.equalsIgnoreCase("antipasti")) {
			jLabel2.setText(jLabel2.getText() + " antipasto:");
			tipo_pietanza = TipoPietanza.ANTIPASTI;
		} else if (tipo.equalsIgnoreCase("primi")) {
			jLabel2.setText(jLabel2.getText() + " primo:");
			tipo_pietanza = TipoPietanza.PRIMO_PIATTO;
		} else if (tipo.equalsIgnoreCase("secondi")) {
			jLabel2.setText(jLabel2.getText() + " secondo:");
			tipo_pietanza = TipoPietanza.SECONDO_PIATTO;
		} else if (tipo.equalsIgnoreCase("contorni")) {
			jLabel2.setText(jLabel2.getText() + " contorno:");
			tipo_pietanza = TipoPietanza.CONTORNO;
		} else {
			jLabel2.setText(jLabel2.getText() + " dolce:");
			tipo_pietanza = TipoPietanza.DOLCE;
		}

		ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) articolo.selezionaPietanzeDisponibiliPerTipo(role, tipo_pietanza);

		tabella_pietanza.setModel(new javax.swing.table.DefaultTableModel(new String[]{"ID", "Pietanza"}, pietanze.size()){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		id_antipasto = new XTableColumnModel();

		tabella_pietanza.setColumnModel(id_antipasto);
		tabella_pietanza.createDefaultColumnsFromModel();

		id = id_antipasto.getColumnByModelIndex(0);

		int i = 0;
		int j = 0;
		for (it.softfood.entity.Pietanza pietanza : pietanze) {
			tabella_pietanza.setValueAt(pietanza.getId(), i, j++);
			tabella_pietanza.setValueAt(pietanza.getNome(), i++, j);
			j = 0;
		}

		id_antipasto.setColumnVisible(id, false);

		tabella_pietanza.setRowHeight(tabella_pietanza.getRowHeight() * 15 / 10);

		Ordinazione ordine = ordinazioneFacade.selezionaOrdinazionePerId(role,tavolo);

		ArrayList<LineaOrdinazione> linee = (ArrayList<LineaOrdinazione>) ordinazioneFacade.selezionaLineeOrdinazionePerOrdinazioneTipoPietanza(role, ordine, tipo_pietanza);

		tabella_ordini.setModel(new javax.swing.table.DefaultTableModel(new Object [linee.size()][3],new String[]{"ID", "Pietanza","Quantita'"}){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		i = 0;
		j = 0;
		for (LineaOrdinazione linea : linee) {
			tabella_ordini.setValueAt(linea.getId(), i, j++);
			tabella_ordini.setValueAt(linea.getArticolo().getNome(), i, j++);
			tabella_ordini.setValueAt(linea.getQuantita(), i++, j);
			j = 0;
		}

		linea_ordine = new XTableColumnModel();

		tabella_ordini.setColumnModel(linea_ordine);
		tabella_ordini.createDefaultColumnsFromModel();

		id_ordini = linea_ordine.getColumnByModelIndex(0);
		linea_ordine.setColumnVisible(id_ordini, false);

		ToolTipCellRender tool = new ToolTipCellRender(role, ordinazioneFacade, id_ordini, linea_ordine);
		tabella_ordini.setDefaultRenderer(tabella_ordini.getColumnClass(0), tool);

	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		jInternalFrame1 = new javax.swing.JInternalFrame();
		pannello_antipasti = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tabella_pietanza = new javax.swing.JTable();
		jPanel1 = new javax.swing.JPanel();
		visuallizza = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();
		pannello_ordini = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		tabella_ordini = new javax.swing.JTable();
		cancella = new javax.swing.JButton();
		bottoni = new javax.swing.JPanel();
		OK = new javax.swing.JButton();
		Annulla = new javax.swing.JButton();

		jInternalFrame1.setName("jInternalFrame1"); 
		jInternalFrame1.setVisible(true);

		setMaximumSize(new java.awt.Dimension(225, 450));
		setMinimumSize(new java.awt.Dimension(225, 450));
		setName("Form"); 
		setLayout(new java.awt.BorderLayout(5, 0));

		pannello_antipasti.setMaximumSize(new java.awt.Dimension(100, 200));
		pannello_antipasti.setMinimumSize(new java.awt.Dimension(100, 200));
		pannello_antipasti.setName("pannello_antipasti"); 
		pannello_antipasti.setPreferredSize(new java.awt.Dimension(100, 200));
		pannello_antipasti.setLayout(new java.awt.BorderLayout(5, 0));

		jScrollPane1.setName("jScrollPane1"); 
		jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 200));

		tabella_pietanza.setModel(new javax.swing.table.DefaultTableModel(
				new Object [][] {}, new String [] {"ID", "Pietanza"}) {
			private static final long serialVersionUID = 1L;
			Class[] types = new Class [] {
					java.lang.Object.class, java.lang.String.class
			};
			boolean[] canEdit = new boolean [] {
					false, false
			};

			public Class getColumnClass(int columnIndex) {
				return types [columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit [columnIndex];
			}
		});

		tabella_pietanza.setColumnSelectionAllowed(true);
		tabella_pietanza.setFocusable(false);
		tabella_pietanza.setName("tabella_pietanza");
		tabella_pietanza.getTableHeader().setReorderingAllowed(false);
		tabella_pietanza.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tabella_pietanzaMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(tabella_pietanza);
		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Pannello_ordinazioni.class);
		tabella_pietanza.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tabella_pietanza.getColumnModel().getColumn(0).setResizable(false);
		tabella_pietanza.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabella_pietanza.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tabella_pietanza.columnModel.title0")); // NOI18N
		tabella_pietanza.getColumnModel().getColumn(1).setResizable(false);
		tabella_pietanza.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tabella_pietanza.columnModel.title1")); // NOI18N

		pannello_antipasti.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		jPanel1.setName("jPanel1"); 
		jPanel1.setLayout(new java.awt.GridLayout(1, 0));

		visuallizza.setText(resourceMap.getString("visuallizza.text")); 
		visuallizza.setEnabled(false);
		visuallizza.setName("visuallizza");
		visuallizza.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				visuallizzaActionPerformed(evt);
			}
		});
		jPanel1.add(visuallizza);

		pannello_antipasti.add(jPanel1, java.awt.BorderLayout.SOUTH);

		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel2.setText(resourceMap.getString("jLabel2.text")); 
		jLabel2.setName("jLabel2");
		pannello_antipasti.add(jLabel2, java.awt.BorderLayout.PAGE_START);

		add(pannello_antipasti, java.awt.BorderLayout.NORTH);

		pannello_ordini.setName("pannello_ordini"); 
		pannello_ordini.setLayout(new java.awt.BorderLayout(5, 5));

		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText(resourceMap.getString("label_ordini.text")); 
		jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jLabel1.setName("label_ordini"); 
		jLabel1.setPreferredSize(new java.awt.Dimension(225, 13));
		pannello_ordini.add(jLabel1, java.awt.BorderLayout.PAGE_START);

		jScrollPane2.setName("jScrollPane2"); 
		jScrollPane2.setPreferredSize(new java.awt.Dimension(100, 200));

		tabella_ordini.setModel(new javax.swing.table.DefaultTableModel(
				new Object [][] {
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null}
				},
				new String [] {"ID", "Pietanza", "Quantita'"}) {
			private static final long serialVersionUID = 1L;
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
		tabella_ordini.setName("tabella_ordini"); // NOI18N
		tabella_ordini.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tabella_ordiniMouseClicked(evt);
			}
		});
		jScrollPane2.setViewportView(tabella_ordini);
		tabella_ordini.getColumnModel().getColumn(0).setResizable(false);
		tabella_ordini.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tabella_ordini.columnModel.title0")); // NOI18N
		tabella_ordini.getColumnModel().getColumn(1).setResizable(false);
		tabella_ordini.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tabella_ordini.columnModel.title1")); // NOI18N
		tabella_ordini.getColumnModel().getColumn(2).setResizable(false);
		tabella_ordini.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tabella_ordini.columnModel.title2")); // NOI18N

		pannello_ordini.add(jScrollPane2, java.awt.BorderLayout.CENTER);

		cancella.setText(resourceMap.getString("cancella.text"));
		cancella.setEnabled(false);
		cancella.setName("cancella");
		cancella.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancellaActionPerformed(evt);
			}
		});
		pannello_ordini.add(cancella, java.awt.BorderLayout.SOUTH);

		add(pannello_ordini, java.awt.BorderLayout.CENTER);

		bottoni.setName("bottoni"); 
		bottoni.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

		OK.setText(resourceMap.getString("OK.text"));
		OK.setName("OK"); 
		OK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				OKActionPerformed(evt);
			}
		});
		bottoni.add(OK);

		Annulla.setText(resourceMap.getString("Annulla.text"));
		Annulla.setName("Annulla");
		Annulla.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				AnnullaActionPerformed(evt);
			}
		});
		bottoni.add(Annulla);

		add(bottoni, java.awt.BorderLayout.SOUTH);
	}

	private void OKActionPerformed(java.awt.event.ActionEvent evt) {
		this.setVisible(false);
		frame.setComponent(new Menu(role, frame, tavolo));
	}

	private void AnnullaActionPerformed(java.awt.event.ActionEvent evt) {
		this.setVisible(false);
		frame.setComponent(new Menu(role,frame, tavolo));
	}

	private void visuallizzaActionPerformed(java.awt.event.ActionEvent evt) {
		id_antipasto.setColumnVisible(id, true);
		long id_long = ((Long) tabella_pietanza.getValueAt(tabella_pietanza.getSelectedRow(), 0)).longValue();
		id_antipasto.setColumnVisible(id, false);
		this.setVisible(false);
		it.softfood.GUI.Pietanza pietanza = new it.softfood.GUI.Pietanza(role, frame, tavolo, id_long, tipo);
		frame.setComponent(pietanza);
	}

	private void tabella_pietanzaMouseClicked(java.awt.event.MouseEvent evt) {
		visuallizza.setEnabled(true);
	}

	private void tabella_ordiniMouseClicked(java.awt.event.MouseEvent evt) {
		cancella.setEnabled(true);
	}

	private void cancellaActionPerformed(java.awt.event.ActionEvent evt) {
		linea_ordine.setColumnVisible(id_ordini, true);
		Long id = (Long) tabella_ordini.getValueAt(tabella_ordini.getSelectedRow(),0);

		ordinazioneFacade.rimuoviLineaOrdinazione(role, id);
		linea_ordine.setColumnVisible(id_ordini, false);
		this.setVisible(false);
		Pannello_ordinazioni pannello = new Pannello_ordinazioni(role,frame, tavolo, tipo);
		frame.setComponent(pannello);
	}

	private javax.swing.JButton Annulla;
	private javax.swing.JButton OK;
	private javax.swing.JPanel bottoni;
	private javax.swing.JButton cancella;
	private javax.swing.JInternalFrame jInternalFrame1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JPanel pannello_antipasti;
	private javax.swing.JPanel pannello_ordini;
	private javax.swing.JTable tabella_ordini;
	private javax.swing.JTable tabella_pietanza;
	private javax.swing.JButton visuallizza;

	private FrameView frame;
	private Long tavolo;
	private XTableColumnModel id_antipasto;
	private XTableColumnModel linea_ordine;
	private TableColumn id_ordini;
	private TableColumn id;
	private String tipo;

}
