package it.softfood.GUI;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.IngredienteMagazzino;
import it.softfood.entity.User;
import it.softfood.facade.POSArticoloMenuFacade;

import java.util.Date;
import java.util.HashSet;

import javax.swing.JOptionPane;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Nuovo_ingrediente extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private MainView frame;
	private Ingrediente ingrediente;
	private String tipo;
	private POSArticoloMenuFacade articolofacade;
	private User role;

	@SuppressWarnings("deprecation")
	public Nuovo_ingrediente(MainView frame, Ingrediente ingrediente, String tipo) {
		this.ingrediente=ingrediente;
		this.frame=frame;
		this.tipo=tipo;
		initComponents();
		role=frame.getUser();
		articolofacade=new POSArticoloMenuFacade();

		if(tipo.equalsIgnoreCase("MODIFICA")){
			jTextField1.setText(ingrediente.getNome());
			jTextField2.setText(ingrediente.getDescrizione());
			Date date=ingrediente.getScadenza();
			giorno.setSelectedIndex(date.getDate()-1);
			mese.setSelectedIndex(date.getMonth());
			anno.setSelectedIndex(date.getYear()-109);
			String t=ingrediente.getTipoIngrediente();
			if(!t.equalsIgnoreCase("IngredienteLungaConservazione"))
				jComboBox1.setSelectedIndex(1);
			String unita=ingrediente.getUnitaMisura();
			if(unita.equalsIgnoreCase("ml"))
				jComboBox2.setSelectedIndex(0);
			else if(unita.equalsIgnoreCase("l"))
				jComboBox2.setSelectedIndex(1);
			else if(unita.equalsIgnoreCase("g"))
				jComboBox2.setSelectedIndex(2);
			else if(unita.equalsIgnoreCase("kg"))
				jComboBox2.setSelectedIndex(3);
			else
				jComboBox2.setSelectedIndex(4);
			if(!ingrediente.isVariante())
				jComboBox3.setSelectedIndex(1);
			else 
				jComboBox3.setSelectedIndex(0);
			try{
				jTextField3.setText(new Integer(((IngredienteMagazzino)ingrediente.getIngredienteMagazzinos().toArray()[0]).getQuantita()).toString());
			}catch(Exception e){
				jTextField3.setText("0");
			}
		}
	}

	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		giorno = new javax.swing.JComboBox();
		mese = new javax.swing.JComboBox();
		anno = new javax.swing.JComboBox();
		jLabel4 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jLabel5 = new javax.swing.JLabel();
		jComboBox2 = new javax.swing.JComboBox();
		jLabel6 = new javax.swing.JLabel();
		jComboBox3 = new javax.swing.JComboBox();
		jLabel7 = new javax.swing.JLabel();
		jTextField3 = new javax.swing.JTextField();
		jPanel2 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		setMaximumSize(new java.awt.Dimension(600, 500));
		setMinimumSize(new java.awt.Dimension(600, 500));
		setName("Form"); 
		setPreferredSize(new java.awt.Dimension(600, 500));
		setLayout(new java.awt.BorderLayout());

		jPanel1.setMaximumSize(new java.awt.Dimension(600, 500));
		jPanel1.setMinimumSize(new java.awt.Dimension(600, 500));
		jPanel1.setName("jPanel1"); 
		jPanel1.setPreferredSize(new java.awt.Dimension(600, 500));
		jPanel1.setLayout(new java.awt.GridLayout(7, 2, 15, 15));

		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Nuovo_ingrediente.class);
		jLabel1.setText(resourceMap.getString("jLabel1.text")); 
		jLabel1.setName("jLabel1"); 
		jLabel1.setText("Nome");
		jPanel1.add(jLabel1);

		jTextField1.setText(resourceMap.getString("jTextField1.text"));
		jTextField1.setName("jTextField1"); 
		jPanel1.add(jTextField1);

		jLabel2.setText(resourceMap.getString("jLabel2.text")); 
		jLabel2.setName("jLabel2"); 
		jLabel2.setText("Descrizione");
		jPanel1.add(jLabel2);

		jTextField2.setText(resourceMap.getString("jTextField2.text")); 
		jTextField2.setName("jTextField2"); 
		jPanel1.add(jTextField2);

		jLabel3.setText(resourceMap.getString("jLabel3.text")); 
		jLabel3.setName("jLabel3");
		jLabel3.setText("Data di scadenza");
		jPanel1.add(jLabel3);

		jPanel3.setName("jPanel3"); 
		jPanel3.setLayout(new java.awt.GridLayout(1, 3, 5, 5));

		giorno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
		giorno.setName("giorno"); 
		jPanel3.add(giorno);

		mese.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gen", "Feb", "Mar", "Apr", "Mag", "Giu", "Lug", "Ago", "Set", "Ott", "Nov", "Dic" }));
		mese.setName("mese");
		jPanel3.add(mese);

		anno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));
		anno.setName("anno"); 
		jPanel3.add(anno);

		jPanel1.add(jPanel3);

		jLabel4.setText(resourceMap.getString("jLabel4.text"));
		jLabel4.setName("jLabel4"); 
		jLabel4.setText("Tipo");
		jPanel1.add(jLabel4);

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lunga Coservazione", "Prodotto Fresco" }));
		jComboBox1.setName("jComboBox1"); 
		jComboBox1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox1ActionPerformed(evt);
			}
		});
		jPanel1.add(jComboBox1);

		jLabel5.setText(resourceMap.getString("jLabel5.text"));
		jLabel5.setName("jLabel5"); 
		jLabel5.setText("Unit� di misura");
		jPanel1.add(jLabel5);

		jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ml", "l", "g", "kg", "pz" }));
		jComboBox2.setName("jComboBox2");
		jPanel1.add(jComboBox2);

		jLabel6.setText(resourceMap.getString("jLabel6.text")); 
		jLabel6.setName("jLabel6"); 
		jLabel6.setText("Variante");
		jPanel1.add(jLabel6);

		jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));
		jComboBox3.setName("jComboBox3");
		jPanel1.add(jComboBox3);

		jLabel7.setText(resourceMap.getString("jLabel7.text"));
		jLabel7.setName("jLabel7"); 
		jLabel7.setText("Quantit�");
		jPanel1.add(jLabel7);

		jTextField3.setText(resourceMap.getString("jTextField3.text")); 
		jTextField3.setName("jTextField3");
		jPanel1.add(jTextField3);

		add(jPanel1, java.awt.BorderLayout.CENTER);

		jPanel2.setName("jPanel2"); 
		jPanel2.setLayout(new java.awt.GridLayout(1, 2, 20, 20));

		jButton1.setText("OK"); 
		jButton1.setName("jButton1");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton1);

		jButton2.setText("Annulla"); 
		jButton2.setName("jButton2"); 
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton2);

		add(jPanel2, java.awt.BorderLayout.SOUTH);
	}

	@SuppressWarnings("deprecation")
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {  
		try {
			if(tipo.equalsIgnoreCase("NUOVO"))
				ingrediente = new Ingrediente();
			if (jTextField1.getText() == null || jTextField1.getText().equals("")) 
				throw new NullPointerException();
			ingrediente.setNome(jTextField1.getText());
			ingrediente.setDescrizione(jTextField2.getText());
			if (jComboBox3.getSelectedIndex() == 0) 
				ingrediente.setVariante(true);
			else
				ingrediente.setVariante(false);
			int d = giorno.getSelectedIndex()+1;
			int m = mese.getSelectedIndex();
			int y = anno.getSelectedIndex()+109;
			Date date = new Date(y,m,d);
			ingrediente.setScadenza(date);
			if(jComboBox1.getSelectedIndex() == 0)
				ingrediente.setTipoIngrediente("IngredienteLungaConservazione");
			else
				ingrediente.setTipoIngrediente("IngredienteFresco");
	
			ingrediente.setUnitaMisura((String) jComboBox2.getSelectedItem());
	
			if(tipo.equalsIgnoreCase("NUOVO"))
				ingrediente = (Ingrediente) articolofacade.inserisciIngrediente(role, ingrediente);
	
			HashSet<IngredienteMagazzino> set=new HashSet<IngredienteMagazzino>();
	
			boolean state = true;
			IngredienteMagazzino ingredienteMagazzino = null;
			try{
				ingredienteMagazzino = (IngredienteMagazzino) ingrediente.getIngredienteMagazzinos().toArray()[0];
				ingredienteMagazzino.setQuantita(new Integer(jTextField3.getText()));
				articolofacade.updateIngredienteMagazzino(role, ingredienteMagazzino);
			} catch(Exception e) {
				state = false;
				try {
					ingredienteMagazzino = articolofacade.inserisciIngredienteMagazzino(role, ingrediente.getId(), new Integer(jTextField3.getText()));
					frame.getActualPanel().setVisible(false);
					Visualizza visualizza = new Visualizza(frame,"Ingrediente");
					frame.setActualPanel(visualizza);
					frame.setComponent(visualizza);
				} catch (Exception npe) {
					JOptionPane.showMessageDialog(frame.getComponent(), "Quantita' non inserita correttamente e settata di default!","Errore Quantita'",JOptionPane.ERROR_MESSAGE);
					frame.getActualPanel().setVisible(false);
					Visualizza visualizza = new Visualizza(frame,"Ingrediente");
					frame.setActualPanel(visualizza);
					frame.setComponent(visualizza);
				}
			}
			if (state) {
				set.add(ingredienteMagazzino);
				ingrediente.setIngredienteMagazzinos(set);
		
				articolofacade.updateIngrediente(role, ingrediente);
		
				frame.getActualPanel().setVisible(false);
				Visualizza visualizza = new Visualizza(frame,"Ingrediente");
				frame.setActualPanel(visualizza);
				frame.setComponent(visualizza);
			}
		} catch (NullPointerException npe) {
			JOptionPane.showMessageDialog(frame.getComponent(), "Inserire i dati in modo corretto!","Errore Quantita'",JOptionPane.ERROR_MESSAGE);
			frame.getActualPanel().setVisible(false);
			Nuovo_ingrediente ingr = new Nuovo_ingrediente(frame, new Ingrediente(), "NUOVO");
			frame.setActualPanel(ingr);
			frame.setComponent(ingr);
		}
	}                                        

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		frame.getActualPanel().setVisible(false);
		Visualizza visualizza = new Visualizza(frame,"Ingrediente");
		frame.setActualPanel(visualizza);
		frame.setComponent(visualizza);
	}                                        

	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {}

	private javax.swing.JComboBox anno;
	private javax.swing.JComboBox giorno;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JComboBox mese;

}

