package it.softfood.session.ingredientemagazzino;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface IngredienteMagazzinoSessionBeanRemote {

	it.softfood.entity.IngredienteMagazzino inserisciIngredienteMagazzino(
			it.softfood.entity.IngredienteMagazzino ingredienteMagazzino);
	
	it.softfood.entity.IngredienteMagazzino selezionaIngredienteMagazzinoPerId(Long id);

	java.util.List<it.softfood.entity.IngredienteMagazzino> selezionaIngredientiLungaConservazionePerQuantita(Integer quantita);

	boolean rimuoviIngredienteMagazzino(Long id);

    java.util.List<it.softfood.entity.IngredienteMagazzino> selezionaIngredientiMagazzino();
	
}
