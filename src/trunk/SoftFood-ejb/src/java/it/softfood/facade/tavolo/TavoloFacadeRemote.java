package it.softfood.facade.tavolo;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface TavoloFacadeRemote {
	
	it.softfood.entity.Tavolo inserisciTavolo(it.softfood.entity.Tavolo tavolo);
	
	boolean liberaTavolo(it.softfood.entity.Tavolo tavolo);
	
	boolean occupaTavolo(it.softfood.entity.Tavolo tavolo);
	
	it.softfood.entity.Tavolo selezionaTavolo(Long id);
	
	java.util.List<it.softfood.entity.Tavolo> selezionaTavoliLiberi();
	
	boolean rimuoviTavolo(Long id);

}
