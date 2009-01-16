package it.softfood.session.ingredientepietanza;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface IngredientePietanzaSessionBeanLocal {
	
	it.softfood.entity.IngredientePietanza inserisciIngredientePietanza(
			it.softfood.entity.IngredientePietanza ingredientePietanza);
	
	it.softfood.entity.IngredientePietanza selezionaIngredientePietanzaPerId(Long id);

	boolean rimuoviIngredientePietanza(Long id);
}
