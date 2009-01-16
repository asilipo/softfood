package it.softfood.session.magazzino;

import it.softfood.entity.Magazzino;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class MagazzinoSessionBean implements MagazzinoSessionBeanRemote, MagazzinoSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public Magazzino inserisciMagazzino(Magazzino magazzino) {
		if (magazzino != null) 
			em.persist(magazzino);
		
		return magazzino;
	}
	
	public Magazzino selezionaMagazzinoPerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(Magazzino.class, id);
	}
	
    public boolean rimuoviMagazzino(Long id) {
        if (id != null) {
        	Magazzino magazzino = em.find(Magazzino.class, id);
            if (magazzino != null) {
                em.remove(magazzino);
                return true;
            }
        }
        
        return false;
    }

}
