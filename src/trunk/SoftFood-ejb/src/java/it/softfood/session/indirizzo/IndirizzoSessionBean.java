package it.softfood.session.indirizzo;

import it.softfood.entity.Indirizzo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class IndirizzoSessionBean implements IndirizzoSessionBeanRemote, IndirizzoSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public Indirizzo inserisciIndirizzo(Indirizzo indirizzo) {
		try {
			em.persist(indirizzo);
            return indirizzo;
        } catch (Exception e) {
            System.err.println("IndirizzoSessionBean#inserisciIndirizzo");
            return null;
        }
	}
	
	public Indirizzo selezionaIndirizzoPerId(Long id) {
        try {
            return em.find(Indirizzo.class, id);
        } catch (Exception e) {
            System.err.println("IndirizzoSessionBean#selezionaIndirizzoPerId");
            return null;
        }
	}
	
    public boolean rimuoviIndirizzo(Long id) {
        try {
        	Indirizzo indirizzo = em.find(Indirizzo.class, id);
            if (indirizzo != null) {
                em.remove(indirizzo);
                return true;
            }

            return false;
        } catch (Exception e) {
            System.err.println("IndirizzoSessionBean#rimuoviIndirizzo");
            return false;
        }
    }

}
