package it.softfood.session.ingredientefresco;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface IngredienteFrescoSessionBeanRemote {

	it.softfood.entity.IngredienteFresco inserisciIngredienteFresco(it.softfood.entity.IngredienteFresco ingredienteFresco);
	
	it.softfood.entity.IngredienteFresco selezionaIngredienteFrescoPerId(Long id);

    @java.lang.SuppressWarnings(value = {"unchecked"})
	java.util.List<it.softfood.entity.IngredienteFresco> selezionaIngredientiFreschi();
	
	boolean rimuoviIngredienteFresco(Long id);
	
}
