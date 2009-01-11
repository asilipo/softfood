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
		if (indirizzo != null) 
			em.persist(indirizzo);
		
		return indirizzo;
	}
	
	public Indirizzo selezionaIndirizzoPerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(Indirizzo.class, id);
	}
	
    public boolean rimuoviIndirizzo(Long id) {
        if (id != null) {
        	Indirizzo indirizzo = em.find(Indirizzo.class, id);
            if (indirizzo != null) {
                em.remove(indirizzo);
                return true;
            }
        }
        
        return false;
    }

}
