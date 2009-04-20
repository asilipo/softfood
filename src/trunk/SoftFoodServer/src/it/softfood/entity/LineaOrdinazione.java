package it.softfood.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class LineaOrdinazione implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Ordinazione ordinazione;
	private Articolo articolo;
	private int quantita;
	private Boolean evaso;
	private Set<Variante> variantes = new HashSet<Variante>(0);

	public LineaOrdinazione() {
	}

	public LineaOrdinazione(long id, Ordinazione ordinazione, Articolo articolo, int quantita) {
		this.id = id;
		this.ordinazione = ordinazione;
		this.articolo = articolo;
		this.quantita = quantita;
	}

	public LineaOrdinazione(long id, Ordinazione ordinazione,
			Articolo articolo, int quantita, Set<Variante> variantes) {
		this.id = id;
		this.ordinazione = ordinazione;
		this.articolo = articolo;
		this.quantita = quantita;
		this.variantes = variantes;
	}

	public Boolean getEvaso() {
		return evaso;
	}

	public void setEvaso(Boolean evaso) {
		this.evaso = evaso;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}
	
	public Ordinazione getOrdinazione() {
		return this.ordinazione;
	}

	public void setOrdinazione(Ordinazione ordinazione) {
		this.ordinazione = ordinazione;
	}
	
	public Articolo getArticolo() {
		return this.articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public int getQuantita() {
		return this.quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public Set<Variante> getVariantes() {
		return this.variantes;
	}

	public void setVariantes(Set<Variante> variantes) {
		this.variantes = variantes;
	}
         
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
