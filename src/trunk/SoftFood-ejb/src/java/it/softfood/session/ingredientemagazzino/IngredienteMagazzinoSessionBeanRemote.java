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

    @java.lang.SuppressWarnings(value = {"unchecked"})
	java.util.List<it.softfood.entity.IngredienteMagazzino> selezionaIngredientiLungaConservazionePerQuantita(Integer quantita);

    @java.lang.SuppressWarnings(value = {"unchecked"})
    java.util.List<it.softfood.entity.IngredienteMagazzino> selezionaIngredientiMagazzino();

	boolean rimuoviIngredienteMagazzino(Long id);
	
}
