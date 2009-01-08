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
	
}
