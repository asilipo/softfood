package it.softfood.GUI;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.User;
import it.softfood.entity.Variante;
import it.softfood.enumeration.TipoVariante;
import it.softfood.exception.AggiornamentoIngredientiMagazzinoException;
import it.softfood.exception.DisponibilitaBevandaException;
import it.softfood.exception.DisponibilitaPietanzaException;
import it.softfood.exception.UserException;
import it.softfood.facade.PDAArticoloMenuFacade;
import it.softfood.facade.PDAOrdinazioneFacade;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import org.jdesktop.application.FrameView;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Pietanza extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private PDAArticoloMenuFacade articolo;
    private PDAOrdinazioneFacade ordinazioneFacade;
    private User role;

    @SuppressWarnings("unchecked")
	private void initFacade(Hashtable hash) {
        try {
            articolo = new PDAArticoloMenuFacade();
            ordinazioneFacade = new PDAOrdinazioneFacade();
        } catch (Exception e) {
            System.err.println("Errore binding: ArticoloMenuFacade - OrdinazioneFacade");
        }
    }

    public Pietanza(User role,FrameView frame, Long tavolo, Long id, String tipo) {
        this.tavolo = tavolo;
        this.tipo = tipo;
        this.role = role;
        this.frame = frame;
        this.id = id;
        
        initComponents();
        initFacade(null);

        ArrayList<Ingrediente> ingredienti = (ArrayList<Ingrediente>) articolo.selezionaIngredientiPietanza(role,id);

        String ingr[] = new String[ingredienti.size()];
        int i = 0;
        for (Ingrediente ingrediente : ingredienti) {
            ingr[i++] = ingrediente.getNome();
        }
        listaIngredienti.setListData(ingr);

        int disp;
        if (tipo.equalsIgnoreCase("bibite")) {
            disp = articolo.selezionaDisponibilitaBevanda(role,id);
            jPanel1.setVisible(false);
            cancella.setVisible(false);
        } else {
            disp = (Integer) articolo.selezionaDisponibilitaPietanza(role,id);
        }
        disponibilita.setText(disponibilita.getText() + disp);

        for (int idisp = 1; idisp <= disp; idisp++) {
            jComboBox1.addItem(idisp);
        }
        if (!tipo.equalsIgnoreCase("bibite")) {
            ArrayList<Ingrediente> ing = (ArrayList<Ingrediente>) ordinazioneFacade.selezionaIngredientiPerVariante(role);

            for (Ingrediente ingrediente : ing) {
                jComboBox3.addItem(ingrediente.getNome());
            }
        }

        jListModel = new DefaultListModel();
        jList1.setModel(jListModel);
    }

    private void initComponents() {
        ingredienti = new javax.swing.JPanel();
        disponibilita = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaIngredienti = new javax.swing.JList();
        quantita = new javax.swing.JPanel();
        combo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        cancella = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        pannello_bottoni = new javax.swing.JPanel();
        OK = new javax.swing.JButton();
        annulla = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(225, 450));
        setMinimumSize(new java.awt.Dimension(225, 450));
        setName("Form"); 
        setPreferredSize(new java.awt.Dimension(225, 450));
        setLayout(new java.awt.BorderLayout(5, 5));

        ingredienti.setName("ingredienti"); 
        ingredienti.setLayout(new java.awt.BorderLayout(0, 2));

        disponibilita.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(it.softfood.GUI.Main.class).getContext().getResourceMap(Pietanza.class);
        disponibilita.setText(resourceMap.getString("disponibilita.text")); 
        disponibilita.setName("disponibilita"); 
        ingredienti.add(disponibilita, java.awt.BorderLayout.SOUTH);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); 
        jLabel1.setName("jLabel1"); 
        ingredienti.add(jLabel1, java.awt.BorderLayout.NORTH);

        jScrollPane1.setName("ingredienti");

        listaIngredienti.setName("listaIngredienti"); 
        jScrollPane1.setViewportView(listaIngredienti);

        ingredienti.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(ingredienti, java.awt.BorderLayout.NORTH);

        quantita.setName("quantita"); 
        quantita.setLayout(new java.awt.BorderLayout(0, 2));

        combo.setName("combo"); 
        combo.setPreferredSize(new java.awt.Dimension(20, 20));
        combo.setLayout(new java.awt.BorderLayout());

        jLabel2.setText(resourceMap.getString("jLabel2.text")); 
        jLabel2.setName("jLabel2"); 
        combo.add(jLabel2, java.awt.BorderLayout.WEST);

        jComboBox1.setName("jComboBox1"); 
        combo.add(jComboBox1, java.awt.BorderLayout.CENTER);

        quantita.add(combo, java.awt.BorderLayout.NORTH);

        if (!tipo.equalsIgnoreCase("bibite")) {
            cancella.setText(resourceMap.getString("cancella.text")); 
            cancella.setEnabled(false);
            cancella.setName("cancella");
        }
        cancella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellaActionPerformed(evt);
            }
        });
        if (!tipo.equalsIgnoreCase("bibite")) {
            quantita.add(cancella, java.awt.BorderLayout.SOUTH);
        }

        if (!tipo.equalsIgnoreCase("bibite")) {
            jPanel1.setName("jPanel1"); // NOI18N
        }
        jPanel1.setLayout(new java.awt.BorderLayout());
        if (!tipo.equalsIgnoreCase("bibite")) {

            if (!tipo.equalsIgnoreCase("bibite")) {
                jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel3.setText(resourceMap.getString("jLabel3.text"));
                jLabel3.setName("jLabel3"); 
            }
            jPanel1.add(jLabel3, java.awt.BorderLayout.NORTH);

            jPanel2.setMinimumSize(new java.awt.Dimension(155, 150));
            jPanel2.setName("jPanel2"); 
            jPanel2.setPreferredSize(new java.awt.Dimension(100, 150));
            jPanel2.setLayout(new java.awt.BorderLayout());

            if (!tipo.equalsIgnoreCase("bibite")) {
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
                jComboBox2.setName("jComboBox2"); 
            }
            jComboBox2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jComboBox2ActionPerformed(evt);
                }
            });
            jPanel2.add(jComboBox2, java.awt.BorderLayout.WEST);

            jComboBox3.setName("jComboBox3");
            jPanel2.add(jComboBox3, java.awt.BorderLayout.CENTER);

            jButton1.setText(resourceMap.getString("jButton1.text"));
            jButton1.setName("jButton1"); 
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });
            jPanel2.add(jButton1, java.awt.BorderLayout.PAGE_END);

            jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

            jScrollPane2.setName("jScrollPane2"); 
            jScrollPane2.setPreferredSize(new java.awt.Dimension(260, 100));

            jList1.setName("jList1");
            jList1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jList1MouseClicked(evt);
                }
            });
            jScrollPane2.setViewportView(jList1);

            jPanel1.add(jScrollPane2, java.awt.BorderLayout.SOUTH);
        }

        if (!tipo.equalsIgnoreCase("bibite")) {
            quantita.add(jPanel1, java.awt.BorderLayout.CENTER);
        }

        add(quantita, java.awt.BorderLayout.CENTER);

        pannello_bottoni.setName("pannello_bottoni"); 
        pannello_bottoni.setLayout(new java.awt.GridLayout(1, 2));

        OK.setText(resourceMap.getString("OK.text")); 
        OK.setName("OK");
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });
        pannello_bottoni.add(OK);

        annulla.setText(resourceMap.getString("annulla.text")); 
        annulla.setName("annulla"); 
        annulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annullaActionPerformed(evt);
            }
        });
        pannello_bottoni.add(annulla);

        add(pannello_bottoni, java.awt.BorderLayout.SOUTH);
    }

	private void cancellaActionPerformed(java.awt.event.ActionEvent evt) {
	    jListModel.removeElementAt(jList1.getSelectedIndex());
	    cancella.setEnabled(false);
	}

	@SuppressWarnings("unchecked")
	private void OKActionPerformed(java.awt.event.ActionEvent evt) {
	    this.setVisible(false); 
	    try {
		    LineaOrdinazione linea = new LineaOrdinazione();
		    linea.setOrdinazione(ordinazioneFacade.selezionaOrdinazionePerId(role,tavolo));
		    linea.setArticolo(articolo.selezionaArticoloMenuPerId(role,id));   
	    	linea.setQuantita((Integer) jComboBox1.getSelectedItem());
		    linea = ordinazioneFacade.inserisciLineaOrdinazione(role, linea);
	
		    Variante variante = new Variante();
		
		    if (!jListModel.isEmpty()) {
		        Enumeration enumeration = jListModel.elements();
		        while (enumeration.hasMoreElements()) {
		            String var = (String) enumeration.nextElement();
		            variante.setLineaOrdinazione(linea);
		            if (var.substring(0, 1).equalsIgnoreCase("+")) {
		                variante.setTipoVariazione(TipoVariante.AGGIUNTA.ordinal());
		            } else {
		                variante.setTipoVariazione(TipoVariante.RIMOZIONE.ordinal());
		            }
		
		            variante.setIngrediente(ordinazioneFacade.selezionaIngredientePerNome(role,var.substring(2)));
		
		            ordinazioneFacade.inserisciVariante(role,variante);
		            variante = new Variante();
		        }
		    }

		    if (linea != null) {
			    if (tipo.equalsIgnoreCase("bibite")) {
			    	this.setVisible(false);
			        Bibite pannello = new Bibite(role, frame, tavolo);
			        frame.setComponent(pannello);
			    } else {
			    	this.setVisible(false);
			        Pannello_ordinazioni pannello = new Pannello_ordinazioni(role, frame, tavolo, tipo);
			        frame.setComponent(pannello);
			    }
		    } else {
		    	if (tipo.equalsIgnoreCase("bibite")) 
		    		throw new DisponibilitaBevandaException(null);
		    	else 
		    		throw new DisponibilitaPietanzaException(null);
		    }
		    	
	    } catch (UserException ue) {
	    	JOptionPane.showMessageDialog(frame.getComponent(), "L'utente non ha le credenziali per eseguire l'operazione!", "Errore sicurezza", JOptionPane.ERROR_MESSAGE);
	    } catch (DisponibilitaBevandaException e) {
	        Bibite pannello = new Bibite(role, frame, tavolo);
	        frame.setComponent(pannello);
	      	JOptionPane.showMessageDialog(frame.getComponent(), "La bevanda non è più disponibile!", "Errore disponibilità bevanda", JOptionPane.ERROR_MESSAGE);
		} catch (DisponibilitaPietanzaException e) {
	        Pannello_ordinazioni pannello = new Pannello_ordinazioni(role, frame, tavolo, tipo);
	        frame.setComponent(pannello);
		  	JOptionPane.showMessageDialog(frame.getComponent(), "La pietanza non è più disponibile!", "Errore disponibilità pietanza", JOptionPane.ERROR_MESSAGE);
		} catch (AggiornamentoIngredientiMagazzinoException e) {
		  	JOptionPane.showMessageDialog(frame.getComponent(), "Scorte magazzino insufficienti!", "Errore aggiornamento magazzino", JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException npe) {
	        Pannello_ordinazioni pannello = new Pannello_ordinazioni(role, frame, tavolo, tipo);
	        frame.setComponent(pannello);
	    	JOptionPane.showMessageDialog(frame.getComponent(), "L'articolo non è più disponibile!", "Errore sicurezza", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void annullaActionPerformed(java.awt.event.ActionEvent evt) {
	    this.setVisible(false);
	    if (tipo.equalsIgnoreCase("bibite")) {
	        Bibite pannello = new Bibite(role,frame, tavolo);
	        frame.setComponent(pannello);
	    } else {
	        Pannello_ordinazioni pannello = new Pannello_ordinazioni(role,frame, tavolo, tipo);
	        frame.setComponent(pannello);
	    }
	}
	
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
	    String data = (String) jComboBox2.getSelectedItem() + " " + (String) jComboBox3.getSelectedItem();
	    jListModel.addElement(data);
	}
	
	private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {
	    String opt = (String) jComboBox2.getSelectedItem();
	    jComboBox3.removeAllItems();
	    ArrayList<Ingrediente> ingredienti;
	    if (opt.equalsIgnoreCase("-")) {
	        ingredienti = (ArrayList<Ingrediente>) articolo.selezionaIngredientiPietanza(role,id);
	    } else {
	        ingredienti = (ArrayList<Ingrediente>) ordinazioneFacade.selezionaIngredientiPerVariante(role);
	    }
	
	    for (Ingrediente ingrediente : ingredienti) {
	        jComboBox3.addItem(ingrediente.getNome());
	    }
	}
	
	private void jList1MouseClicked(java.awt.event.MouseEvent evt) {
	    int i = jList1.getFirstVisibleIndex();
	    if (i == -1) {
	        cancella.setEnabled(false);
	    } else {
	        cancella.setEnabled(true);
	    }
	}

    private javax.swing.JButton OK;
    private javax.swing.JButton annulla;
    private javax.swing.JButton cancella;
    private javax.swing.JPanel combo;
    private javax.swing.JLabel disponibilita;
    private javax.swing.JPanel ingredienti;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList listaIngredienti;
    private javax.swing.JPanel pannello_bottoni;
    private javax.swing.JPanel quantita;

    private FrameView frame;
    private Long tavolo;
    private Long id;
    private String tipo;
    private DefaultListModel jListModel;
    
}

