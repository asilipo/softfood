package it.softfood.session.ingredientelungaconservazione;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface IngredienteLungaConservazioneSessionBeanRemote {
	
	it.softfood.entity.IngredienteLungaConservazione inserisciIngredienteLungaConservazione(
			it.softfood.entity.IngredienteLungaConservazione ingredienteLungaConservazione);
	
	it.softfood.entity.IngredienteLungaConservazione selezionaIngredienteLungaConservazionePerId(Long id);

    @java.lang.SuppressWarnings(value = {"unchecked"})
	java.util.List<it.softfood.entity.IngredienteLungaConservazione> selezionaIngredientiLungaConservazione();

	boolean rimuoviIngredienteLungaConservazione(Long id);
		
}
