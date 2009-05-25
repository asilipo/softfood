package it.softfood.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.User;
import it.softfood.facade.POSOrdinazioneFacade;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Ordini extends javax.swing.JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;	
	private javax.swing.JList jList1;
	private javax.swing.JScrollPane jScrollPane1;
	private User u;
	private POSOrdinazioneFacade ordinazioniFacade;
	private ArrayList<LineaOrdinazione> ordini;
	private Timer timer;
	private MainView frame;

	public Ordini(MainView frame) {
		this.frame = frame;
		initComponents();
		u = frame.getUser();
		ordinazioniFacade = new POSOrdinazioneFacade();

		ordini = ordinazioniFacade.selezionaOrdinazioniGiornaliereNoData(u);

		String[] strings = new String[ordini.size()];
		int i = 0;
		for (LineaOrdinazione lin : ordini) {
			strings[i++] = lin.getArticolo().getNome() + " - " + lin.getId();
		}

		jList1.setListData(strings);

		int delay = 10000;
		timer = new Timer(delay, this);
		frame.setTimer(timer);
		timer.start();
	}

	private void initComponents() {
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();

		setMaximumSize(new java.awt.Dimension(600, 500));
		setMinimumSize(new java.awt.Dimension(600, 500));
		setName("Form");
		setPreferredSize(new java.awt.Dimension(600, 500));
		setLayout(new java.awt.GridLayout(1, 1, 5, 5));

		jScrollPane1.setName("jScrollPane1"); 

		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application
				.getInstance(it.softfood.GUI.Main.class).getContext()
				.getResourceMap(Ordini.class);
		jList1.setFont(resourceMap.getFont("jList1.font")); 
		jList1.setModel(new javax.swing.AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] strings = {};

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jList1
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jList1.setName("jList1");
		jList1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jList1MouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(jList1);

		add(jScrollPane1);
	}

	private void jList1MouseClicked(java.awt.event.MouseEvent evt) {
		String obj = null;
		timer.stop();
		try{
			obj = (String) jList1.getSelectedValue();
			Long id = Long.valueOf(obj.substring(obj.lastIndexOf("-") + 2));
			if (id < 0) {
				frame.getActualPanel().setVisible(false);
				Ordini ord = new Ordini(frame);
				frame.setActualPanel(ord);
				frame.setComponent(ord);
			} else {
				frame.getActualPanel().setVisible(false);
				Ingredienti ingr = new Ingredienti(frame, id);
				frame.setActualPanel(ingr);
				frame.setComponent(ingr);
			}
		}catch(Exception e){
			frame.getActualPanel().setVisible(false);
			Ordini ord = new Ordini(frame);
			frame.setActualPanel(ord);
			frame.setComponent(ord);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.getActualPanel().setVisible(false);
		Ordini ordini = new Ordini(frame);
		frame.setActualPanel(ordini);
		frame.setComponent(ordini);
		timer.stop();
	}

}
