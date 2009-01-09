package it.softfood.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Entity
@Table(name = "ingrediente")
@SequenceGenerator(name = "sequenza_ingrediente", sequenceName = "seq_id_ingrediente")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_ingrediente", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "Ingrediente")
public class Ingrediente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id()
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenza_ingrediente")
	private Long id;
	@Column(name = "descrizione", nullable = false)
	private String descrizione;
	@Column(name = "scadenza", nullable = true)
	private Date scadenza;
	
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

	public Date getScadenza() {
		return scadenza;
	}

	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
