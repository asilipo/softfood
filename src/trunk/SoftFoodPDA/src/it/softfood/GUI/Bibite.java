package it.softfood.GUI;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.User;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.facade.PDAArticoloMenuFacade;
import it.softfood.facade.PDAOrdinazioneFacade;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.table.TableColumn;

import org.jdesktop.application.FrameView;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Bibite extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private PDAOrdinazioneFacade ordinazioneFacade;
	private PDAArticoloMenuFacade articolo;
	private User role;

	private void initFacade() {
		try {

			articolo = new PDAArticoloMenuFacade();
			ordinazioneFacade = new PDAOrdinazioneFacade();
		} catch (Exception e) {
			System.err.println("Errore binding: ArticoloMenuFacade - OrdinazioneFacade");
		}

	}

	@SuppressWarnings("serial")
	public Bibite(User role,FrameView frame, Long tavolo) {
		this.frame = frame;
		this.tavolo = tavolo;
		this.role = role;

		initComponents();
		initFacade();

		ArrayList<it.softfood.entity.Bevanda> bibite = (ArrayList<it.softfood.entity.Bevanda>) articolo.selezionaBevandeDisponibili(role);

		JComboBox combo = new JComboBox();
		combo.addItem(1);
		combo.addItem(2);
		combo.addItem(3);
		combo.addItem(4);
		combo.addItem(5);

		if (bibite != null) {
			tabella_bibite.setModel(new javax.swing.table.DefaultTableModel(new String[]{"ID", "Bevanda"}, bibite.size()) {

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
		}else{
			tabella_bibite.setModel(new javax.swing.table.DefaultTableModel(new String[]{"ID", "Bevanda"}, 0) {

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
		}
		id_antipasto = new XTableColumnModel();
		tabella_bibite.setColumnModel(id_antipasto);
		tabella_bibite.createDefaultColumnsFromModel();

		id = id_antipasto.getColumnByModelIndex(0);

		int i = 0;
		int j = 0;

		if (bibite != null) {
			for (it.softfood.entity.Bevanda pietanza : bibite) {
				tabella_bibite.setValueAt(pietanza.getId(), i, j++);
				tabella_bibite.setValueAt(pietanza.getNome(), i++, j);
				j = 0;
			}
		}

		id_antipasto.setColumnVisible(id, false);

		tabella_bibite.setRowHeight(tabella_bibite.getRowHeight() * 15 / 10);

		Ordinazione ordine = ordinazioneFacade.selezionaOrdinazionePerId(role, tavolo);

		ArrayList<LineaOrdinazione> linee = (ArrayList<LineaOrdinazione>) ordinazioneFacade.selezionaLineeOrdinazionePerOrdinazioneTipoPietanza(role, ordine, TipoPietanza.BEVANDA);

		if (linee != null && linee.size() > 0)
			tabella_ordini.setModel(new javax.swing.table.DefaultTableModel(new Object[linee.size()][3], new String[]{"ID", "Pietanza", "Quantita'"}) {

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
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		pannello_bibite = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tabella_bibite = new javax.swing.JTable();
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

		setMaximumSize(new java.awt.Dimension(225, 450));
		setMinimumSize(new java.awt.Dimension(225, 450));
		setName("Form"); 
		setLayout(new java.awt.BorderLayout(5, 0));

		pannello_bibite.setMaximumSize(new java.awt.Dimension(100, 200));
		pannello_bibite.setMinimumSize(new java.awt.Dimension(100, 200));
		pannello_bibite.setName("pannello_bibite"); 
		pannello_bibite.setPreferredSize(new java.awt.Dimension(100, 200));
		pannello_bibite.setLayout(new java.awt.BorderLayout(5, 0));

		jScrollPane1.setName("jScrollPane1"); // NOI18N
		jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 200));

		tabella_bibite.setModel(new javax.swing.table.DefaultTableModel(
				new Object [][] {}, new String [] {"ID", "Bevanda"}) {
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

		tabella_bibite.setFocusable(false);
		tabella_bibite.setName("tabella_bibite"); // NOI18N
		tabella_bibite.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tabella_bibiteMouseClicked(evt);
			}
		});

		jScrollPane1.setViewportView(tabella_bibite);
		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Bibite.class);
		tabella_bibite.getColumnModel().getColumn(0).setResizable(false);
		tabella_bibite.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabella_bibite.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tabella_bibite.columnModel.title0")); // NOI18N
		tabella_bibite.getColumnModel().getColumn(1).setResizable(false);
		tabella_bibite.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tabella_bibite.columnModel.title1")); // NOI18N

		pannello_bibite.add(jScrollPane1, java.awt.BorderLayout.CENTER);

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

		pannello_bibite.add(jPanel1, java.awt.BorderLayout.SOUTH);

		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
		jLabel2.setName("jLabel2"); // NOI18N
		pannello_bibite.add(jLabel2, java.awt.BorderLayout.PAGE_START);

		add(pannello_bibite, java.awt.BorderLayout.NORTH);

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
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null}
				},
				new String [] {
						"ID", "Pietanza", "Quantita'"
				}
		) {
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

		tabella_ordini.setName("tabella_ordini");
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
		Menu menu = new Menu(role,frame, tavolo);
		frame.setComponent(menu);
	}

	private void AnnullaActionPerformed(java.awt.event.ActionEvent evt) {
		this.setVisible(false);
		Menu menu = new Menu(role,frame, tavolo);
		frame.setComponent(menu);
	}

	private void visuallizzaActionPerformed(java.awt.event.ActionEvent evt) {
		int row = tabella_bibite.getSelectedRow();
		id_antipasto.setColumnVisible(id, true);
		long id_long = ((Long) tabella_bibite.getValueAt(row, 0)).longValue();
		id_antipasto.setColumnVisible(id, false);
		this.setVisible(false);
		it.softfood.GUI.Pietanza pietanza = new it.softfood.GUI.Pietanza(role,frame, tavolo, id_long, "bibite");
		frame.setComponent(pietanza);
	}

	private void tabella_bibiteMouseClicked(java.awt.event.MouseEvent evt) {
		visuallizza.setEnabled(true);
	}

	private void cancellaActionPerformed(java.awt.event.ActionEvent evt) {
		linea_ordine.setColumnVisible(id_ordini, true);
		Long id = (Long) tabella_ordini.getValueAt(tabella_ordini.getSelectedRow(), 0);
		ordinazioneFacade.rimuoviLineaOrdinazione(role,id);
		linea_ordine.setColumnVisible(id_ordini, false);
		this.setVisible(false);
		Bibite pannello = new Bibite(role,frame, tavolo);
		frame.setComponent(pannello);
	}

	private void tabella_ordiniMouseClicked(java.awt.event.MouseEvent evt) {
		cancella.setEnabled(true);
	}

	private javax.swing.JButton Annulla;
	private javax.swing.JButton OK;
	private javax.swing.JPanel bottoni;
	private javax.swing.JButton cancella;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JPanel pannello_bibite;
	private javax.swing.JPanel pannello_ordini;
	private javax.swing.JTable tabella_bibite;
	private javax.swing.JTable tabella_ordini;
	private javax.swing.JButton visuallizza;

	private FrameView frame;
	private Long tavolo;
	private XTableColumnModel id_antipasto;
	private XTableColumnModel linea_ordine;
	private TableColumn id_ordini;
	private TableColumn id;
	
}

