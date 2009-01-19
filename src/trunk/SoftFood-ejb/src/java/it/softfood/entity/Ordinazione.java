package it.softfood.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Temporal;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Entity
@Table(name = "ordinazione")
@SequenceGenerator(name = "sequenza_ordinazione", sequenceName = "seq_id_ordinazione")
@NamedQueries({
	@NamedQuery(name = "Ordinazione.selezionaOrdinazioni", query = "SELECT o FROM Ordinazione o"),
	@NamedQuery(name = "Ordinazione.selezionaOrdinazioniGionalierePerTavolo", query = "SELECT o FROM Ordinazione o WHERE o.tavolo = :tavolo AND terminato = :terminato"),
	@NamedQuery(name = "Ordinazione.selezionaOrdinazioniPerData", query = "SELECT o FROM Ordinazione o WHERE o.data = :data")
})
public class Ordinazione implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenza_ordinazione")
	private Long id;
	@Column(name = "totale", nullable = true)
	private Double totale;
	@Column(name = "sconto", nullable = true)
	private Double sconto;
	@Column(name = "terminato", nullable = false)
	private Boolean terminato;
	@Column(name = "data", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
	private Date data;
	@ManyToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "tavolo", nullable = false)
	private Tavolo tavolo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getTotale() {
		return totale;
	}

	public void setTotale(Double totale) {
		this.totale = totale;
	}

	public Double getSconto() {
		return sconto;
	}

	public void setSconto(Double sconto) {
		this.sconto = sconto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getTerminato() {
		return terminato;
	}

	public void setTerminato(Boolean terminato) {
		this.terminato = terminato;
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
