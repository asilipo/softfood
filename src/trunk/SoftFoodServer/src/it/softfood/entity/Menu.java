package it.softfood.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Menu implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private Ristorante ristorante;
	private String descrizione;
	private List<Listino> listini = new ArrayList<Listino>(0);

	public Menu() {
	}

	public Menu(String id, Ristorante ristorante) {
		this.id = id;
		this.ristorante = ristorante;
	}

	public Menu(String id, Ristorante ristorante, String descrizione, List<Listino> listini) {
		this.id = id;
		this.ristorante = ristorante;
		this.descrizione = descrizione;
		this.listini = listini;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Ristorante getRistorante() {
		return this.ristorante;
	}

	public void setRistorante(Ristorante ristorante) {
		this.ristorante = ristorante;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
        
    public List<Listino> getListini() {
		return this.listini;
	}

	public void setListini(List<Listino> listini) {
		this.listini = listini;
	}
        
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
