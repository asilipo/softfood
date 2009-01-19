package it.softfood.session.variante;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface VarianteSessionBeanRemote {

	it.softfood.entity.Variante inserisciVariante(it.softfood.entity.Variante variante);
	
	it.softfood.entity.Variante selezionaVariantePerId(Long id);

    @java.lang.SuppressWarnings(value = {"unchecked"})
	java.util.List<it.softfood.entity.Variante> selezionaVariantiPerIngrediente(it.softfood.entity.Ingrediente ingrediente);
    
    @java.lang.SuppressWarnings(value = {"unchecked"})
	java.util.List<it.softfood.entity.Variante> selezionaVariantiPerLineaOrdinazione(it.softfood.entity.LineaOrdinazione lineaOrdinazione);
	
	boolean rimuoviVariante(Long id);
	
}
