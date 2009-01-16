package it.softfood.session.magazzino;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface MagazzinoSessionBeanRemote {

	it.softfood.entity.Magazzino inserisciMagazzino(it.softfood.entity.Magazzino magazzino);
	
	it.softfood.entity.Magazzino selezionaMagazzinoPerId(Long id);
	
	boolean rimuoviMagazzino(Long id);
	
}
