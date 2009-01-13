package it.softfood.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Event;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.KeyStroke;
import java.awt.Point;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JDialog;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;

public class Start { 

	private JFrame jFrame = null;
	private JPanel jContentPane = null;
	private JButton Inserisci = null;
	private JButton Gestisci = null;
	private boolean vuoti=true;
	/**
	 * This method initializes jFrame
	 * 
	 * @return javax.swing.JFrame
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setSize(225, 450);
			jFrame.setContentPane(getJContentPane());
			jFrame.setTitle("SoftFood");
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(2);
			gridLayout1.setColumns(1);
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(2);
			gridLayout.setColumns(1);
			jContentPane = new JPanel();
			jContentPane.setLayout(gridLayout1);
			jContentPane.add(getInserisci(), null);
			jContentPane.add(getGestisci(), null);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes Inserisci	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInserisci() {
		if (Inserisci == null) {
			ImageIcon cup = new ImageIcon("GUI/it/softfood/GUI/inserimento.jpg");
			Inserisci = new JButton();
			Inserisci.setText("Inserisci ordine");
			Inserisci.setIcon(cup);
			Inserisci.setVerticalTextPosition(SwingConstants.BOTTOM);
			Inserisci.setHorizontalAlignment(SwingConstants.CENTER);
			Inserisci.setMnemonic(KeyEvent.VK_M);
			Inserisci.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jContentPane.setVisible(false); // TODO Auto-generated Event stub actionPerformed()
					vuoti=true;
					Search_tavoli tav=new Search_tavoli(vuoti);
					jFrame.setContentPane(tav);
					
				}
			});
		}
		return Inserisci;
	}

	/**
	 * This method initializes Gestisci	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGestisci() {
		if (Gestisci == null) {
			ImageIcon cup = new ImageIcon("GUI/it/softfood/GUI/gestione.jpg");
			Gestisci = new JButton();
			Gestisci.setText("Gestisci ordine");
			Gestisci.setIcon(cup);
			Gestisci.setVerticalTextPosition(SwingConstants.BOTTOM);
			Gestisci.setHorizontalAlignment(SwingConstants.CENTER);
			Gestisci.setMnemonic(KeyEvent.VK_M);
			Gestisci.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//jContentPane.setVisible(false); // TODO Auto-generated Event stub actionPerformed()
					vuoti=false;
					Search_tavoli tav=new Search_tavoli(vuoti);
					jFrame.setContentPane(tav);
					
				}
			});
		}
		return Gestisci;
	}

	/**
	 * Launches this application
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Start application = new Start();
				application.getJFrame().setVisible(true);
			}
		});
	}

}
