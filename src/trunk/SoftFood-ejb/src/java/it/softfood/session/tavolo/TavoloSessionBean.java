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
		try {
			if (tavolo != null)
				em.persist(tavolo);
			
			return tavolo;
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#inserisciTavolo");
			return null;
		}
	}
	
	public Tavolo selezionaTavoloPerId(Long id) {
		try {
			if (id != null) 
				return null;
	        
	        return em.find(Tavolo.class, id);
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#selezionaTavoloPerId");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Tavolo> selezionaTavoliPerNumeroPosti(Integer numeroPosti) {
		try {
	        if (numeroPosti == null) 
	            return null;
	       
	        return em.createNamedQuery("Tavolo.selezionaTavoliPerNumeroPosti")
	        	.setParameter("numero_posti", numeroPosti).getResultList();
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#selezionaTavoliPerNumeroPosti");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Tavolo> selezionaTavoliLiberi() {    
		try {
	       return em.createNamedQuery("Tavolo.selezionaTavoliLiberi")
	        	.getResultList();
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#selezionaTavoliLiberi");
			return null;
		}
	}
	
	public boolean modificaStatoTavolo(Tavolo tavolo, Boolean occupato) {
		try {
			tavolo.setOccupato(occupato);
			tavolo = em.merge(tavolo);
			
			return true;
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#modificaStatoTavolo");
			return false;
		}
    }
	
    public boolean rimuoviTavolo(Long id) {
    	try {
	        if (id != null) {
	        	Tavolo tavolo = em.find(Tavolo.class, id);
	            if (tavolo != null) {
	                em.remove(tavolo);
	                return true;
	            }
	        }
	        
	        return false;
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#rimuoviTavolo");
			return false;
		}
    }
    
}
