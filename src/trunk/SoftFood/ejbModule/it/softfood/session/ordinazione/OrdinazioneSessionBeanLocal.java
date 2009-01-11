package it.softfood.session.ordinazione;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface OrdinazioneSessionBeanLocal {
	
	it.softfood.entity.Ordinazione inserisciOrdinazione(it.softfood.entity.Ordinazione ordinazione);
	
	it.softfood.entity.Ordinazione selezionaOrdinazionePerId(Long id);
	
	java.util.List<it.softfood.entity.Ordinazione> selezionaOrdinazioni();
	
	java.util.List<it.softfood.entity.Ordinazione> selezionaOrdinazioniPerData(java.util.Date data);
	
	java.util.List<it.softfood.entity.Ordinazione> selezionaOrdinazioniGionalierePerTavolo(it.softfood.entity.Tavolo tavolo, Boolean terminato);
	
	boolean rimuoviOrdinazione(Long id);

}
