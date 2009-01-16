package it.softfood.session.articolo;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface ArticoloSessionBeanLocal {

	it.softfood.entity.Articolo inserisciArticolo(it.softfood.entity.Articolo articolo);
	
	it.softfood.entity.Articolo selezionaArticoloPerId(Long id);
	
	boolean rimuoviBevanda(Long id);
	
}
