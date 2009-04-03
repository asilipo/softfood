package it.softfood.handler;

import it.softfood.entity.Ristorante;
import it.softfood.session.RistoranteSession;


/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */


public class RistoranteFacade  {

	private static RistoranteFacade singleton;

	private RistoranteSession ristoranteSession=RistoranteSession.getInstance();
	
	public RistoranteFacade() {
	}
	
	public synchronized static RistoranteFacade getInstance() {
		if (singleton == null) {
			singleton = new RistoranteFacade();
		}
		return singleton;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.IRistoranteSession#inserisciRistorante(it.softfood.entity.Ristorante)
	 */
	public Ristorante inserisciRistorante(Ristorante ristorante) {
		if (ristorante != null)
			return ristoranteSession.inserisciRistorante(ristorante);
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.IRistoranteSession#modificaRistorante(it.softfood.entity.Ristorante, it.softfood.entity.Ristorante)
	 */
	public Ristorante modificaRistorante(Ristorante nuovoRistorante, Ristorante vecchioRistorante) {
		if (nuovoRistorante != null && vecchioRistorante != null) {
			Ristorante ristorante = vecchioRistorante;
			ristorante.setIndirizzo(nuovoRistorante.getIndirizzo());
			ristorante.setPartitaIva(nuovoRistorante.getPartitaIva());
			ristoranteSession.update(ristorante);
            return ristorante;
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.IRistoranteSession#selezionaRistorantePerRagioneSociale(java.lang.String)
	 */
	public Ristorante selezionaRistorantePerRagioneSociale(String ragioneSociale) {
		if (ragioneSociale != null)
			return ristoranteSession.selezionaRistorantePerRagioneSociale(ragioneSociale);
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.IRistoranteSession#selezionaRistorantePerPartitaIva(java.lang.String)
	 */
	public Ristorante selezionaRistorantePerPartitaIva(String partitaIva) {
		if (partitaIva != null)
			return ristoranteSession.selezionaRistorantePerPartitaIva(partitaIva);
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.IRistoranteSession#rimuoviRistorante(java.lang.String)
	 */
	public boolean rimuoviRistorante(String ragioneSociale) {
		if (ragioneSociale != null)
			return ristoranteSession.rimuoviRistorante(ragioneSociale);
		
		return false;
	}
	
}
