package it.softfood.GUI;

import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.facade.PDAUserFacade;

import javax.swing.JOptionPane;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Login extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	public PDAUserFacade userFacade;
	private MainView frame;

	public Login(MainView frame) {
		userFacade = new PDAUserFacade();
		this.frame = frame;
		initComponents();
	}

	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		text = new javax.swing.JPasswordField();
		jPanel2 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jButton7 = new javax.swing.JButton();
		jButton8 = new javax.swing.JButton();
		jButton9 = new javax.swing.JButton();
		jButton10 = new javax.swing.JButton();
		jButton11 = new javax.swing.JButton();
		jButton12 = new javax.swing.JButton();

		setMaximumSize(new java.awt.Dimension(225, 450));
		setMinimumSize(new java.awt.Dimension(225, 450));
		setLayout(new java.awt.GridLayout(2, 1));

		jPanel1.setName("jPanel1");
		jPanel1.setLayout(new java.awt.GridLayout(2, 1));

		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Login cameriere - inserire password");
		jLabel1.setName("jLabel1");
		jPanel1.add(jLabel1);

		jScrollPane1.setName("jScrollPane1"); 

		text.setColumns(1);

		text.setName("text"); 
		text.setSize(150, 10);
		text.setMaximumSize(new java.awt.Dimension(150, 10));
		jScrollPane1.setViewportView(text);

		jPanel1.add(jScrollPane1);

		add(jPanel1);

		jPanel2.setName("jPanel2"); 
		jPanel2.setLayout(new java.awt.GridLayout(3, 4));

		jButton1.setText("0");
		jButton1.setName("jButton1");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton1);

		jButton2.setText("1");
		jButton2.setName("jButton2");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton2);

		jButton3.setText("2");
		jButton3.setName("jButton3"); 
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton3);

		jButton4.setText("3");
		jButton4.setName("jButton4"); 
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton4);

		jButton5.setText("4");
		jButton5.setName("jButton5"); 
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton5);

		jButton6.setText("5");
		jButton6.setName("jButton6");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton6);

		jButton7.setText("6");
		jButton7.setName("jButton7"); 
		jButton7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton7ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton7);

		jButton8.setText("7");
		jButton8.setName("jButton8"); 
		jButton8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton8ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton8);

		jButton9.setText("8");
		jButton9.setName("jButton9");
		jButton9.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton9ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton9);

		jButton10.setText("9");
		jButton10.setName("jButton10");
		jButton10.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton10ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton10);

		jButton11.setText("C");
		jButton11.setName("jButton11");
		jButton11.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton11ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton11);

		jButton12.setText("N");
		jButton12.setName("jButton12");
		jButton12.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton12ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton12);

		add(jPanel2);
	}

	@SuppressWarnings("deprecation")
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		String area = text.getText();
		text.setText(area.concat("0"));
	}

	@SuppressWarnings("deprecation")
	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		String area = text.getText();
		text.setText(area.concat("3"));
	}

	@SuppressWarnings("deprecation")
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		String area = text.getText();
		text.setText(area.concat("1"));
	}

	@SuppressWarnings("deprecation")
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		String area = text.getText();
		text.setText(area.concat("2"));
	}

	@SuppressWarnings("deprecation")
	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		String area = text.getText();
		text.setText(area.concat("4"));
	}

	@SuppressWarnings("deprecation")
	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		String area = text.getText();
		text.setText(area.concat("5"));
	}

	@SuppressWarnings("deprecation")
	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
		String area = text.getText();
		text.setText(area.concat("6"));
	}

	@SuppressWarnings("deprecation")
	private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
		String area = text.getText();
		text.setText(area.concat("7"));
	}

	@SuppressWarnings("deprecation")
	private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {
		String area = text.getText();
		text.setText(area.concat("8"));
	}

	@SuppressWarnings("deprecation")
	private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {
		String area = text.getText();
		text.setText(area.concat("9"));
	}

	@SuppressWarnings("deprecation")
	private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {
		String area = text.getText();
		if (!area.equalsIgnoreCase(""))
			text.setText(area.substring(0, area.length() - 1));
	}

	@SuppressWarnings("deprecation")
	private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {
		User u = null;
		try {
			u = userFacade.login(Ruolo.CAMERIERE, text.getText());
			if (u == null)
				throw new Exception();
			
			frame.setUser(u);
			frame.enableLogout();
			this.setVisible(false);
			Ordine ordine = new Ordine(u, frame);
			frame.setComponent(ordine);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame.getComponent(), "Password errata o l'utente ha gia' \neffettuato l'accesso!","Errore Login",JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			Login t = new Login(frame);
			frame.setComponent(t);
		}
	}

	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton10;
	private javax.swing.JButton jButton11;
	private javax.swing.JButton jButton12;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JButton jButton8;
	private javax.swing.JButton jButton9;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JPasswordField text;

}

