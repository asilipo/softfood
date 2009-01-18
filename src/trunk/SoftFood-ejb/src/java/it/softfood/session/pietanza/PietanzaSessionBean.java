package it.softfood.session.pietanza;

import it.softfood.entity.Pietanza;

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

