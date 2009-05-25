package it.softfood.GUI;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.User;
import it.softfood.entity.Variante;
import it.softfood.enumeration.TipoVariante;
import it.softfood.facade.POSArticoloMenuFacade;
import it.softfood.facade.POSOrdinazioneFacade;

import java.util.ArrayList;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Ingredienti extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private MainView frame;
	@SuppressWarnings("unused")
	private Long id;
	private POSArticoloMenuFacade articoloFacade;
	private POSOrdinazioneFacade ordiniFacade;
	private User u;
	private LineaOrdinazione linea;

	public Ingredienti(MainView frame, Long id) {
		this.id = id;
		this.frame = frame;
		initComponents();

		u = frame.getUser();

		articoloFacade = new POSArticoloMenuFacade();
		ordiniFacade = new POSOrdinazioneFacade();

		linea = ordiniFacade.selezionaLineaOrdinazionePerId(u, id);        
		@SuppressWarnings("unused")
		String ingredienti[] = null;

		ArrayList<Ingrediente> ingr = articoloFacade.selezionaIngredientiPietanza(u, linea.getArticolo().getId());     
		ArrayList<Variante> var = ordiniFacade.selezionaVariantiPerLineaOrdinazione(u, linea);

		int ingr_size = 0;
		int var_size = 0;

		try{
			ingr_size = ingr.size();
		}catch(Exception e){
			ingr_size = 0;
		}

		try{
			var_size = var.size();
		}catch(Exception e){
			var_size=0;
		}

		String data[] = new String[ingr_size+var_size+10];

		int i = 0;
		data[i++] = "-----------------------------------------";
		data[i++] = "-             INGREDIENTI               -";
		data[i++] = "-----------------------------------------";

		for(int is = 0; is < ingr_size; is++)
			data[i++]=ingr.get(is).getNome();

		data[i++] = "-----------------------------------------";
		data[i++] = "-                VARIANTI               -";
		data[i++] = "-----------------------------------------";

		for(int is = 0; is < var_size; is++)
			data[i++]=TipoVariante.values()[var.get(is).getTipoVariazione()]+" - "+var.get(is).getIngrediente().getNome();

		data[i++] = "-----------------------------------------";
		data[i++] = "-                QUANTITA               -";
		data[i++] = "-----------------------------------------";
		
		data[i++] = ""+linea.getQuantita();
		
		jList1.setListData(data);
	}

	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();
		jPanel2 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		setMaximumSize(new java.awt.Dimension(600, 500));
		setMinimumSize(new java.awt.Dimension(600, 500));
		setName("Form"); 
		setPreferredSize(new java.awt.Dimension(600, 500));
		setLayout(new java.awt.BorderLayout());

		jPanel1.setName("jPanel1"); 
		jPanel1.setLayout(new java.awt.GridLayout());

		jScrollPane1.setName("jScrollPane1");

		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Ingredienti.class);
		jList1.setFont(resourceMap.getFont("jList1.font"));
		jList1.setModel(new javax.swing.AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] strings = { };
			public int getSize() { return strings.length; }
			public Object getElementAt(int i) { return strings[i]; }
		});
		jList1.setName("jList1");
		jScrollPane1.setViewportView(jList1);

		jPanel1.add(jScrollPane1);

		add(jPanel1, java.awt.BorderLayout.CENTER);

		jPanel2.setMaximumSize(new java.awt.Dimension(600, 100));
		jPanel2.setMinimumSize(new java.awt.Dimension(600, 100));
		jPanel2.setName("jPanel2"); 
		jPanel2.setPreferredSize(new java.awt.Dimension(600, 100));
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

		add(jPanel2, java.awt.BorderLayout.SOUTH);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		frame.getActualPanel().setVisible(false);
		Ordini ordini=new Ordini(frame);
		frame.setActualPanel(ordini);
		frame.setComponent(ordini);
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		linea.setEvaso(true);
		ordiniFacade.setLineaEvasa(u, linea);
		frame.getActualPanel().setVisible(false);
		Ordini ordini=new Ordini(frame);
		frame.setActualPanel(ordini);
		frame.setComponent(ordini);
	}

	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JList jList1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;

}
