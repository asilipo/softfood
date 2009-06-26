package it.softfood.GUI;

import java.util.HashSet;

import javax.swing.JOptionPane;

import it.softfood.entity.Bevanda;
import it.softfood.entity.BevandaMagazzino;
import it.softfood.entity.User;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.facade.POSArticoloMenuFacade;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Nuova_bevanda extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private MainView frame;
	private Bevanda bevanda;
	private String tipo;
	private POSArticoloMenuFacade articolofacade;
	private User role;

	public Nuova_bevanda(MainView frame, Bevanda bevanda, String tipo) {
		this.bevanda = bevanda;
		this.frame = frame;
		this.tipo = tipo;
		initComponents();
		role = frame.getUser();
		articolofacade = new POSArticoloMenuFacade();

		jLabel3.setText(jLabel3.getText() + " (ml)");
		jLabel4.setText(jLabel4.getText() + " (ml)");

		if(tipo.equalsIgnoreCase("MODIFICA")){
			jTextField1.setText(bevanda.getNome());
			jTextArea1.setText(bevanda.getDescrizione());
			jTextField2.setText(bevanda.getCapacita().toString());
			try{
				jTextField3.setText(new Integer(((BevandaMagazzino)bevanda.getBevandaMagazzinos().toArray()[0]).getQuantita()).toString());
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
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jTextField3 = new javax.swing.JTextField();
		jPanel2 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		setMaximumSize(new java.awt.Dimension(600, 500));
		setMinimumSize(new java.awt.Dimension(600, 500));
		setName("Form"); 
		setPreferredSize(new java.awt.Dimension(600, 500));
		setLayout(new java.awt.BorderLayout());

		jPanel1.setName("jPanel1"); 
		jPanel1.setLayout(new java.awt.GridLayout(4, 2, 20, 5));

		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Nuova_bevanda.class);
		jLabel1.setText(resourceMap.getString("jLabel1.text")); 
		jLabel1.setName("jLabel1"); 
		jPanel1.add(jLabel1);

		jTextField1.setText(resourceMap.getString("jTextField1.text")); 
		jTextField1.setName("jTextField1");
		jPanel1.add(jTextField1);

		jLabel2.setText(resourceMap.getString("jLabel2.text"));
		jLabel2.setName("jLabel2");
		jPanel1.add(jLabel2);

		jScrollPane1.setName("jScrollPane1"); 

		jTextArea1.setColumns(20);
		jTextArea1.setAutoscrolls(false);
		jTextArea1.setName("jTextArea1"); 
		jScrollPane1.setViewportView(jTextArea1);

		jPanel1.add(jScrollPane1);

		jLabel3.setText(resourceMap.getString("jLabel3.text")); 
		jLabel3.setName("jLabel3"); 
		jPanel1.add(jLabel3);

		jTextField2.setText(resourceMap.getString("jTextField2.text")); 
		jTextField2.setName("jTextField2"); 
		jPanel1.add(jTextField2);

		jLabel4.setText(resourceMap.getString("jLabel4.text")); 
		jLabel4.setName("jLabel4"); 
		jPanel1.add(jLabel4);

		jTextField3.setText(resourceMap.getString("jTextField3.text")); 
		jTextField3.setName("jTextField3");
		jPanel1.add(jTextField3);

		add(jPanel1, java.awt.BorderLayout.CENTER);

		jPanel2.setName("jPanel2"); 
		jPanel2.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

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
		Boolean bm = false;
		if(tipo.equalsIgnoreCase("NUOVO"))
			bevanda = new Bevanda();
		try {
			bevanda.setId(null);
			bevanda.setTipoArticolo("Bevanda");
			bevanda.setTipoPietanza(TipoPietanza.BEVANDA.ordinal());
			if (jTextField1.getText() == null || jTextField1.getText().equals(""))
				throw new NullPointerException();
			bevanda.setNome(jTextField1.getText());
			bevanda.setCapacita(new Float(jTextField2.getText()));
			bevanda.setDescrizione(jTextArea1.getText());
	
			if(tipo.equalsIgnoreCase("NUOVO"))
				bevanda = (Bevanda) articolofacade.inserisciBevandaMenu(role, bevanda);
	
			HashSet<BevandaMagazzino> set = new HashSet<BevandaMagazzino>();
	
			BevandaMagazzino bevandaMagazzino = null;
			try{
				bevandaMagazzino = (BevandaMagazzino) bevanda.getBevandaMagazzinos().toArray()[0];
				System.out.println(jTextField3.getText());
				bevandaMagazzino.setQuantita(new Integer(jTextField3.getText()));
				articolofacade.updateBevandaMagazzino(role, bevandaMagazzino);
			} catch(Exception e) {
				bm = true;
				bevandaMagazzino = articolofacade.inserisciBevandaMagazzino(role, bevanda.getId(), new Long(1), new Integer(jTextField3.getText()));
			}
			set.add(bevandaMagazzino);
			bevanda.setBevandaMagazzinos(set);
	
			articolofacade.updateBevanda(role, bevanda);
	
			frame.getActualPanel().setVisible(false);
			Visualizza visualizza=new Visualizza(frame,"Bevanda");
			frame.setActualPanel(visualizza);
			frame.setComponent(visualizza);
		}catch(NullPointerException npe){
			JOptionPane.showMessageDialog(frame.getComponent(), "E'necessario inserire il nome!","Errore Dati Immessi", JOptionPane.ERROR_MESSAGE);
			frame.getActualPanel().setVisible(false);
			Nuova_bevanda nuova = new Nuova_bevanda(frame, bevanda, tipo);
			frame.setActualPanel(nuova);
			frame.setComponent(nuova);	
		} catch (NumberFormatException nfe) {
			if (bm) 
				JOptionPane.showMessageDialog(frame.getComponent(), "Quantità non corretta!","Errore Quantità", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(frame.getComponent(), "Capacità non corretta!","Errore Capacità", JOptionPane.ERROR_MESSAGE);
			
			if (bevanda.getId() != null)
				articolofacade.rimuoviBevandaMenu(role, bevanda.getId());
			frame.getActualPanel().setVisible(false);
			Nuova_bevanda nuova = new Nuova_bevanda(frame, bevanda, tipo);
			frame.setActualPanel(nuova);
			frame.setComponent(nuova);
		} 
	}                                        

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		frame.getActualPanel().setVisible(false);
		Visualizza visualizza=new Visualizza(frame,"Bevanda");
		frame.setActualPanel(visualizza);
		frame.setComponent(visualizza);
	}                                        
              
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField jTextArea1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;                

}

