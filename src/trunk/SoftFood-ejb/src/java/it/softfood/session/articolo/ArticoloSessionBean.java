package it.softfood.session.articolo;

import it.softfood.entity.Articolo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class ArticoloSessionBean implements ArticoloSessionBeanRemote, ArticoloSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public Articolo inserisciArticolo(Articolo articolo) {
		if (articolo != null) 
			em.persist(articolo);
		
		return articolo;
	}
	
	public Articolo selezionaArticoloPerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(Articolo.class, id);
	}

    public boolean rimuoviBevanda(Long id) {
        if (id != null) {
        	Articolo articolo = em.find(Articolo.class, id);
            if (articolo != null) {
                em.remove(articolo);
                return true;
            }
        }
        
        return false;
    }

}
