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
@Table(name = "tavolo")
@SequenceGenerator(name = "sequenza_tavolo", sequenceName = "seq_id_tavolo")
@NamedQueries({
		@NamedQuery(name = "Tavolo.selezionaTavoliPerNumeroPosti", query = "SELECT t FROM Tavolo t WHERE t.numeroPosti = :numero_posti"),
		@NamedQuery(name = "Tavolo.selezionaTavoliLiberi", query = "SELECT t FROM Tavolo t WHERE t.occupato = false ORDER BY riferimento"),
        @NamedQuery(name = "Tavolo.selezionaTavoliOccupati", query = "SELECT t FROM Tavolo t WHERE t.occupato = true ORDER BY riferimento")
})
public class Tavolo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id()
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenza_tavolo")
	private Long id;
	@Column(name = "numero_posti", nullable = false)
	private Integer numeroPosti;
    @Column(name = "riferimento", nullable = false, unique = true)
	private String riferimento;
	@Column(name = "occupato", nullable = false)
	private Boolean occupato;
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

    public String getRiferimento() {
        return riferimento;
    }

    public void setRiferimento(String riferimento) {
        this.riferimento = riferimento;
    }
	
	public Integer getNumeroPosti() {
		return numeroPosti;
	}
	
	public void setNumeroPosti(Integer numeroPosti) {
		this.numeroPosti = numeroPosti;
	}
	
	public Boolean getOccupato() {
		return occupato;
	}

	public void setOccupato(Boolean occupato) {
		this.occupato = occupato;
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
