package it.softfood.session.entrata;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface EntrataSessionBeanRemote {
	
	it.softfood.entity.Entrata inserisciEntrata(it.softfood.entity.Entrata entrata);
	
	it.softfood.entity.Entrata selezionaEntrataPerId(Long id);
	
	it.softfood.entity.Ordinazione selezionaBevandeMagazzinoEntrataPerOrdinazione(it.softfood.entity.Ordinazione ordinazione);
	
	boolean rimuoviEntrata(Long id);

}
