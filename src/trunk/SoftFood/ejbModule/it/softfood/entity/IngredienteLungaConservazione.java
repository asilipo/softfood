package it.softfood.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Entity
@DiscriminatorValue(value = "IngredienteLungaConservazione")
public class IngredienteLungaConservazione extends Ingrediente implements Serializable {
	
	private static final long serialVersionUID = 1L;

}
