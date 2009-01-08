package it.softfood.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Entity
@Table(name = "fornitore")
public class Fornitore implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ragione_sociale")
	private String ragioneSociale;
	@Column(name = "partita_iva", nullable = false)
	private String partitaIva;
	@Column(name = "descrizione", nullable = true)
	private String descrizione;
	@Column(name = "via", nullable = true)
	private String via;
	@Column(name = "civico", nullable = true)
	private String civico;
	@Column(name = "cap", nullable = true)
	private String cap;
	@Column(name = "citta", nullable = true)
	private String citta;
	@Column(name = "provincia", nullable = true)
	private String provincia;
	@ManyToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "ragione_sociale", nullable = false)
	private Ristorante ristorante;
	
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	
	public String getPartitaIva() {
		return partitaIva;
	}
	
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getVia() {
		return via;
	}
	
	public void setVia(String via) {
		this.via = via;
	}
	
	public String getCivico() {
		return civico;
	}
	
	public void setCivico(String civico) {
		this.civico = civico;
	}
	
	public String getCap() {
		return cap;
	}
	
	public void setCap(String cap) {
		this.cap = cap;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public Ristorante getRistorante() {
		return ristorante;
	}
	
	public void setRistorante(Ristorante ristorante) {
		this.ristorante = ristorante;
	}
	
}
