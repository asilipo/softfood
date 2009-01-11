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
		if (ristorante != null) 
			em.persist(ristorante);
		
		return ristorante;
	}
	
	public Ristorante selezionaRistorantePerRagioneSociale(String ragioneSociale) {
        if (ragioneSociale == null) 
            return null;
        
        return em.find(Ristorante.class, ragioneSociale);
	}
	
	public Ristorante selezionaRistorantePerPartitaIva(String partitaIva) {
        if (partitaIva == null) 
            return null;
       
       return (Ristorante) em.createNamedQuery("Ristorante.selezionaRistorantePerPartitaIva")
        	.setParameter("partita_iva", partitaIva).getSingleResult();
	}
	
    public boolean rimuoviRistorante(String partitaIva) {
        if (partitaIva != null) {
        	Ristorante ristorante = em.find(Ristorante.class, partitaIva);
            if (ristorante != null) {
                em.remove(ristorante);
                return true;
            }
        }
        
        return false;
    }
    
}
