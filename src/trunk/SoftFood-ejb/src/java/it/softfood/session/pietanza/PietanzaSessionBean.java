package it.softfood.session.pietanza;

import it.softfood.entity.Pietanza;

import it.softfood.enumeration.TipoPietanza;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class PietanzaSessionBean implements PietanzaSessionBeanRemote, PietanzaSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;
    
	public Pietanza inserisciPietanza(Pietanza pietanza) {
		try {
			em.persist(pietanza);
            return pietanza;
        } catch (Exception e) {
            System.err.println("PietanzaSessionBean#inserisciPietanza");
            return null;
        }
	}
	
	public Pietanza selezionaPietanzaPerId(Long id) {
        try {
            return em.find(Pietanza.class, id);
        } catch (Exception e) {
            System.err.println("PietanzaSessionBean#selezionaPietanzaPerId");
            return null;
        }
	}

    @SuppressWarnings("unchecked")
    public List<Pietanza> selezionaPietanzePerTipo(TipoPietanza tipoPietanza) {
		try {
	        return (ArrayList<Pietanza>) em.createNamedQuery("Pietanza.selezionaPietanzePerTipo")
	        	.setParameter("tipo", tipoPietanza).getResultList();
		} catch (Exception e) {
			System.err.println("PietanzaSessionBean#selezionaPietanzePerTipo");
			return null;
        }
	}

    @SuppressWarnings("unchecked")
    public List<Pietanza> selezionaPietanze() {
		try {
	        return (ArrayList<Pietanza>) em.createNamedQuery("Pietanza.selezionaPietanze")
	        	.getResultList();
		} catch (Exception e) {
			System.err.println("PietanzaSessionBean#selezionaPietanze");
			return null;
        }
	}

    public boolean rimuoviPietanza(Long id) {
        try {
        	Pietanza pietanza = em.find(Pietanza.class, id);
            if (pietanza != null) {
                em.remove(pietanza);
                return true;
            }

            return false;
         } catch (Exception e) {
            System.err.println("PietanzaSessionBean#rimuoviPietanza");
            return false;
         }
    }
    
}

