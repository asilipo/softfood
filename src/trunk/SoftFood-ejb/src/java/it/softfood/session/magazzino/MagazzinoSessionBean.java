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
		try {
			em.persist(magazzino);
            return magazzino;
        } catch (Exception e) {
            System.err.println("MagazzinoSessionBean#inserisciMagazzino");
            return null;
        }
	}
	
	public Magazzino selezionaMagazzinoPerId(Long id) {
        try {
            return em.find(Magazzino.class, id);
        } catch (Exception e) {
            System.err.println("MagazzinoSessionBean#selezionaMagazzinoPerId");
            return null;
        }
	}
	
    public boolean rimuoviMagazzino(Long id) {
        try {
        	Magazzino magazzino = em.find(Magazzino.class, id);
            if (magazzino != null) {
                em.remove(magazzino);
                return true;
            }

            return false;
        } catch (Exception e) {
            System.err.println("MagazzinoSessionBean#rimuoviMagazzino");
            return false;
        }
    }

}
