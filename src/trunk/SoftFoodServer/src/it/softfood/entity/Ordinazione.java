package it.softfood.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Ordinazione implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Tavolo tavolo;
	private int coperti;
	private Date data;
	private Double sconto;
	private boolean terminato;
	private Double totale;
	private Set<Entrata> entratas = new HashSet<Entrata>(0);
	private Set<LineaOrdinazione> lineaOrdinaziones = new HashSet<LineaOrdinazione>(0);

	public Ordinazione() {
	}

	public Ordinazione(Long id, Tavolo tavolo, int coperti, Date data, boolean terminato) {
		this.id = id;
		this.tavolo = tavolo;
		this.coperti = coperti;
		this.data = data;
		this.terminato = terminato;
	}

	public Ordinazione(Long id, Tavolo tavolo, int coperti, Date data, Double sconto, 
			boolean terminato, Double totale, Set<Entrata> entratas, 
				Set<LineaOrdinazione> lineaOrdinaziones) {
		this.id = id;
		this.tavolo = tavolo;
		this.coperti = coperti;
		this.data = data;
		this.sconto = sconto;
		this.terminato = terminato;
		this.totale = totale;
		this.entratas = entratas;
		this.lineaOrdinaziones = lineaOrdinaziones;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tavolo getTavolo() {
		return this.tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

	public int getCoperti() {
		return this.coperti;
	}

	public void setCoperti(int coperti) {
		this.coperti = coperti;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getSconto() {
		return this.sconto;
	}

	public void setSconto(Double sconto) {
		this.sconto = sconto;
	}

	public boolean isTerminato() {
		return this.terminato;
	}

	public void setTerminato(boolean terminato) {
		this.terminato = terminato;
	}

	public Double getTotale() {
		return this.totale;
	}

	public void setTotale(Double totale) {
		this.totale = totale;
	}
	
	public Set<Entrata> getEntratas() {
		return this.entratas;
	}

	public void setEntratas(Set<Entrata> entratas) {
		this.entratas = entratas;
	}

	public Set<LineaOrdinazione> getLineaOrdinaziones() {
		return this.lineaOrdinaziones;
	}

	public void setLineaOrdinaziones(Set<LineaOrdinazione> lineaOrdinaziones) {
		this.lineaOrdinaziones = lineaOrdinaziones;
	}
        
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
