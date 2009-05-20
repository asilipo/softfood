package it.softfood.GUI;

import it.softfood.entity.User;
import it.softfood.facade.POSUserFacade;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.jdesktop.application.Action;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.SingleFrameApplication;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class MainView extends FrameView {
    
    private JDialog aboutBox;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private JPanel actual = null;
    private POSUserFacade userFacade;
    private User u;
    private Timer timer;

    public MainView(SingleFrameApplication app) {
        super(app);
        userFacade = new POSUserFacade();
        u = null;
        timer = null;
        initComponents();
        
        Login login = new Login(this);
        this.setActualPanel(login);
        setComponent(login);   
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            aboutBox = new MainAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(aboutBox);
    }

    private void initComponents() {
        mainPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        mainPanel.setMaximumSize(new java.awt.Dimension(600, 500));
        mainPanel.setMinimumSize(new java.awt.Dimension(600, 500));
        mainPanel.setName("mainPanel");
        mainPanel.setPreferredSize(new java.awt.Dimension(600, 500));

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

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(MainView.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text"));
        fileMenu.setName("fileMenu"); 

        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text"));
        jMenuItem2.setEnabled(false);
        jMenuItem2.setName("jMenuItem2"); 
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem2);

        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); 
        jMenuItem1.setEnabled(false);
        jMenuItem1.setName("jMenuItem1"); 
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem1);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getActionMap(MainView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); 
        exitMenuItem.setAccelerator(null);
        exitMenuItem.setName("exitMenuItem");
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text"));
        helpMenu.setName("helpMenu");

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); 
        aboutMenuItem.setName("aboutMenuItem"); 
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }

	private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
		if(timer != null)
			timer.stop();
		
	    this.getActualPanel().setVisible(false);
	    Start start=new Start(this);
	    this.setActualPanel(start);
	    setComponent(start);
	}
	
	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
		try{
			userFacade.logout(this.getUser());
		}catch(Exception e){
			
		}
		if(timer!=null)
			timer.stop();
	    this.getActualPanel().setVisible(false);
	    Login login=new Login(this);
	    this.setActualPanel(login);
	    this.disableLogout();
	    this.disableStart();
	    setComponent(login);
	}

    public void setActualPanel(JPanel actual){
        this.actual=actual;
    }
    
    public JPanel getActualPanel(){
        return actual;
    }
    
    public void enableLogout(){
        jMenuItem1.setEnabled(true);
    }
    
    public void disableLogout(){
        jMenuItem1.setEnabled(false);
    }
    
    public void enableStart(){
        jMenuItem2.setEnabled(true);
    }
    
    public void disableStart(){
        jMenuItem2.setEnabled(false);
    }
    
    public void setUser(User u){
    	this.u = u;
    }
    
    public User getUser(){
    	return u;
    }
    
    public Timer getTimer(){
    	return timer;
    }
    
    public void setTimer(Timer timer){
    	this.timer = timer;
    }

}
