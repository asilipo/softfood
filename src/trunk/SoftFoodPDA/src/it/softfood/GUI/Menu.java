package it.softfood.GUI;

import it.softfood.entity.User;

import org.jdesktop.application.FrameView;


/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Menu extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private User role;
	
	public Menu(User role,FrameView frame,Long tavolo) {
        initComponents();
        this.frame = frame;
        this.tavolo = tavolo;
        this.role = role;
    }

    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        Antipasti = new javax.swing.JButton();
        Primi = new javax.swing.JButton();
        Secondi = new javax.swing.JButton();
        Contorni = new javax.swing.JButton();
        Dolci = new javax.swing.JButton();
        Bibite = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Ok = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Menu.class);
        jToggleButton1.setText(resourceMap.getString("jToggleButton1.text")); // NOI18N
        jToggleButton1.setName("jToggleButton1"); // NOI18N

        setMaximumSize(new java.awt.Dimension(225, 450));
        setMinimumSize(new java.awt.Dimension(225, 450));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(225, 450));
        setLayout(new java.awt.GridLayout(4, 2, 5, 5));

        Antipasti.setIcon(resourceMap.getIcon("Antipasti.icon")); // NOI18N
        Antipasti.setText(resourceMap.getString("Antipasti.text")); // NOI18N
        Antipasti.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Antipasti.setName("Antipasti"); // NOI18N
        Antipasti.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Antipasti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AntipastiActionPerformed(evt);
            }
        });
        add(Antipasti);

        Primi.setIcon(resourceMap.getIcon("Primi.icon")); // NOI18N
        Primi.setText(resourceMap.getString("Primi.text")); // NOI18N
        Primi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Primi.setName("Primi"); // NOI18N
        Primi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Primi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrimiActionPerformed(evt);
            }
        });
        add(Primi);

        Secondi.setIcon(resourceMap.getIcon("Secondi.icon")); 
        Secondi.setText(resourceMap.getString("Secondi.text")); 
        Secondi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Secondi.setName("Secondi"); 
        Secondi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Secondi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SecondiActionPerformed(evt);
            }
        });
        add(Secondi);

        Contorni.setIcon(resourceMap.getIcon("Contorni.icon")); 
        Contorni.setText(resourceMap.getString("Contorni.text")); 
        Contorni.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Contorni.setName("Contorni");
        Contorni.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Contorni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContorniActionPerformed(evt);
            }
        });
        add(Contorni);

        Dolci.setIcon(resourceMap.getIcon("Dolci.icon")); 
        Dolci.setText(resourceMap.getString("Dolci.text")); 
        Dolci.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Dolci.setName("Dolci"); 
        Dolci.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Dolci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DolciActionPerformed(evt);
            }
        });
        add(Dolci);

        Bibite.setIcon(resourceMap.getIcon("Bibite.icon"));
        Bibite.setText(resourceMap.getString("Bibite.text")); 
        Bibite.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Bibite.setName("Bibite"); // NOI18N
        Bibite.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Bibite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BibiteActionPerformed(evt);
            }
        });
        add(Bibite);

        jPanel2.setName("jPanel2"); 
        add(jPanel2);

        jPanel1.setMaximumSize(new java.awt.Dimension(100, 29));
        jPanel1.setName("jPanel1"); 
        jPanel1.setLayout(new java.awt.BorderLayout());

        Ok.setText(resourceMap.getString("Ok.text")); 
        Ok.setMaximumSize(new java.awt.Dimension(100, 29));
        Ok.setMinimumSize(new java.awt.Dimension(100, 29));
        Ok.setName("Ok"); 
        Ok.setPreferredSize(new java.awt.Dimension(100, 50));
        Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkActionPerformed(evt);
            }
        });
        jPanel1.add(Ok, java.awt.BorderLayout.PAGE_END);

        add(jPanel1);
    }

    private void OkActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        Conferma conferma = new Conferma(role,frame,tavolo);
        frame.setComponent(conferma);
    }

	private void AntipastiActionPerformed(java.awt.event.ActionEvent evt) {
	    this.setVisible(false);
	    Pannello_ordinazioni antipasto = new Pannello_ordinazioni(role,frame, tavolo, "antipasti");
	    frame.setComponent(antipasto);
	}

	private void PrimiActionPerformed(java.awt.event.ActionEvent evt) {
	    this.setVisible(false);
	    Pannello_ordinazioni primi = new Pannello_ordinazioni(role,frame,tavolo,"primi");
	    frame.setComponent(primi);
	}
	
	private void SecondiActionPerformed(java.awt.event.ActionEvent evt) {
	    this.setVisible(false);
	    Pannello_ordinazioni secondi = new Pannello_ordinazioni(role,frame,tavolo,"secondi");
	    frame.setComponent(secondi);
	}

	private void ContorniActionPerformed(java.awt.event.ActionEvent evt) {
	    this.setVisible(false);
	    Pannello_ordinazioni contorni = new Pannello_ordinazioni(role,frame,tavolo,"contorni");
	    frame.setComponent(contorni);
	}
	
	private void DolciActionPerformed(java.awt.event.ActionEvent evt) {
	    this.setVisible(false);
	    Pannello_ordinazioni dolci = new Pannello_ordinazioni(role,frame,tavolo,"dolci");
	    frame.setComponent(dolci);
	}
	
	private void BibiteActionPerformed(java.awt.event.ActionEvent evt) {
	    this.setVisible(false);
	    Bibite bibite = new Bibite(role,frame,tavolo);
	    frame.setComponent(bibite);
	}

    private javax.swing.JButton Antipasti;
    private javax.swing.JButton Bibite;
    private javax.swing.JButton Contorni;
    private javax.swing.JButton Dolci;
    private javax.swing.JButton Ok;
    private javax.swing.JButton Primi;
    private javax.swing.JButton Secondi;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToggleButton jToggleButton1;
    private FrameView frame;
    private Long tavolo;
    
}
