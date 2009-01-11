package it.softfood.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "entrata")
@SequenceGenerator(name = "sequenza_entrata", sequenceName = "seq_id_entrata")
@NamedQueries({
    @NamedQuery(name = "Entrata.selezionaEntrataPerOrdinazione", query = "SELECT e FROM Entrata e WHERE ordinazione = :ordinazione")
})
public class Entrata implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenza_entrata")
	private Long id;
	@ManyToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "registro", nullable = false)
	private Registro registro;
	@OneToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "ordinazione", nullable = false)
	private Ordinazione ordinazione;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Registro getRegistro() {
		return registro;
	}
	
	public void setRegistro(Registro registro) {
		this.registro = registro;
	}
	
	public Ordinazione getOrdinazione() {
		return ordinazione;
	}
	
	public void setOrdinazione(Ordinazione ordinazione) {
		this.ordinazione = ordinazione;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
