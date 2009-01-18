package it.softfood.session.ristorante;

import it.softfood.entity.Ristorante;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class RistoranteSessionBean implements RistoranteSessionBeanRemote, RistoranteSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public Ristorante inserisciRistorante(Ristorante ristorante) {
		try {
            em.persist(ristorante);
			return ristorante;
		} catch (Exception e) {
			System.err.println("RistoranteSessionBean#inserisciRistorante");
			return null;
		}
	}
	
	public Ristorante selezionaRistorantePerRagioneSociale(String ragioneSociale) {
        try {        
	        return em.find(Ristorante.class, ragioneSociale);
		} catch (Exception e) {
			System.err.println("RistoranteSessionBean#selezionaRistorantePerRagioneSociale");
			return null;
		}
	}
	
	public Ristorante selezionaRistorantePerPartitaIva(String partitaIva) {
        try {
			return (Ristorante) em.createNamedQuery("Ristorante.selezionaRistorantePerPartitaIva")
	        	.setParameter("partita_iva", partitaIva).getSingleResult();
        } catch (Exception e) {
			System.err.println("RistoranteSessionBean#selezionaRistorantePerPartitaIva");
			return null;
		}
	}
	
    public boolean rimuoviRistorante(String ragioneSociale) {
        try {
            Ristorante ristorante = em.find(Ristorante.class, ragioneSociale);
            if (ristorante != null) {
                em.remove(ristorante);
                return true;
            }

            return false;
        } catch (Exception e) {
			System.err.println("RistoranteSessionBean#rimuoviRistorante");
			return false;
		}
    }
    
}
