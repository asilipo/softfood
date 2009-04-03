package it.softfood.entity;

import java.util.HashSet;
import java.util.Set;

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
	private Set<Listino> listinos = new HashSet<Listino>(0);

	public Menu() {
	}

	public Menu(String id, Ristorante ristorante) {
		this.id = id;
		this.ristorante = ristorante;
	}

	public Menu(String id, Ristorante ristorante, String descrizione, Set<Listino> listinos) {
		this.id = id;
		this.ristorante = ristorante;
		this.descrizione = descrizione;
		this.listinos = listinos;
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
        
    public Set<Listino> getListinos() {
		return this.listinos;
	}

	public void setListinos(Set<Listino> listinos) {
		this.listinos = listinos;
	}
        
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
