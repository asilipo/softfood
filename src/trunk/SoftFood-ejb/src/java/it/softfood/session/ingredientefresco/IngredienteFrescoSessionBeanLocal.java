package it.softfood.session.ingredientefresco;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface IngredienteFrescoSessionBeanLocal {
	
	it.softfood.entity.IngredienteFresco inserisciIngredienteFresco(it.softfood.entity.IngredienteFresco ingredienteFresco);
	
	it.softfood.entity.IngredienteFresco selezionaIngredienteFrescoPerId(Long id);

	java.util.List<it.softfood.entity.IngredienteFresco> selezionaIngredientiFreschi();
	
	boolean rimuoviIngredienteFresco(Long id);

}
