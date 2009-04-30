/*
 * Nuova_pietanza.java
 *
 * Created on 19 aprile 2009, 19.22
 */

package it.softfood.GUI;

import java.util.ArrayList;
import java.util.HashSet;

import it.softfood.entity.Bevanda;
import it.softfood.entity.BevandaMagazzino;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.IngredientePietanza;
import it.softfood.entity.IngredientePietanzaPK;
import it.softfood.entity.Pietanza;
import it.softfood.entity.User;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.facade.POSArticoloMenuFacade;

/**
 * 
 * @author marcograsso
 */
public class Nuova_pietanza extends javax.swing.JPanel {

	private MainView frame;
	private Pietanza pietanza;
	private String tipo;
	private POSArticoloMenuFacade articolo;
	private Object[] ingrediente;
	private Object[] ingredientePietanza;
	private User role;
	private String[] data;
	private String[] ingredientis;

	/** Creates new form Nuova_pietanza */
	public Nuova_pietanza(MainView frame, Pietanza pietanza, String tipo) {
		this.pietanza = pietanza;
		this.frame = frame;
		this.tipo = tipo;
		initComponents();
		role = frame.getUser();
		articolo = new POSArticoloMenuFacade();
		data = new String[0];

		ingrediente = (articolo.selezionaIngredienti(role)).toArray();
		ingredientis = new String[ingrediente.length];
		for (int i = 0; i < ingrediente.length; i++)
			ingredientis[i] = ((Ingrediente) ingrediente[i]).getNome();

		if (tipo.equalsIgnoreCase("MODIFICA")) {
			ingredientePietanza = pietanza.getIngredientePietanzas().toArray();
			data = new String[ingredientePietanza.length];
			for (int i = 0; i < ingredientePietanza.length; i++)
				data[i] = ((IngredientePietanza) ingredientePietanza[i])
						.getIngrediente().getNome();
			jTextField1.setText(pietanza.getNome());
			jTextField2.setText(pietanza.getDescrizione());
			jList1.setListData(ingredientis);
			jList2.setListData(data);

		} else {

			jList1.setListData(ingredientis);
		}

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();
		jPanel7 = new javax.swing.JPanel();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		jList2 = new javax.swing.JList();
		jLabel4 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jPanel2 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		setMaximumSize(new java.awt.Dimension(600, 500));
		setMinimumSize(new java.awt.Dimension(600, 500));
		setName("Form"); // NOI18N
		setPreferredSize(new java.awt.Dimension(600, 500));
		setLayout(new java.awt.BorderLayout());

		jPanel1.setMaximumSize(new java.awt.Dimension(600, 500));
		jPanel1.setMinimumSize(new java.awt.Dimension(600, 500));
		jPanel1.setName("jPanel1"); // NOI18N
		jPanel1.setPreferredSize(new java.awt.Dimension(600, 500));
		jPanel1.setLayout(new java.awt.GridLayout(4, 2, 5, 0));

		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application
				.getInstance(it.softfood.GUI.Main.class).getContext()
				.getResourceMap(Nuova_pietanza.class);
		jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
		jLabel1.setName("jLabel1"); // NOI18N
		jPanel1.add(jLabel1);

		jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
		jTextField1.setName("jTextField1"); // NOI18N
		jPanel1.add(jTextField1);

		jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
		jLabel2.setName("jLabel2"); // NOI18N
		jPanel1.add(jLabel2);

		jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
		jTextField2.setName("jTextField2"); // NOI18N
		jPanel1.add(jTextField2);

		jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
		jLabel3.setName("jLabel3"); // NOI18N
		jPanel1.add(jLabel3);

		jPanel3.setName("jPanel3"); // NOI18N
		jPanel3.setLayout(new java.awt.GridLayout(1, 3));

		jScrollPane1.setName("jScrollPane1"); // NOI18N

		jList1.setName("jList1"); // NOI18N
		jScrollPane1.setViewportView(jList1);

		jPanel3.add(jScrollPane1);

		jPanel7.setName("jPanel7"); // NOI18N
		jPanel7.setLayout(new java.awt.GridLayout(2, 1));

		jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
		jButton3.setName("jButton3"); // NOI18N
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});
		jPanel7.add(jButton3);

		jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
		jButton4.setName("jButton4"); // NOI18N
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});
		jPanel7.add(jButton4);

		jPanel3.add(jPanel7);

		jScrollPane2.setName("jScrollPane2"); // NOI18N

		jList2.setName("jList2"); // NOI18N
		jScrollPane2.setViewportView(jList2);

		jPanel3.add(jScrollPane2);

		jPanel1.add(jPanel3);

		jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
		jLabel4.setName("jLabel4"); // NOI18N
		jLabel4.setText("Tipo pietanza");
		jPanel1.add(jLabel4);

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Antipasto", "Primo", "Secondo", "Contorno", "Dolce" }));
		jComboBox1.setName("jComboBox1"); // NOI18N
		jPanel1.add(jComboBox1);

		add(jPanel1, java.awt.BorderLayout.CENTER);

		jPanel2.setMaximumSize(new java.awt.Dimension(600, 500));
		jPanel2.setMinimumSize(new java.awt.Dimension(600, 500));
		jPanel2.setName("jPanel2"); // NOI18N
		jPanel2.setPreferredSize(new java.awt.Dimension(600, 29));
		jPanel2.setLayout(new java.awt.GridLayout(1, 2, 15, 15));

		jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
		jButton1.setName("jButton1"); // NOI18N
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton1);

		jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
		jButton2.setName("jButton2"); // NOI18N
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton2);

		add(jPanel2, java.awt.BorderLayout.SOUTH);
	}// </editor-fold>

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		String index = "";
		try {
			index = (String) jList1.getSelectedValue();
			String ing[] = new String[data.length + 1];
			int i = 0;
			for (; i < data.length; i++)
				ing[i] = data[i];
			ing[i] = index;
			data = ing;
			jList2.setListData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		try {
			int index = jList2.getSelectedIndex();
			String ing[] = new String[data.length - 1];
			int i = 0;
			int b = 0;
			for (; i < data.length;i++)
				if (i != index)
					ing[b++] = data[i];
			
			data = ing;
			jList2.setListData(data);
		} catch (Exception e) {
			
			data=new String[0];
			jList2.setListData(data);
		}
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		if (tipo.equalsIgnoreCase("NUOVO"))
			pietanza = new Pietanza();
		pietanza.setTipoArticolo("Pietanza");
		String type = (String) jComboBox1.getSelectedItem();
		if (type.equalsIgnoreCase("Antipasto"))
			pietanza.setTipoPietanza(TipoPietanza.ANTIPASTI.ordinal());
		else if (type.equalsIgnoreCase("Primo"))
			pietanza.setTipoPietanza(TipoPietanza.PRIMO_PIATTO.ordinal());
		else if (type.equalsIgnoreCase("Secondo"))
			pietanza.setTipoPietanza(TipoPietanza.SECONDO_PIATTO.ordinal());
		else if (type.equalsIgnoreCase("Contorno"))
			pietanza.setTipoPietanza(TipoPietanza.CONTORNO.ordinal());
		else
			pietanza.setTipoPietanza(TipoPietanza.DOLCE.ordinal());
		pietanza.setNome(jTextField1.getText());
		pietanza.setDescrizione(jTextField2.getText());

		if (tipo.equalsIgnoreCase("NUOVO"))
			pietanza = (Pietanza) articolo
					.inserisciPietanzaMenu(role, pietanza);

		HashSet<IngredientePietanza> set = null;
		
		
		set=new HashSet<IngredientePietanza>();
		IngredientePietanza in=null;
		
		
		for(int i=0;i<data.length;i++){
			in=new IngredientePietanza();
			in.setArticolo(pietanza);
			in.setIngrediente(articolo.selezionaIngredientePerNome(role, data[i]));
			in.setQuantita(0);
			in.setId(new IngredientePietanzaPK(articolo.selezionaIngredientePerNome(role, data[i]).getId(),pietanza.getId()));
			set.add(in);
		}
		
//		pietanza.setIngredientePietanzas(set);
		
		System.out.println("SET "+set.size());

//		articolo.inserisciIngredientePietanza(role, ingrediente)
		
//		articolo.updatePietanza(role, pietanza);

		frame.getActualPanel().setVisible(false);
		Quantita quantita = new Quantita(frame, pietanza, set.toArray(),tipo);
		frame.setActualPanel(quantita);
		frame.setComponent(quantita);
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		frame.getActualPanel().setVisible(false);
		Visualizza visualizza = new Visualizza(frame, "Pietanza");
		frame.setActualPanel(visualizza);
		frame.setComponent(visualizza);
	}
	
	

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JList jList1;
	private javax.swing.JList jList2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JComboBox jComboBox1;
	// End of variables declaration

}
