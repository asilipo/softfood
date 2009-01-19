package it.softfood.session.ingredientemagazzino;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface IngredienteMagazzinoSessionBeanLocal {

	it.softfood.entity.IngredienteMagazzino inserisciIngredienteMagazzino(
			it.softfood.entity.IngredienteMagazzino ingredienteMagazzino);
	
	it.softfood.entity.IngredienteMagazzino selezionaIngredienteMagazzinoPerId(Long id);

    @java.lang.SuppressWarnings(value = {"unchecked"})
	java.util.List<it.softfood.entity.IngredienteMagazzino> selezionaIngredientiLungaConservazionePerQuantita(Integer quantita);
	
	boolean rimuoviIngredienteMagazzino(Long id);

    @java.lang.SuppressWarnings(value = {"unchecked"})
    java.util.List<it.softfood.entity.IngredienteMagazzino> selezionaIngredientiMagazzino();
	
}
