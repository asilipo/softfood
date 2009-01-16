package it.softfood.facade.tavolo;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface TavoloFacadeLocal {

	it.softfood.entity.Tavolo inserisciTavolo(it.softfood.entity.Tavolo tavolo);
	
	boolean liberaTavolo(it.softfood.entity.Tavolo tavolo);
	
	boolean occupaTavolo(it.softfood.entity.Tavolo tavolo);
	
	it.softfood.entity.Tavolo selezionaTavolo(Long id);
	
	java.util.List<it.softfood.entity.Tavolo> selezionaTavoliLiberi();

    java.util.List<it.softfood.entity.Tavolo> selezionaTavoliOccupati();
    
	boolean rimuoviTavolo(Long id);
	
}
