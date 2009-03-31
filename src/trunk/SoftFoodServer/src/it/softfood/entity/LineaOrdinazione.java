package it.softfood.entity;

import java.util.ArrayList;
import java.util.List;

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
	private List<Variante> varianti = new ArrayList<Variante>(0);

	public LineaOrdinazione() {
	}

	public LineaOrdinazione(long id, Ordinazione ordinazione,
			Articolo articolo, int quantita) {
		this.id = id;
		this.ordinazione = ordinazione;
		this.articolo = articolo;
		this.quantita = quantita;
	}

	public LineaOrdinazione(long id, Ordinazione ordinazione,
			Articolo articolo, int quantita, List<Variante> varianti) {
		this.id = id;
		this.ordinazione = ordinazione;
		this.articolo = articolo;
		this.quantita = quantita;
		this.varianti = varianti;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<Variante> getVarianti() {
		return this.varianti;
	}

	public void setVarianti(List<Variante> varianti) {
		this.varianti = varianti;
	}
         
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
