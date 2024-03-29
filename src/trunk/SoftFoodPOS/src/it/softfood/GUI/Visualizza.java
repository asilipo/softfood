package it.softfood.GUI;

import it.softfood.facade.POSArticoloMenuFacade;
import it.softfood.facade.POSOrdinazioneFacade;
import it.softfood.entity.Bevanda;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.Pietanza;
import it.softfood.entity.User;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Visualizza extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private MainView frame;
	private String tipo;
	@SuppressWarnings("unused")
	private POSOrdinazioneFacade ordinazionefacade;
	private POSArticoloMenuFacade articolofacade;
	private User role;
	private ArrayList<Pietanza> pietanze;
	private ArrayList<Bevanda> bevande;
	private ArrayList<Ingrediente> ingredienti;

	public Visualizza(MainView frame, String tipo) {
		this.frame = frame;
		this.tipo = tipo;
		initComponents();
		ordinazionefacade = new POSOrdinazioneFacade();
		articolofacade = new POSArticoloMenuFacade();
		role = frame.getUser();
		String data[] = null;

		int i = 0;
		if (tipo.equalsIgnoreCase("Pietanza")) {
			pietanze = articolofacade.selezionaPietanze(role);
			data = new String[pietanze.size()];
			for (Pietanza pietanza : pietanze)
				data[i++] = pietanza.getNome() + " - " + pietanza.getId();
		} else if (tipo.equalsIgnoreCase("Bevanda")) {
			bevande = articolofacade.selezionaBevande(role);
			data = new String[bevande.size()];
			for (Bevanda bevanda : bevande)
				data[i++] = bevanda.getNome() + " - " + bevanda.getId();
		} else {
			ingredienti = articolofacade.selezionaIngredienti(role);
			data = new String[ingredienti.size()];
			for (Ingrediente ingrediente : ingredienti)
				data[i++] = ingrediente.getNome() + " - " + ingrediente.getId();
		}

		jList1.setListData(data);
	}

	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();
		jPanel2 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();

		setMaximumSize(new java.awt.Dimension(600, 500));
		setMinimumSize(new java.awt.Dimension(600, 500));
		setName("Form"); 
		setPreferredSize(new java.awt.Dimension(600, 500));
		setLayout(new java.awt.BorderLayout());

		jPanel1.setName("jPanel1"); 
		jPanel1.setLayout(new java.awt.GridLayout(1, 1, 10, 10));

		jScrollPane1.setName("jScrollPane1");

		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application
				.getInstance(it.softfood.GUI.Main.class).getContext()
				.getResourceMap(Visualizza.class);
		jList1.setFont(resourceMap.getFont("jList1.font"));
		jList1.setName("jList1");
		jList1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jList1MouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(jList1);

		jPanel1.add(jScrollPane1);

		add(jPanel1, java.awt.BorderLayout.CENTER);

		jPanel2.setName("jPanel2");
		jPanel2.setLayout(new java.awt.GridLayout(1, 3, 5, 5));

		jButton1.setText(resourceMap.getString("jButton1.text"));
		jButton1.setName("jButton1");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton1);

		jButton2.setText(resourceMap.getString("jButton2.text")); 
		jButton2.setName("jButton2"); 
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton2);

		jButton3.setText(resourceMap.getString("jButton3.text")); 
		jButton3.setName("jButton3"); 
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton3);

		jButton2.setEnabled(false);
		jButton3.setEnabled(false);

		add(jPanel2, java.awt.BorderLayout.SOUTH);
	}

	private void jList1MouseClicked(java.awt.event.MouseEvent evt) {
		jButton2.setEnabled(true);
		jButton3.setEnabled(true);
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		String obj = (String) jList1.getSelectedValue();
		Long id = Long.valueOf(obj.substring(obj.lastIndexOf("-") + 2));

		int n = JOptionPane.showConfirmDialog(frame.getComponent(),
				"Sei sicuro di voler cancellare l'articolo selezionato?",
				"Rimozione", JOptionPane.YES_NO_OPTION);

		if (n == JOptionPane.YES_OPTION) {
			try {
				if (tipo.equalsIgnoreCase("Pietanza")) {
					if (!articolofacade.rimuoviPietanzaMenu(role, id)) {
						JOptionPane
								.showMessageDialog(
										frame.getComponent(),
										"Cancellazione non eseguita - pietanza presente in ordinazioni!",
										"Violazione vincoli",
										JOptionPane.ERROR_MESSAGE);
					}
				} else if (tipo.equalsIgnoreCase("Bevanda")) {
					if (!articolofacade.rimuoviBevandaMenu(role, id))
						JOptionPane
								.showMessageDialog(
										frame.getComponent(),
										"Cancellazione non eseguita - bevanda presente in ordinazioni!",
										"Violazione vincoli",
										JOptionPane.ERROR_MESSAGE);
				} else {
					if(!articolofacade.rimuoviIngrediente(role, id))
						JOptionPane
						.showMessageDialog(
								frame.getComponent(),
								"Cancellazione non eseguita - ingrediente presente in pietanze!",
								"Violazione vincoli",
								JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
			}
		}

		frame.getActualPanel().setVisible(false);
		Gestione gestione = new Gestione(frame);
		frame.setActualPanel(gestione);
		frame.setComponent(gestione);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		frame.getActualPanel().setVisible(false);
		if (tipo.equalsIgnoreCase("Pietanza")) {
			Nuova_pietanza pietanza = new Nuova_pietanza(frame, new Pietanza(),
					"NUOVO");
			frame.setActualPanel(pietanza);
			frame.setComponent(pietanza);
		} else if (tipo.equalsIgnoreCase("Bevanda")) {
			Nuova_bevanda bevanda = new Nuova_bevanda(frame, new Bevanda(),
					"NUOVO");
			frame.setActualPanel(bevanda);
			frame.setComponent(bevanda);
		} else {
			Nuovo_ingrediente ingrediente = new Nuovo_ingrediente(frame,
					new Ingrediente(), "NUOVO");
			frame.setActualPanel(ingrediente);
			frame.setComponent(ingrediente);
		}
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		String obj = (String) jList1.getSelectedValue();
		Long.valueOf(obj.substring(obj.lastIndexOf("-") + 2));

		frame.getActualPanel().setVisible(false);

		if (tipo.equalsIgnoreCase("Pietanza")) {
			Nuova_pietanza pietanza = new Nuova_pietanza(frame, pietanze
					.get(jList1.getSelectedIndex()), "MODIFICA");
			frame.setActualPanel(pietanza);
			frame.setComponent(pietanza);
		} else if (tipo.equalsIgnoreCase("Bevanda")) {
			Nuova_bevanda bevanda = new Nuova_bevanda(frame, bevande.get(jList1
					.getSelectedIndex()), "MODIFICA");
			frame.setActualPanel(bevanda);
			frame.setComponent(bevanda);
		} else {
			Nuovo_ingrediente ingrediente = new Nuovo_ingrediente(frame,
					ingredienti.get(jList1.getSelectedIndex()), "MODIFICA");
			frame.setActualPanel(ingrediente);
			frame.setComponent(ingrediente);
		}
	}

	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JList jList1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;

}
