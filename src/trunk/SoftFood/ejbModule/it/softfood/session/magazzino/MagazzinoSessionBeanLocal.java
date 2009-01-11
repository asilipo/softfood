package it.softfood.session.magazzino;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface MagazzinoSessionBeanLocal {

	it.softfood.entity.Magazzino inserisciMagazzino(it.softfood.entity.Magazzino magazzino);
	
	it.softfood.entity.Magazzino selezionaMagazzinoPerId(Long id);
	
	boolean rimuoviMagazzino(Long id);
	
}
