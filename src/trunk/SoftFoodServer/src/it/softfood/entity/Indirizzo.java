package it.softfood.entity;

import java.util.ArrayList;
import java.util.List;

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
	private List<Ristorante> ristoranti = new ArrayList<Ristorante>(0);
	private List<Magazzino> magazzini = new ArrayList<Magazzino>(0);
	private List<Fornitore> fornitori = new ArrayList<Fornitore>(0);

	public Indirizzo() {
	}

	public Indirizzo(Long id) {
		this.id = id;
	}

	public Indirizzo(Long id, String cap, String citta, String civico,
			String provincia, String via, List<Ristorante> ristoranti,
				List<Magazzino> magazzini, List<Fornitore> fornitori) {
		this.id = id;
		this.cap = cap;
		this.citta = citta;
		this.civico = civico;
		this.provincia = provincia;
		this.via = via;
		this.ristoranti = ristoranti;
		this.magazzini = magazzini;
		this.fornitori = fornitori;
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

	public List<Ristorante> getRistoranti() {
		return this.ristoranti;
	}

	public void setRistorantes(List<Ristorante> ristoranti) {
		this.ristoranti = ristoranti;
	}

	public List<Magazzino> getMagazzini() {
		return this.magazzini;
	}

	public void setMagazzinos(List<Magazzino> magazzini) {
		this.magazzini = magazzini;
	}

	public List<Fornitore> getFornitori() {
		return this.fornitori;
	}

	public void setFornitori(List<Fornitore> fornitori) {
		this.fornitori = fornitori;
	}
        
    @Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
