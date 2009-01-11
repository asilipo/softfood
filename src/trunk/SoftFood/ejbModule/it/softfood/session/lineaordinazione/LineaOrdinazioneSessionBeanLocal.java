package it.softfood.session.lineaordinazione;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface LineaOrdinazioneSessionBeanLocal {

	it.softfood.entity.LineaOrdinazione inserisciLineaOrdinazione(it.softfood.entity.LineaOrdinazione lineaOrdinazione);
	
	it.softfood.entity.LineaOrdinazione selezionaLineaOrdinazionePerId(Long id);
	
	java.util.List<it.softfood.entity.LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(it.softfood.entity.Ordinazione ordinazione);
	
	boolean rimuoviLineaOrdinazione(Long id);
	
}
