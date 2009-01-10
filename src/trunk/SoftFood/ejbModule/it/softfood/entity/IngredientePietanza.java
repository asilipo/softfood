package it.softfood.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Entity
@Table(name = "ingrediente_pietanza")
public class IngredientePietanza {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IngredientePietanzaPK ingredientePietanzaPK;
	@Column(name = "quantita", nullable = false)
	private Integer quantita;
	
	public IngredientePietanzaPK getIngredientePietanzaPK() {
		return ingredientePietanzaPK;
	}

	public void setIngredientePietanzaPK(IngredientePietanzaPK ingredientePietanzaPK) {
		this.ingredientePietanzaPK = ingredientePietanzaPK;
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
