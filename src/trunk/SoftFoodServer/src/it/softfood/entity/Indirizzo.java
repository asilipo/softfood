package it.softfood.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Indirizzo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cap;
	private String citta;
	private String civico;
	private String provincia;
	private String via;
	private Set<Ristorante> ristorantes = new HashSet<Ristorante>(0);
	private Set<Magazzino> magazzinos = new HashSet<Magazzino>(0);
	private Set<Fornitore> fornitores = new HashSet<Fornitore>(0);

	public Indirizzo() {
	}

	public Indirizzo(Long id) {
		this.id = id;
	}

	public Indirizzo(Long id, String cap, String citta, String civico,
			String provincia, String via, Set<Ristorante> ristorantes,
				Set<Magazzino> magazzinos, Set<Fornitore> fornitores) {
		this.id = id;
		this.cap = cap;
		this.citta = citta;
		this.civico = civico;
		this.provincia = provincia;
		this.via = via;
		this.ristorantes = ristorantes;
		this.magazzinos = magazzinos;
		this.fornitores = fornitores;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}
	
	public String getCitta() {
		return this.citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String getCivico() {
		return this.civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}
	
	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public String getVia() {
		return this.via;
	}

	public void setVia(String via) {
		this.via = via;
	}
	
	public Set<Ristorante> getRistorantes() {
		return this.ristorantes;
	}

	public void setRistorantes(Set<Ristorante> ristorantes) {
		this.ristorantes = ristorantes;
	}
	
	public Set<Magazzino> getMagazzinos() {
		return this.magazzinos;
	}

	public void setMagazzinos(Set<Magazzino> magazzinos) {
		this.magazzinos = magazzinos;
	}
	
	public Set<Fornitore> getFornitores() {
		return this.fornitores;
	}

	public void setFornitores(Set<Fornitore> fornitores) {
		this.fornitores = fornitores;
	}
        
    @Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
