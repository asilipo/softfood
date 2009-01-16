package it.softfood.session.listino;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface ListinoSessionBeanRemote {

	it.softfood.entity.Listino inserisciListino(it.softfood.entity.Listino listino);
	
	it.softfood.entity.Listino selezionaListinoPerId(Long id);
	
	java.util.List<it.softfood.entity.Articolo> selezionaArticoli();
	
	boolean rimuoviListino(Long id);
	
}
