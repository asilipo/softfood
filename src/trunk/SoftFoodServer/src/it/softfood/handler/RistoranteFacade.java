package it.softfood.handler;

import it.softfood.entity.Indirizzo;
import it.softfood.entity.Ristorante;
import it.softfood.entity.User;
import it.softfood.session.IndirizzoSession;
import it.softfood.session.RistoranteSession;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class RistoranteFacade  {

	private static RistoranteFacade singleton;
	private RistoranteSession ristoranteSession = RistoranteSession.getInstance();
	private IndirizzoSession indirizzoSession = IndirizzoSession.getInstance();
	
	public RistoranteFacade() {}
	
	public synchronized static RistoranteFacade getInstance() {
		if (singleton == null) {
			singleton = new RistoranteFacade();
		}
		
		return singleton;
	}

	public Ristorante inserisciRistorante(User user, Ristorante ristorante) 
	{
		if (ristorante.getIndirizzo()!=null &&  user != null && ristorante != null && ristorante.getRagioneSociale() != null &&
				!ristorante.getRagioneSociale().equals("") && ristorante.getPartitaIva() != null &&
					!ristorante.getPartitaIva().equals("")) {
			Indirizzo indirizzo = ristorante.getIndirizzo();
			if (indirizzo != null) {
				try {
					String via = indirizzo.getVia();
					via = indirizzo.getCitta();
					via = indirizzo.getCap();
					via = indirizzo.getProvincia();
					via = indirizzo.getCivico();
					
					if (indirizzo.getVia() != null && !indirizzo.getVia().equals("") &&
							indirizzo.getCitta() != null && !indirizzo.getCitta().equals("") &&
								indirizzo.getCap() != null && !indirizzo.getCap().equals("") &&
									indirizzo.getCivico() != null && !indirizzo.getCivico().equals("") &&
										indirizzo.getProvincia() != null && !indirizzo.getProvincia().equals("")) {
						Integer.parseInt(ristorante.getPartitaIva());
						if (ristorante.getPartitaIva().length() != 11)
							return null;
						
						Integer.parseInt(ristorante.getIndirizzo().getCap());
						if (ristorante.getIndirizzo().getCap().length() != 5)
							return null;
						
						if (ristorante.getIndirizzo().getProvincia().length() != 2)
							return null;
						
						indirizzo = indirizzoSession.inserisciIndirizzo(ristorante.getIndirizzo());
					} else {
						indirizzo = null;
					}
				} catch (NumberFormatException nfe) {
					return null;
				}
			}

			if (indirizzo != null) {
				ristorante = ristoranteSession.inserisciRistorante(ristorante);
				return ristorante;
			}
		}
		
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
		if (user != null && ragioneSociale != null && !ragioneSociale.equals("")) 
			return ristoranteSession.selezionaRistorantePerRagioneSociale(ragioneSociale);
					
		return null;
	}
	
	public Ristorante selezionaRistorantePerPartitaIva(User user, String partitaIva) {
		if (user != null && partitaIva != null && partitaIva.length() == 11) {
			try {
				Integer.parseInt(partitaIva);
				return ristoranteSession.selezionaRistorantePerPartitaIva(partitaIva);
			}
			catch(NumberFormatException nfe) {
				return null;
			}
		}
		
		return null;
	}

	public boolean rimuoviRistorante(User user, String ragioneSociale) {
		if (user != null && ragioneSociale != null && ragioneSociale != "") {
			Ristorante ristorante = ristoranteSession.selezionaRistorantePerRagioneSociale(ragioneSociale);
			
			boolean state = ristoranteSession.rimuoviRistorante(ragioneSociale);			
			if (state && ristorante != null && ristorante.getIndirizzo() != null) 
				indirizzoSession.rimuoviIndirizzo(ristorante.getIndirizzo().getId());
			
			return state;
		}
		
		return false;
	}
	
}
