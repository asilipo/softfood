package it.softfood.session.bevandamagazzino;

import it.softfood.entity.BevandaMagazzino;

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
		if (bevandaMagazzino != null) 
			em.persist(bevandaMagazzino);
		
		return bevandaMagazzino;
	}
	
	public BevandaMagazzino selezionaBevandaMagazzinoPerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(BevandaMagazzino.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<BevandaMagazzino> selezionaBevandeMagazzino() {       
       return em.createNamedQuery("BevandaMagazzino.selezionaBevandeMagazzino")
        	.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<BevandaMagazzino> selezionaBevandeMagazzinoPerQuantita(Integer quantita) {
        if (quantita == null || quantita < 0) 
            quantita = 0;
       
       return em.createNamedQuery("BevandaMagazzino.selezionaBevandeMagazzinoPerQuantita")
        	.setParameter("quantita", quantita).getResultList();
	}
	
    public boolean rimuoviBevandaMagazzino(Long id) {
        if (id != null) {
        	BevandaMagazzino bevandaMagazzino = em.find(BevandaMagazzino.class, id);
            if (bevandaMagazzino != null) {
                em.remove(bevandaMagazzino);
                return true;
            }
        }
        
        return false;
    }

}
