package it.softfood.GUI;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;

public class Search_tavoli extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jTextField = null;
	private boolean vuoti;

	/**
	 * This is the default constructor
	 */
	public Search_tavoli(boolean vuoti) {
		
		super();
		this.vuoti=vuoti;
		initialize();
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.gridx = 0;
		this.setSize(225, 450);
		this.setLayout(new GridBagLayout());
		this.add(getJTextField(), gridBagConstraints);
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		String tavolo_vuoto="Seleziona nuovo tavolo:";
		String tavolo_pieno="Seleziona tavolo:";
		if (jTextField == null) {
			jTextField = new JTextField();
		}
		if(vuoti)
			jTextField.setText(tavolo_vuoto);
		else
			jTextField.setText(tavolo_pieno);
		return jTextField;
	}

}
