package it.softfood.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Listino implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Menu menu;
	private String descrizione;
	private double prezzo;
	private Set<Articolo> articolos = new HashSet<Articolo>(0);

	public Listino() {
	}

	public Listino(long id, Menu menu, double prezzo) {
		this.id = id;
		this.menu = menu;
		this.prezzo = prezzo;
	}

	public Listino(Long id, Menu menu, String descrizione, double prezzo,
			Set<Articolo> articolos) {
		this.id = id;
		this.menu = menu;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.articolos = articolos;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public Set<Articolo> getArticolos() {
		return this.articolos;
	}

	public void setArticolos(Set<Articolo> articolos) {
		this.articolos = articolos;
	}
        
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
