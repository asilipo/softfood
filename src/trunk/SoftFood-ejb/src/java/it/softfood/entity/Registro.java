package it.softfood.entity;

import java.io.Serializable;

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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Entity
@Table(name = "registro")
@SequenceGenerator(name = "sequenza_registro", sequenceName = "seq_id_registro")
@NamedQueries({
	@NamedQuery(name = "Registro.selezionaRegistroPerAnnoRiferimento", query = "SELECT r FROM Registro r WHERE r.annoRiferimento = :anno_riferimento")
})
public class Registro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenza_registro")
	private Long id;
	@Column(name = "descrizione", nullable = true)
	private String descrizione;
	@Column(name = "anno_riferimento", nullable = false, unique = true)
	private Integer annoRiferimento;
	@ManyToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "ristorante", nullable = false)
	private Ristorante ristorante;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getAnnoRiferimento() {
		return annoRiferimento;
	}

	public void setAnnoRiferimento(Integer annoRiferimento) {
		this.annoRiferimento = annoRiferimento;
	}

	public Ristorante getRistorante() {
		return ristorante;
	}
	
	public void setRistorante(Ristorante ristorante) {
		this.ristorante = ristorante;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
