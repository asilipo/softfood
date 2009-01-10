package it.softfood.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Entity
@Table(name = "ristorante")
public class Ristorante implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ragione_sociale")
	private String ragioneSociale;
	@Column(name = "partita_iva", nullable = false, unique = true)
	private String partitaIva;
	@OneToOne(cascade = CascadeType.ALL)
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "indirizzo", nullable = false)
	private Indirizzo indirizzo;
	
	public Ristorante () {}
	
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

	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}
	
}
