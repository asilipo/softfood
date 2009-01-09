package it.softfood.entity;

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
public class IngredientePietanza {

	private static final long serialVersionUID = 1L;

	@Id()
    @OneToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "pietanza")
    private Pietanza pietanza;
	@Id()
    @OneToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "ingrediente")
    private Ingrediente ingrediente;
	@Column(name = "quantita", nullable = false)
	private Integer quantita;
	
	public Pietanza getPietanza() {
		return pietanza;
	}
	
	public void setPietanza(Pietanza pietanza) {
		this.pietanza = pietanza;
	}
	
	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	
	public Integer getQuantita() {
		return quantita;
	}
	
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
