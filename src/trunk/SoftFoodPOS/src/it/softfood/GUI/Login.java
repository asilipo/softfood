package it.softfood.GUI;

import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.facade.POSUserFacade;

import javax.swing.JOptionPane;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Login extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private MainView frame;
	private POSUserFacade userFacade;

	public Login(MainView frame) {
		this.frame = frame;
		userFacade = new POSUserFacade();
		initComponents();
	}

	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JPasswordField();
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

		setName("Form"); 
		setLayout(new java.awt.BorderLayout());

		jPanel1.setMaximumSize(new java.awt.Dimension(600, 100));
		jPanel1.setMinimumSize(new java.awt.Dimension(600, 100));
		jPanel1.setName("jPanel1"); 
		jPanel1.setOpaque(false);
		jPanel1.setPreferredSize(new java.awt.Dimension(600, 100));
		jPanel1.setLayout(new java.awt.GridLayout(1, 2, 0, 20));

		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Login.class);
		jLabel1.setText(resourceMap.getString("jLabel1.text")); 
		jLabel1.setName("jLabel1"); 
		jPanel1.add(jLabel1);

		jTextField1.setText(resourceMap.getString("jTextField1.text")); 
		jTextField1.setName("jTextField1"); 
		jPanel1.add(jTextField1);

		add(jPanel1, java.awt.BorderLayout.NORTH);

		jPanel2.setMaximumSize(new java.awt.Dimension(600, 400));
		jPanel2.setMinimumSize(new java.awt.Dimension(600, 400));
		jPanel2.setName("jPanel2"); 
		jPanel2.setPreferredSize(new java.awt.Dimension(600, 400));
		jPanel2.setLayout(new java.awt.GridLayout(3, 4, 2, 2));

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

		jButton4.setText(resourceMap.getString("jButton4.text")); 
		jButton4.setName("jButton4"); 
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton4);

		jButton5.setText(resourceMap.getString("jButton5.text")); 
		jButton5.setName("jButton5"); 
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton5);

		jButton6.setText(resourceMap.getString("jButton6.text")); 
		jButton6.setName("jButton6"); 
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton6);

		jButton7.setText(resourceMap.getString("jButton7.text")); 
		jButton7.setName("jButton7"); 
		jButton7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton7ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton7);

		jButton8.setText(resourceMap.getString("jButton8.text")); 
		jButton8.setName("jButton8"); 
		jButton8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton8ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton8);

		jButton9.setText(resourceMap.getString("jButton9.text")); 
		jButton9.setName("jButton9");
		jButton9.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton9ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton9);

		jButton10.setText(resourceMap.getString("jButton10.text"));
		jButton10.setName("jButton10"); 
		jButton10.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton10ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton10);

		jButton11.setText(resourceMap.getString("jButton11.text")); 
		jButton11.setName("jButton11"); 
		jButton11.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton11ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton11);

		jButton12.setText(resourceMap.getString("jButton12.text")); 
		jButton12.setName("jButton12"); 
		jButton12.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton12ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton12);

		add(jPanel2, java.awt.BorderLayout.CENTER);
	}

	@SuppressWarnings("deprecation")
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		String text = jTextField1.getText();
		text += "1";
		jTextField1.setText(text);
	}

	@SuppressWarnings("deprecation")
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		String text = jTextField1.getText();
		text += "2";
		jTextField1.setText(text);
	}

	@SuppressWarnings("deprecation")
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		String text = jTextField1.getText();
		text += "3";
		jTextField1.setText(text);
	}

	@SuppressWarnings("deprecation")
	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		String text = jTextField1.getText();
		text += "4";
		jTextField1.setText(text);
	}

	@SuppressWarnings("deprecation")
	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
		String text = jTextField1.getText();
		text += "5";
		jTextField1.setText(text);
	}

	@SuppressWarnings("deprecation")
	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		String text = jTextField1.getText();
		text += "6";
		jTextField1.setText(text);
	}

	@SuppressWarnings("deprecation")
	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
		String text = jTextField1.getText();
		text += "7";
		jTextField1.setText(text);
	}

	@SuppressWarnings("deprecation")
	private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
		String text = jTextField1.getText();
		text += "8";
		jTextField1.setText(text);
	}

	@SuppressWarnings("deprecation")
	private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {
		String text = jTextField1.getText();
		text += "9";
		jTextField1.setText(text);
	}

	@SuppressWarnings("deprecation")
	private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {
		String text = jTextField1.getText();
		text += "0";
		jTextField1.setText(text);
	}

	@SuppressWarnings("deprecation")
	private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {
		String text = jTextField1.getText();
		if (jTextField1.getText().length() > 0) {
			text = text.substring(0, text.length() - 1);
		}
		jTextField1.setText(text);
	}

	@SuppressWarnings("deprecation")
	private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {
		User u = null;
		try {
			u = userFacade.login(Ruolo.CUOCO, jTextField1.getText());
			if (u != null) {
				frame.setUser(u);
				this.setVisible(false);
				Start start=new Start(frame);
				frame.setActualPanel(start);
				frame.setComponent(start);
				frame.enableLogout();
				frame.enableStart();
			} else {
				JOptionPane.showMessageDialog(frame.getComponent(), "PASSWORD ERRATA PER UTENTE CUOCO!","Errore Login",JOptionPane.ERROR_MESSAGE);
				this.setVisible(false);
				Login t = new Login(frame);
				frame.setActualPanel(t);
				frame.setComponent(t);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame.getComponent(), "PASSWORD ERRATA PER UTENTE CUOCO!","Errore Login",JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			Login t = new Login(frame);
			frame.setActualPanel(t);
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
	private javax.swing.JPasswordField jTextField1;

}
