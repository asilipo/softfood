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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "ingrediente_magazzino")
@SequenceGenerator(name = "sequenza_ingrediente_magazzino", sequenceName = "seq_id_ingrediente_magazzino")
@NamedQueries({
	@NamedQuery(name = "IngredienteMagazzino.selezionaIngredientiMagazzino", query = "SELECT i FROM IngredienteMagazzino i"),
	@NamedQuery(name = "IngredienteMagazzino.selezionaIngredientiLungaConservazionePerQuantita", query = "SELECT i FROM IngredienteMagazzino i WHERE quantita >= :quantita")
})
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
	@OneToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "ingrediente", nullable = false)
    private IngredienteLungaConservazione ingredienteLungaConservazione;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IngredienteLungaConservazione getIngredienteLungaConservazione() {
		return ingredienteLungaConservazione;
	}

	public void setIngredienteLungaConservazione(IngredienteLungaConservazione ingredienteLungaConservazione) {
		this.ingredienteLungaConservazione = ingredienteLungaConservazione;
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
