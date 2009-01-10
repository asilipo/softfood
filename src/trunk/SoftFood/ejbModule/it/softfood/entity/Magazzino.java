package it.softfood.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "magazzino")
@SequenceGenerator(name = "sequenza_magazzino", sequenceName = "seq_id_magazzino")
public class Magazzino implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenza_magazzino")
	private Long id;
	@OneToOne(cascade = CascadeType.ALL)
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "indirizzo", nullable = true)
	private Indirizzo indirizzo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
