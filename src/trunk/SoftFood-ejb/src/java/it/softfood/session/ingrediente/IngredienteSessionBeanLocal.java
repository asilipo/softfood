package it.softfood.session.ingrediente;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface IngredienteSessionBeanLocal {
	
	it.softfood.entity.Ingrediente inserisciIngrediente(it.softfood.entity.Ingrediente ingrediente);
	
	it.softfood.entity.Ingrediente selezionaIngredientePerId(Long id);

	java.util.List<it.softfood.entity.Ingrediente> selezionaIngredientePerNome(String nome);
	
    java.util.List<it.softfood.entity.Ingrediente> selezionaIngredientePerVariante();

	boolean rimuoviIngrediente(Long id);

}
