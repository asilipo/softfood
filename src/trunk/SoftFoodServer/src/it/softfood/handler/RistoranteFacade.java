package it.softfood.handler;

import it.softfood.entity.Ristorante;
import it.softfood.entity.User;
import it.softfood.session.RistoranteSession;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class RistoranteFacade  {

	private static RistoranteFacade singleton;
	private RistoranteSession ristoranteSession = RistoranteSession.getInstance();
	
	public RistoranteFacade() {}
	
	public synchronized static RistoranteFacade getInstance() {
		if (singleton == null) {
			singleton = new RistoranteFacade();
		}
		return singleton;
	}

	public Ristorante inserisciRistorante(User user, Ristorante ristorante) {
		if (user != null && ristorante != null)
			return ristoranteSession.inserisciRistorante(ristorante);
		
		return null;
	}
	
	public Ristorante modificaRistorante(User user, Ristorante nuovoRistorante, Ristorante vecchioRistorante) {
		if (user != null && nuovoRistorante != null && vecchioRistorante != null) {
			Ristorante ristorante = vecchioRistorante;
			ristorante.setIndirizzo(nuovoRistorante.getIndirizzo());
			ristorante.setPartitaIva(nuovoRistorante.getPartitaIva());
			ristoranteSession.update(ristorante);
            return ristorante;
		}
		
		return null;
	}

	public Ristorante selezionaRistorantePerRagioneSociale(User user, String ragioneSociale) {
		if (user != null && ragioneSociale != null)
			return ristoranteSession.selezionaRistorantePerRagioneSociale(ragioneSociale);
		
		return null;
	}
	
	public Ristorante selezionaRistorantePerPartitaIva(User user, String partitaIva) {
		if (user != null && partitaIva != null)
			return ristoranteSession.selezionaRistorantePerPartitaIva(partitaIva);
		
		return null;
	}

	public boolean rimuoviRistorante(User user, String ragioneSociale) {
		if (user != null && ragioneSociale != null)
			return ristoranteSession.rimuoviRistorante(ragioneSociale);
		
		return false;
	}
	
}
