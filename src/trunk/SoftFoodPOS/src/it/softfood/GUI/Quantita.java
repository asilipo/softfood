package it.softfood.GUI;

import it.softfood.entity.IngredientePietanza;
import it.softfood.entity.Pietanza;
import it.softfood.entity.User;
import it.softfood.facade.POSArticoloMenuFacade;

import java.util.HashSet;

import javax.swing.JOptionPane;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Quantita extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private javax.swing.JButton jButton1;
	@SuppressWarnings("unused")
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel[] jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane[] jScrollPane1;
	private javax.swing.JTextArea[] jTextArea1;
	private MainView frame;
	private Object[] ingredienti;
	private POSArticoloMenuFacade articolo;
	private User user;
	private Pietanza pietanza;
	private String tipo;

	public Quantita(MainView frame, Pietanza pietanza, Object[] ingredienti, String tipo) {
		this.frame = frame;
		this.ingredienti = ingredienti;
		this.pietanza=pietanza;
		this.tipo=tipo;
		articolo=new POSArticoloMenuFacade();
		user = frame.getUser();
		initComponents();
	}

	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel[ingredienti.length];
		jScrollPane1 = new javax.swing.JScrollPane[ingredienti.length];
		jTextArea1 = new javax.swing.JTextArea[ingredienti.length];
		jPanel2 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		for (int i = 0; i < ingredienti.length; i++) {
			jLabel1[i] = new javax.swing.JLabel();
			jTextArea1[i] = new javax.swing.JTextArea();
			jScrollPane1[i] = new javax.swing.JScrollPane();
		}

		setMaximumSize(new java.awt.Dimension(600, 500));
		setMinimumSize(new java.awt.Dimension(600, 500));
		setName("Form"); 
		setLayout(new java.awt.BorderLayout());

		jPanel1.setName("jPanel1"); 
		jPanel1.setLayout(new java.awt.GridLayout(ingredienti.length, 2));

		for (int i = 0; i < ingredienti.length; i++) {
			jLabel1[i].setText(((IngredientePietanza) ingredienti[i])
					.getIngrediente().getNome()+" ("+((IngredientePietanza)ingredienti[i]).getIngrediente().getUnitaMisura()+")"); // NOI18N
			jLabel1[i].setName("jLabel" + i); 
			jPanel1.add(jLabel1[i]);

			jScrollPane1[i].setName("jScrollPane" + i);

			jTextArea1[i].setColumns(10);
			jTextArea1[i].setRows(2);
			jTextArea1[i].setName("jTextArea" + i); 
			if(tipo.equalsIgnoreCase("MODIFICA"))
				jTextArea1[i].setText(((Integer)((IngredientePietanza)ingredienti[i]).getQuantita()).toString());
			jScrollPane1[i].setViewportView(jTextArea1[i]);

			jPanel1.add(jScrollPane1[i]);
		}
		add(jPanel1, java.awt.BorderLayout.CENTER);

		jPanel2.setName("jPanel2"); 
		jPanel2.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

		jButton1.setText("OK"); 
		jButton1.setName("jButton1"); 
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton1);

		add(jPanel2, java.awt.BorderLayout.SOUTH);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		int quantita[]=new int[ingredienti.length];
		HashSet<IngredientePietanza> set=new HashSet<IngredientePietanza> ();
		try {
			for(int i = 0; i < quantita.length; i++){
				quantita[i]=Integer.parseInt(jTextArea1[i].getText());
				((IngredientePietanza)ingredienti[i]).setQuantita(quantita[i]);
				set.add((IngredientePietanza) ingredienti[i]);
			}
			
			if(tipo.equalsIgnoreCase("NUOVO"))
				set = articolo.inserisciIngredientiPietanze(user, set);
			else
				articolo.updateIndredientiPietanza(user, set);
			
			pietanza.setIngredientePietanzas(set);
			
			articolo.updatePietanza(user, pietanza);
			
			frame.getActualPanel().setVisible(false);
			Visualizza visualizza = new Visualizza(frame, "Pietanza");
			frame.setActualPanel(visualizza);
			frame.setComponent(visualizza);
		} catch(Exception e){
			JOptionPane.showMessageDialog(frame.getComponent(), "ERRORE NELL'INSERIMENTO DELLE QUANTITA'!","Errore Quantita'",JOptionPane.ERROR_MESSAGE);
			frame.getActualPanel().setVisible(false);
			Quantita quant = new Quantita(frame, pietanza, ingredienti,tipo);
			frame.setActualPanel(quant);
			frame.setComponent(quant);
		}		
	}

}
