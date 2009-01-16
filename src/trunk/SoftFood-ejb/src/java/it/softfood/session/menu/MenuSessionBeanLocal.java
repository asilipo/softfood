package it.softfood.session.menu;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface MenuSessionBeanLocal {

	it.softfood.entity.Menu inserisciMenu(it.softfood.entity.Menu menu);
	
	it.softfood.entity.Menu selezionaMenuPerId(Long id);
	
	boolean rimuoviMenu(Long id);
	
}
