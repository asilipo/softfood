package it.softfood.session.tavolo;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface TavoloSessionBeanLocal {

	it.softfood.entity.Tavolo inserisciTavolo(it.softfood.entity.Tavolo tavolo);

	it.softfood.entity.Tavolo selezionaTavoloPerId(Long id);

	java.util.List<it.softfood.entity.Tavolo> selezionaTavoliPerNumeroPosti(Integer numeroPosti);

	java.util.List<it.softfood.entity.Tavolo> selezionaTavoliLiberi();

    java.util.List<it.softfood.entity.Tavolo> selezionaTavoliOccupati();
	
	boolean modificaStatoTavolo(it.softfood.entity.Tavolo tavolo, Boolean occupato);

    java.util.List<it.softfood.entity.Tavolo> selezionaTavoliNonAttivi();

    it.softfood.entity.Tavolo selezionaTavoloPerRiferimento(java.lang.String riferimento);

    boolean rimuoviTavolo(Long id);
    
}
