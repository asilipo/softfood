package it.softfood.GUI;

import it.softfood.entity.Ordinazione;
import it.softfood.entity.Tavolo;
import it.softfood.entity.User;
import it.softfood.exception.TavoloOccupatoException;
import it.softfood.facade.PDAOrdinazioneFacade;
import it.softfood.facade.PDATavoloFacade;

import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import org.jdesktop.application.FrameView;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Tavoli extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	
	private PDATavoloFacade tavoloFacade;
	private PDAOrdinazioneFacade ordinazioneFacade;
	private ArrayList<Tavolo> tavoli;
	private String[] listaTavoli;
	private int numeroPosti = 0;
	private User role;

	private void initFacade() {
		try {
			tavoloFacade=new PDATavoloFacade();
			ordinazioneFacade=new PDAOrdinazioneFacade();    
		} catch (NullPointerException ex) {
			System.err.println("Errore null pointer");
		}
	}

	public Tavoli(User role,FrameView frame, boolean vuoti) {
		this.frame = frame;
		this.vuoti = vuoti;
		this.role = role;

		initComponents();
		initFacade();

		if (vuoti) {
			SelezionaTavoli.setText(SelezionaTavoli.getText() + " un tavolo vuoto:");
			tavoli = (ArrayList<Tavolo>) tavoloFacade.selezionaTavoliLiberi(role);
		} else {
			SelezionaTavoli.setText(SelezionaTavoli.getText() + " un tavolo:");
			tavoli = (ArrayList<Tavolo>) tavoloFacade.selezionaTavoliOccupati(role);

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
		setName("Form"); 
		setPreferredSize(new java.awt.Dimension(225, 450));
		setLayout(new java.awt.GridLayout(6, 1, 5, 5));

		SelezionaTavoli.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Tavoli.class);
		SelezionaTavoli.setText(resourceMap.getString("SelezionaTavoli.text")); 
		SelezionaTavoli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		SelezionaTavoli.setName("SelezionaTavoli"); 
		add(SelezionaTavoli);

		pannello_tavolo.setName("pannello_tavolo");
		pannello_tavolo.setLayout(new java.awt.BorderLayout());

		jComboBox1.setName("jComboBox1");
		pannello_tavolo.add(jComboBox1, java.awt.BorderLayout.CENTER);

		add.setFont(resourceMap.getFont("add.font")); 
		add.setText(resourceMap.getString("add.text")); 
		add.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		add.setMaximumSize(new java.awt.Dimension(75, 10));
		add.setMinimumSize(new java.awt.Dimension(75, 10));
		add.setName("add"); 
		add.setPreferredSize(new java.awt.Dimension(75, 10));
		add.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addActionPerformed(evt);
			}
		});
		pannello_tavolo.add(add, java.awt.BorderLayout.EAST);

		add(pannello_tavolo);

		pannello_aggiunta.setName("pannello_aggiunta"); 
		pannello_aggiunta.setLayout(new java.awt.GridLayout(1, 1));

		jScrollPane1.setName("jScrollPane1"); 

		jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jList1.setName("jList1"); 
		jScrollPane1.setViewportView(jList1);

		pannello_aggiunta.add(jScrollPane1);

		add(pannello_aggiunta);

		pannello_posti.setName("pannello_posti"); 
		pannello_posti.setLayout(new java.awt.BorderLayout());

		jComboBox2.setName("jComboBox2"); 
		pannello_posti.add(jComboBox2, java.awt.BorderLayout.CENTER);

		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText(resourceMap.getString("jLabel1.text")); 
		jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jLabel1.setName("jLabel1"); 
		pannello_posti.add(jLabel1, java.awt.BorderLayout.NORTH);

		add(pannello_posti);

		jPanel2.setName("jPanel2"); 
		add(jPanel2);

		jPanel1.setName("jPanel1");
		jPanel1.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

		Ok.setText(resourceMap.getString("Ok.text")); 
		Ok.setEnabled(false);
		Ok.setMaximumSize(new java.awt.Dimension(100, 29));
		Ok.setMinimumSize(new java.awt.Dimension(100, 29));
		Ok.setName("Ok"); 
		Ok.setPreferredSize(new java.awt.Dimension(100, 29));
		Ok.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				OkActionPerformed(evt);
			}
		});
		jPanel1.add(Ok);

		Annulla.setText(resourceMap.getString("Annulla.text")); 
		Annulla.setMaximumSize(new java.awt.Dimension(100, 29));
		Annulla.setMinimumSize(new java.awt.Dimension(100, 29));
		Annulla.setName("Annulla"); 
		Annulla.setPreferredSize(new java.awt.Dimension(100, 50));
		Annulla.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				AnnullaActionPerformed(evt);
			}
		});
		jPanel1.add(Annulla);

		add(jPanel1);
	}

	@SuppressWarnings("unchecked")
	private void OkActionPerformed(java.awt.event.ActionEvent evt) {
		Enumeration enumeration = model.elements();
		ArrayList<String> tav = new ArrayList<String>();

		while (enumeration.hasMoreElements()) {
			tav.add((String) enumeration.nextElement());
		}
		
		Ordinazione ordine = null;
		if (vuoti) {
			Long tavoloSelezionato = tavoloFacade.occupaTavoli(role,tav);

			ordine = new Ordinazione();
			ordine.setTavolo(tavoloFacade.selezionaTavolo(role,tavoloSelezionato));
			ordine.setCoperti(Integer.parseInt((String) jComboBox2.getSelectedItem()));
			ordine.setTerminato(false);

			try {
				ordine = ordinazioneFacade.inserisciOrdinazione(role, ordine);
			} catch (NullPointerException e) {
				this.setVisible(false);
				Tavoli pannello_tavoli = new Tavoli(role, frame, vuoti);
				frame.setComponent(pannello_tavoli);
			} catch (TavoloOccupatoException e) {
				JOptionPane.showMessageDialog(frame.getComponent(), "Tavolo occupato!", "Attenzione", JOptionPane.ERROR_MESSAGE);
				this.setVisible(false);
				Tavoli pannello_tavoli = new Tavoli(role, frame, vuoti);
				frame.setComponent(pannello_tavoli);
			}
		} else {
			ordine = ordinazioneFacade.selezionaOrdinazioneGiornalieraPerTavolo(role, ((String)tav.get(0)), false);
		}

		

		if (ordine != null) {
			this.setVisible(false);
			frame.setComponent(new Menu(role, frame, ordine.getId()));
		} else {
			JOptionPane.showMessageDialog(frame.getComponent(), "Ordinazioni del giorno precedente da chiudere!", "Attenzione", JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			ordine = ordinazioneFacade.selezionaOrdinazionePerTavolo(role, ((String)tav.get(0)), false);
			if (ordine != null)
				frame.setComponent(new Menu(role, frame, ordine.getId()));
			else {
				this.setVisible(false);
				frame.setComponent(new Ordine(role, frame));
			}
		}
	}

	private void AnnullaActionPerformed(java.awt.event.ActionEvent evt) {
		this.setVisible(false);

		frame.setComponent(new Ordine(role, frame));
	}

	private void addActionPerformed(java.awt.event.ActionEvent evt) {
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
	}

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
	private FrameView frame;
	private DefaultListModel model;
	private boolean vuoti;
	
}
