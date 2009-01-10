package it.softfood.session.ristorante;

import it.softfood.entity.Ristorante;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface RistoranteSessionBeanRemote {

	it.softfood.entity.Ristorante inserisciRistorante(Ristorante ristorante);
	
	it.softfood.entity.Ristorante selezionaRistorantePerRagioneSociale(String ragioneSociale);
	
	java.util.List<it.softfood.entity.Ristorante> selezionaRistorantePerPartitaIva(String partitaIva);
	
	boolean rimuoviRistorante(String partitaIva);
	
}
