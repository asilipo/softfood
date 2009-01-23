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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

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
@NamedQueries({
	@NamedQuery(name = "Ingrediente.selezionaIngredientiPerNome", query = "SELECT i FROM Ingrediente i WHERE nome = :nome"),
    @NamedQuery(name = "Ingrediente.selezionaIngredientiPerVariante", query = "SELECT i FROM Ingrediente i WHERE variante = true")
})
public class Ingrediente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id()
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenza_ingrediente")
	private Long id;
	@Column(name = "nome", nullable = false)
	private String nome;
	@Column(name = "descrizione", nullable = true)
	private String descrizione;
    @Column(name = "unita_misura", nullable = true)
	private String unitaMisura;
    @Column(name = "variante", nullable = false)
	private Boolean variante;
	@Column(name = "scadenza", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
	private Date scadenza;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

    public String getUnitaMisura() {
        return unitaMisura;
    }

    public void setUnitaMisura(String unitaMisura) {
        this.unitaMisura = unitaMisura;
    }

    public Boolean getVariante() {
        return variante;
    }

    public void setVariante(Boolean variante) {
        this.variante = variante;
    }
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
