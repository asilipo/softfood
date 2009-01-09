package it.softfood.entity;

import java.io.Serializable;

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
@Table(name = "ingrediente_pietanza")
public class IngredientePietanza implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "articolo", nullable = false)
	private Articolo articolo;
	@Id
	@OneToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "ingrediente", nullable = false)
	private Ingrediente ingrediente;
	@Column(name = "quantita", nullable = false)
	private Float quantita;
	
	public Articolo getArticolo() {
		return articolo;
	}
	
	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}
	
	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	
	public Float getQuantita() {
		return quantita;
	}
	
	public void setQuantita(Float quantita) {
		this.quantita = quantita;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
