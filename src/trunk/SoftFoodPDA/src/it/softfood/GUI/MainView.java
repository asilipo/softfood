package it.softfood.GUI;

import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import it.softfood.GUI.Login;
import it.softfood.entity.User;
import it.softfood.facade.PDAUserFacade;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class MainView extends FrameView {

	private User u;
	private PDAUserFacade userFacade;

	public MainView(SingleFrameApplication app) {
		super(app);
		userFacade=new PDAUserFacade();
		u=null;
		initComponents();      

		Login ordine = new Login(this);
		setComponent(ordine);
	}

	private void initComponents() {
		mainPanel = new javax.swing.JPanel();
		menuBar = new javax.swing.JMenuBar();
		jMenuItem2 = new javax.swing.JMenuItem();
		jMenuItem1 = new javax.swing.JMenuItem();
		javax.swing.JMenu fileMenu = new javax.swing.JMenu();

		mainPanel.setMaximumSize(new java.awt.Dimension(225, 450));
		mainPanel.setMinimumSize(new java.awt.Dimension(225, 450));
		mainPanel.setName("mainPanel"); // NOI18N
		mainPanel.setPreferredSize(new java.awt.Dimension(225, 450));
		mainPanel.setLayout(null);

		javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
		mainPanel.setLayout(mainPanelLayout);
		mainPanelLayout.setHorizontalGroup(
				mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 600, Short.MAX_VALUE)
		);
		mainPanelLayout.setVerticalGroup(
				mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 500, Short.MAX_VALUE)
		);

		menuBar.setMaximumSize(new java.awt.Dimension(600, 22));
		menuBar.setMinimumSize(new java.awt.Dimension(600, 22));
		menuBar.setName("menuBar");
		menuBar.setPreferredSize(new java.awt.Dimension(600, 22));

		fileMenu.setText("File"); 
		fileMenu.setName("fileMenu");

		jMenuItem1.setText("Logout");
		jMenuItem1.setEnabled(false);
		jMenuItem1.setName("jMenuItem1"); 
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed(evt);
			}
		});
		fileMenu.add(jMenuItem1);

		menuBar.add(fileMenu);

		setComponent(mainPanel);
		setMenuBar(menuBar);
	}

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
		try{
			userFacade.logout(this.getUser());
		}catch(Exception e){
			e.printStackTrace();
		}
		this.getComponent().setVisible(false);
		Login login=new Login(this);
		this.disableLogout();
		setComponent(login);
	}

	public void enableLogout(){
		jMenuItem1.setEnabled(true);
	}

	public void disableLogout(){
		jMenuItem1.setEnabled(false);
	}

	public void setUser(User u){
		this.u=u;
	}

	public User getUser(){
		return u;
	}

	private javax.swing.JPanel mainPanel;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenuItem jMenuItem1;
	@SuppressWarnings("unused")
	private javax.swing.JMenuItem jMenuItem2;

}
