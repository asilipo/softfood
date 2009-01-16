package it.softfood.session.listino;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface ListinoSessionBeanLocal {

	it.softfood.entity.Listino inserisciListino(it.softfood.entity.Listino listino);
	
	it.softfood.entity.Listino selezionaListinoPerId(Long id);
	
	java.util.List<it.softfood.entity.Articolo> selezionaArticoli();
	
	boolean rimuoviListino(Long id);
	
}
