package it.softfood.session.tavolo;

import it.softfood.entity.Tavolo;

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
public class TavoloSessionBean implements TavoloSessionBeanRemote, TavoloSessionBeanLocal {

	@PersistenceContext
	private EntityManager em;
	
	public Tavolo inserisciTavolo(Tavolo tavolo) {
		try {
            em.persist(tavolo);
			return tavolo;
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#inserisciTavolo");
			return null;
		}
	}
	
	public Tavolo selezionaTavoloPerId(Long id) {
		try {        
	        return em.find(Tavolo.class, id);
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#selezionaTavoloPerId");
			return null;
		}
	}

    public Tavolo selezionaTavoloPerRiferimento(String riferimento) {
		try {
	        return (Tavolo) em.createNamedQuery("Tavolo.selezionaTavoloPerRiferimento")
	        	.setParameter("riferimento", riferimento).getSingleResult();
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#selezionaTavoloPerRiferimento");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Tavolo> selezionaTavoliPerNumeroPosti(Integer numeroPosti) {
		try {	       
	        return (ArrayList<Tavolo>) em.createNamedQuery("Tavolo.selezionaTavoliPerNumeroPosti")
	        	.setParameter("numero_posti", numeroPosti).getResultList();
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#selezionaTavoliPerNumeroPosti");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Tavolo> selezionaTavoliLiberi() {    
		try {
	       return (ArrayList<Tavolo>) em.createNamedQuery("Tavolo.selezionaTavoliLiberi")
	        	.getResultList();
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#selezionaTavoliLiberi");
			return null;
		}
	}

    @SuppressWarnings("unchecked")
	public List<Tavolo> selezionaTavoliOccupati() {
		try {
	       return (ArrayList<Tavolo>) em.createNamedQuery("Tavolo.selezionaTavoliOccupati")
	        	.getResultList();
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#selezionaTavoliOccupati");
			return null;
		}
	}

    @SuppressWarnings("unchecked")
	public List<Tavolo> selezionaTavoliNonAttivi() {
		try {
	        return (ArrayList<Tavolo>) em.createNamedQuery("Tavolo.selezionaTavoliAttivi")
	        	.getResultList();
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#selezionaTavoliNonAttivi");
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
            Tavolo tavolo = em.find(Tavolo.class, id);
            if (tavolo != null) {
                em.remove(tavolo);
                return true;
            }
	        
	        return false;
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#rimuoviTavolo");
			return false;
		}
    }
    
}
