package it.softfood.GUI;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.User;
import it.softfood.facade.PDAOrdinazioneFacade;

import it.softfood.handler.OrdinazioneFacade;

import java.util.ArrayList;
import java.util.Hashtable;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jdesktop.application.FrameView;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Conferma extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	
	private PDAOrdinazioneFacade ordinazioneFacade;
    private User role;

    private void initFacade() {
        try {
            ordinazioneFacade = new PDAOrdinazioneFacade();
        } catch(Exception e) {
            System.err.println("Errore binding: OrdinazioneFacade");
        }
    }
    
    public Conferma(User role,FrameView frame,Long tavolo) {
        this.frame = frame;
        this.tavolo = tavolo;
        this.role = role;

        initComponents();
        initFacade();
        
        ArrayList<LineaOrdinazione> ordini = (ArrayList<LineaOrdinazione>) ordinazioneFacade.selezionaLineeOrdinazionePerOrdinazione(role,ordinazioneFacade.selezionaOrdinazionePerId(role,tavolo));
        
        String data[] = new String[ordini.size()];
        
        int i = 0;
        for(LineaOrdinazione linea : ordini)
            data[i++] = linea.getArticolo().getNome() + " - " + linea.getQuantita();
        
        menu.setListData(data);
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        menu = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Cancella = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        OK = new javax.swing.JButton();
        Annulla = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(225, 450));
        setMinimumSize(new java.awt.Dimension(225, 450));
        setName("Form"); 
        setPreferredSize(new java.awt.Dimension(225, 450));
        setLayout(new java.awt.BorderLayout(5, 5));

        jScrollPane1.setMaximumSize(new java.awt.Dimension(225, 350));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(225, 350));
        jScrollPane1.setName("jScrollPane1"); 
        jScrollPane1.setPreferredSize(new java.awt.Dimension(225, 350));

        menu.setName("menu"); 
        jScrollPane1.setViewportView(menu);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); 
        jPanel1.setLayout(new java.awt.GridLayout(1, 3));

        jPanel2.setName("jPanel2");
        jPanel2.setLayout(new java.awt.BorderLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Conferma.class);
        Cancella.setText(resourceMap.getString("Cancella.text"));
        Cancella.setName("Cancella"); 
        Cancella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancellaActionPerformed(evt);
            }
        });
        jPanel2.add(Cancella, java.awt.BorderLayout.NORTH);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        OK.setText(resourceMap.getString("OK.text")); // NOI18N
        OK.setName("OK"); // NOI18N
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });
        jPanel3.add(OK);

        Annulla.setText(resourceMap.getString("Annulla.text")); // NOI18N
        Annulla.setName("Annulla"); // NOI18N
        Annulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnullaActionPerformed(evt);
            }
        });
        jPanel3.add(Annulla);

        jPanel2.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanel2);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);
    }

    private void CancellaActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);

        ordinazioneFacade.rimuoviOrdinazione(role,tavolo, true);
        Ordine ordine = new Ordine(role, frame);
        frame.setComponent(ordine);   
    }

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);

        Ordine ordine = new Ordine(role, frame);
        frame.setComponent(ordine);
    }

    private void AnnullaActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        
        frame.setComponent(new Menu(role,frame,tavolo));
    }

    private javax.swing.JButton Annulla;
    private javax.swing.JButton Cancella;
    private javax.swing.JButton OK;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList menu;

    private FrameView frame;
    private Long tavolo;
    
}
