package it.softfood.session.ristorante;

import it.softfood.entity.Ristorante;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface RistoranteSessionBeanLocal {

	it.softfood.entity.Ristorante inserisciRistorante(Ristorante ristorante);
	
	it.softfood.entity.Ristorante selezionaRistorantePerRagioneSociale(String ragioneSociale);
	
	it.softfood.entity.Ristorante selezionaRistorantePerPartitaIva(String partitaIva);
	
	boolean rimuoviRistorante(String ragioneSociale);
	
}
