package it.softfood.session.menu;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface MenuSessionBeanRemote {

	it.softfood.entity.Menu inserisciMenu(it.softfood.entity.Menu menu);
	
	it.softfood.entity.Menu selezionaMenuPerId(Long id);
	
	boolean rimuoviMenu(Long id);
	
}
