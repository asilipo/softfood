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
		try {
			em.persist(articolo);		
            return articolo;
        } catch (Exception e) {
            System.err.println("ArticoloSessionBean#inserisciArticolo");
            return null;
        }
	}
	
	public Articolo selezionaArticoloPerId(Long id) {
        try {
            return em.find(Articolo.class, id);
        } catch (Exception e) {
            System.err.println("ArticoloSessionBean#selezionaArticoloPerId");
            return null;
        }
	}

    public boolean rimuoviBevanda(Long id) {
        try {
        	Articolo articolo = em.find(Articolo.class, id);
            if (articolo != null) {
                em.remove(articolo);
                return true;
            }

            return false;
        } catch (Exception e) {
            System.err.println("ArticoloSessionBean#rimuoviBevanda");
            return false;
        }
    }

}
