package it.softfood.session.ordinazione;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface OrdinazioneSessionBeanRemote {

	it.softfood.entity.Ordinazione inserisciOrdinazione(it.softfood.entity.Ordinazione ordinazione);
	
	it.softfood.entity.Ordinazione selezionaOrdinazionePerId(Long id);

	java.util.List<it.softfood.entity.Ordinazione> selezionaOrdinazioni();

	java.util.List<it.softfood.entity.Ordinazione> selezionaOrdinazioniPerData(java.util.Date data);

	java.util.List<it.softfood.entity.Ordinazione> selezionaOrdinazioniGionalierePerTavolo(it.softfood.entity.Tavolo tavolo, Boolean terminato);
	
	boolean rimuoviOrdinazione(Long id);
	
}
