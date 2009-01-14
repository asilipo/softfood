package it.softfood.facade.ristorante;

import it.softfood.entity.Ristorante;
import it.softfood.session.ristorante.RistoranteSessionBeanLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class RistoranteFacade implements RistoranteFacadeRemote, RistoranteFacadeLocal {

	@PersistenceContext
    private EntityManager em;
	@EJB(beanName = "RistoranteSessionBean", mappedName = "it.sofdfood.session.ristorante.RistoranteSessionBean")
	private RistoranteSessionBeanLocal ristoranteSessionBean;
	
	public Ristorante inserisciRistorante(Ristorante ristorante) {
		if (ristorante != null)
			return ristoranteSessionBean.inserisciRistorante(ristorante);
		
		return null;
	}
	
	public Ristorante modificaRistorante(Ristorante nuovoRistorante, Ristorante vecchioRistorante) {
		if (nuovoRistorante != null && vecchioRistorante != null) {
			Ristorante ristorante = em.merge(vecchioRistorante);
			ristorante.setIndirizzo(nuovoRistorante.getIndirizzo());
			ristorante.setPartitaIva(nuovoRistorante.getPartitaIva());
		}
		
		return null;
	}
	
	public Ristorante selezionaRistorantePerRagioneSociale(String ragioneSociale) {
		if (ragioneSociale != null)
			return ristoranteSessionBean.selezionaRistorantePerRagioneSociale(ragioneSociale);
		
		return null;
	}
	
	public Ristorante selezionaRistorantePerPartitaIva(String partitaIva) {
		if (partitaIva != null)
			return ristoranteSessionBean.selezionaRistorantePerPartitaIva(partitaIva);
		
		return null;
	}
	
	public boolean rimuoviRistorante(String ragioneSociale) {
		if (ragioneSociale != null)
			return ristoranteSessionBean.rimuoviRistorante(ragioneSociale);
		
		return false;
	}
	
}
