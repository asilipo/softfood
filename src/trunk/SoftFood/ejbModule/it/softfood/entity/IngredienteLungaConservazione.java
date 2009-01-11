package it.softfood.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Entity
@DiscriminatorValue(value = "IngredienteLungaConservazione")
@NamedQueries({
	@NamedQuery(name = "IngredienteLungaConservazione.selezionaIngredientiLungaConservazione", query = "SELECT i FROM IngredienteLungaConservazione i")
})
public class IngredienteLungaConservazione extends Ingrediente implements Serializable {
	
	private static final long serialVersionUID = 1L;

}
