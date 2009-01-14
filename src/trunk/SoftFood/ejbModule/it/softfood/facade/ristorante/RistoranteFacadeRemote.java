package it.softfood.facade.ristorante;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface RistoranteFacadeRemote {

	it.softfood.entity.Ristorante inserisciRistorante(it.softfood.entity.Ristorante ristorante);
	
	it.softfood.entity.Ristorante modificaRistorante(it.softfood.entity.Ristorante nuovoRistorante, it.softfood.entity.Ristorante vecchioRistorante);
	
	it.softfood.entity.Ristorante selezionaRistorantePerRagioneSociale(String ragioneSociale);
	
	it.softfood.entity.Ristorante selezionaRistorantePerPartitaIva(String partitaIva);
	
	boolean rimuoviRistorante(String ragioneSociale);
	
}
