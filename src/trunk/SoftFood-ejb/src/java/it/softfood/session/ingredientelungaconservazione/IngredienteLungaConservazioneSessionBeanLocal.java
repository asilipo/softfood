package it.softfood.session.ingredientelungaconservazione;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface IngredienteLungaConservazioneSessionBeanLocal {

	it.softfood.entity.IngredienteLungaConservazione inserisciIngredienteLungaConservazione(
			it.softfood.entity.IngredienteLungaConservazione ingredienteLungaConservazione);
	
	it.softfood.entity.IngredienteLungaConservazione selezionaIngredienteLungaConservazionePerId(Long id);

	java.util.List<it.softfood.entity.IngredienteLungaConservazione> selezionaIngredientiLungaConservazione();

	boolean rimuoviIngredienteLungaConservazione(Long id);
	
}
