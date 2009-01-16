package it.softfood.session.entrata;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface EntrataSessionBeanLocal {

	it.softfood.entity.Entrata inserisciEntrata(it.softfood.entity.Entrata entrata);
	
	it.softfood.entity.Entrata selezionaEntrataPerId(Long id);
	
	it.softfood.entity.Ordinazione selezionaBevandeMagazzinoEntrataPerOrdinazione(it.softfood.entity.Ordinazione ordinazione);
	
	boolean rimuoviEntrata(Long id);
	
}
