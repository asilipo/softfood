package it.softfood.facade.ordinazione;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface OrdinazioneFacadeLocal {

	it.softfood.entity.Ordinazione inserisciOrdinazione(it.softfood.entity.Ordinazione ordinazione);
	
	it.softfood.entity.Ordinazione selezionaOrdinazione(Long id);
	
	java.util.List<it.softfood.entity.Ordinazione> selezionaOrdinazioni();
	
	java.util.List<it.softfood.entity.Ordinazione> selezionaOrdinazioniPerData(java.util.Date data);
	
	java.util.List<it.softfood.entity.Ordinazione> selezionaOrdinazioniGiornalierePerTavolo(it.softfood.entity.Tavolo tavolo, Boolean terminato);
	
	boolean rimuoviOrdinazione(Long id);
	
	it.softfood.entity.LineaOrdinazione inserisciLineaOrdinazione(it.softfood.entity.LineaOrdinazione lineaOrdinazione);
	
	it.softfood.entity.LineaOrdinazione modificaLineaOrdinazione(
			it.softfood.entity.LineaOrdinazione nuovaLineaOrdinazione, it.softfood.entity.LineaOrdinazione vecchiaLineaOrdinazione);
	
}
