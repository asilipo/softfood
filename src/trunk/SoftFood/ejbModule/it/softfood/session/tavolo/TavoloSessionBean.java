package it.softfood.session.tavolo;

import it.softfood.entity.Tavolo;

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
public class TavoloSessionBean implements TavoloSessionBeanRemote, TavoloSessionBeanLocal {

	@PersistenceContext
	private EntityManager em;
	
	public Tavolo inserisciTavolo(Tavolo tavolo) {
		if (tavolo != null)
			em.persist(tavolo);
		
		return tavolo;
	}
	
	public Tavolo selezionaTavoloPerId(Long id) {
		if (id != null) 
			return null;
        
        return em.find(Tavolo.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tavolo> selezionaTavoliPerNumeroPosti(Integer numeroPosti) {
        if (numeroPosti == null) 
            return null;
       
       return em.createNamedQuery("Tavolo.selezionaTavoliPerNumeroPosti")
        	.setParameter("numero_posti", numeroPosti).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Tavolo> selezionaTavoliLiberi() {     
       return em.createNamedQuery("Tavolo.selezionaTavoliLiberi")
        	.getResultList();
	}
	
	public void modificaStatoTavolo(Tavolo tavolo, Boolean occupato) {
		tavolo.setOccupato(occupato);
		tavolo = em.merge(tavolo);
    }
	
    public boolean rimuoviTavolo(Long id) {
        if (id != null) {
        	Tavolo tavolo = em.find(Tavolo.class, id);
            if (tavolo != null) {
                em.remove(tavolo);
                return true;
            }
        }
        
        return false;
    }
    
}
