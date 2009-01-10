package it.softfood.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "ingrediente_magazzino")
@SequenceGenerator(name = "sequenza_ingrediente_magazzino", sequenceName = "seq_id_ingrediente_magazzino")
public class IngredienteMagazzino implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenza_ingrediente_magazzino")
	private Long id;
	@Column(name = "quantita", nullable = false)
	private Integer quantita;
	@ManyToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "magazzino", nullable = false)
	private Magazzino magazzino;
	@OneToMany()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "magazzino", nullable = false)
    private Collection<IngredienteLungaConservazione> ingredienti;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<IngredienteLungaConservazione> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(Collection<IngredienteLungaConservazione> ingredienti) {
		this.ingredienti = ingredienti;
	}

	public Magazzino getMagazzino() {
		return magazzino;
	}
	
	public void setMagazzino(Magazzino magazzino) {
		this.magazzino = magazzino;
	}
	
	public Integer getQuantita() {
		return quantita;
	}
	
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}	
	
}
