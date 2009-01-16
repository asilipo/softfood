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
@DiscriminatorValue(value = "IngredienteFresco")
@NamedQueries({
	@NamedQuery(name = "IngredienteFresco.selezionaIngredientiFreschi", query = "SELECT i FROM IngredienteFresco i")
})
public class IngredienteFresco extends Ingrediente implements Serializable {
	
	private static final long serialVersionUID = 1L;

}