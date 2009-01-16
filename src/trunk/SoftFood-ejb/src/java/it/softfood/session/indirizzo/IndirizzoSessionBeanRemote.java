package it.softfood.session.indirizzo;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface IndirizzoSessionBeanRemote {

	it.softfood.entity.Indirizzo inserisciIndirizzo(it.softfood.entity.Indirizzo indirizzo);
	
	it.softfood.entity.Indirizzo selezionaIndirizzoPerId(Long id);
	
	boolean rimuoviIndirizzo(Long id);
	
}
