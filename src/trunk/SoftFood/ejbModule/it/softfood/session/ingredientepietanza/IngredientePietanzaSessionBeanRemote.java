package it.softfood.session.ingredientepietanza;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface IngredientePietanzaSessionBeanRemote {

	it.softfood.entity.IngredientePietanza inserisciIngredientePietanza(
			it.softfood.entity.IngredientePietanza ingredientePietanza);
	
	it.softfood.entity.IngredientePietanza selezionaIngredientePietanzaPerId(Long id);

	boolean rimuoviIngredientePietanza(Long id);
	
}
