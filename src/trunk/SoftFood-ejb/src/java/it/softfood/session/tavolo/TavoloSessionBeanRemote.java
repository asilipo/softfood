package it.softfood.session.tavolo;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface TavoloSessionBeanRemote {

	it.softfood.entity.Tavolo inserisciTavolo(it.softfood.entity.Tavolo tavolo);

	it.softfood.entity.Tavolo selezionaTavoloPerId(Long id);

    @java.lang.SuppressWarnings(value = {"unchecked"})
	java.util.List<it.softfood.entity.Tavolo> selezionaTavoliPerNumeroPosti(Integer numeroPosti);

    @java.lang.SuppressWarnings(value = {"unchecked"})
	java.util.List<it.softfood.entity.Tavolo> selezionaTavoliLiberi();

    @java.lang.SuppressWarnings(value = {"unchecked"})
    java.util.List<it.softfood.entity.Tavolo> selezionaTavoliOccupati();
	
	boolean modificaStatoTavolo(it.softfood.entity.Tavolo tavolo, Boolean occupato);
	
	boolean rimuoviTavolo(Long id); 
	
}
