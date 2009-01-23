package it.softfood.session.bevandamagazzino;

import it.softfood.entity.BevandaMagazzino;

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
public class BevandaMagazzinoSessionBean implements BevandaMagazzinoSessionBeanRemote, BevandaMagazzinoSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public BevandaMagazzino inserisciBevandaMagazzino(BevandaMagazzino bevandaMagazzino) {
		try {
			em.persist(bevandaMagazzino);
            return bevandaMagazzino;
        } catch (Exception e) {
            System.err.println("BevandaMagazzino#inserisciBevandaMagazzino");
            return null;
        }
	}
	
	public BevandaMagazzino selezionaBevandaMagazzinoPerId(Long id) {
        try {
            return em.find(BevandaMagazzino.class, id);
        } catch (Exception e) {
            System.err.println("BevandaMagazzino#selezionaBevandaMagazzinoPerId");
            return null;
        }
	}

	public List<BevandaMagazzino> selezionaBevandeMagazzino() {
        try {
            return (ArrayList<BevandaMagazzino>) em.createNamedQuery("BevandaMagazzino.selezionaBevandeMagazzino")
                .getResultList();
        } catch (Exception e) {
            System.err.println("BevandaMagazzino#selezionaBevandeMagazzino");
            return null;
        }
	}
	
	public List<BevandaMagazzino> selezionaBevandeMagazzinoPerQuantita(Integer quantita) {
        try {
            if (quantita == null || quantita < 0)
                quantita = 0;

            return (ArrayList<BevandaMagazzino>) em.createNamedQuery("BevandaMagazzino.selezionaBevandeMagazzinoPerQuantita")
                .setParameter("quantita", quantita).getResultList();
        } catch (Exception e) {
            System.err.println("BevandaMagazzino#selezionaBevandeMagazzinoPerQuantita");
            return null;
        }
	}
	
    public boolean rimuoviBevandaMagazzino(Long id) {
        try {
        	BevandaMagazzino bevandaMagazzino = em.find(BevandaMagazzino.class, id);
            if (bevandaMagazzino != null) {
                em.remove(bevandaMagazzino);
                return true;
            }

            return false;
        } catch (Exception e) {
            System.err.println("BevandaMagazzino#rimuoviBevandaMagazzino");
            return false;
        }
    }

}
