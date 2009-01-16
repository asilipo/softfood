package it.softfood.session.ordinazione;

import it.softfood.entity.Ordinazione;
import it.softfood.entity.Tavolo;

import java.util.Date;
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
public class OrdinazioneSessionBean implements OrdinazioneSessionBeanRemote, OrdinazioneSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public Ordinazione inserisciOrdinazione(Ordinazione ordinazione) {
		try {
			if (ordinazione != null) 
				em.persist(ordinazione);
			
			return ordinazione;
		} catch (Exception e) {
			System.err.println("OrdinazioneSessionBean#inserisciOrdinazione");
			return null;
		}
	}
	
	public Ordinazione selezionaOrdinazionePerId(Long id) {
		try {
	        if (id == null) 
	            return null;
	        
	        return em.find(Ordinazione.class, id);
		} catch (Exception e) {
			System.err.println("OrdinazioneSessionBean#selezionaOrdinazionePerId");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Ordinazione> selezionaOrdinazioni() {    
		try {
	       return em.createNamedQuery("Ordinazione.selezionaOrdinazioni")
	        	.getResultList();
		} catch (Exception e) {
			System.err.println("OrdinazioneSessionBean#selezionaOrdinazioni");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ordinazione> selezionaOrdinazioniPerData(Date data) {
		try {
	        if (data == null) 
	            return null;
	       
        	return em.createNamedQuery("Ordinazione.selezionaOrdinazioniPerData")
	        	.setParameter("data", data).getResultList();
		} catch (Exception e) {
			System.err.println("OrdinazioneSessionBean#selezionaOrdinazioniPerData");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ordinazione> selezionaOrdinazioniGionalierePerTavolo(Tavolo tavolo, Boolean terminato) {
        try {
			if (tavolo == null || terminato == null) 
	            return null;
	       
	        return em.createNamedQuery("Ordinazione.selezionaOrdinazioniGionalierePerTavolo")
	        	.setParameter("tavolo", tavolo).setParameter("terminato", terminato).getResultList();
		} catch (Exception e) {
			System.err.println("OrdinazioneSessionBean#selezionaOrdinazioniGionalierePerTavolo");
			return null;
		}
	}
	
    public boolean rimuoviOrdinazione(Long id) {
    	try {
	        if (id != null) {
	        	Ordinazione ordinazione = em.find(Ordinazione.class, id);
	            if (ordinazione != null) {
	                em.remove(ordinazione);
	                return true;
	            }
	        }
	        
	        return false;
		} catch (Exception e) {
			System.err.println("OrdinazioneSessionBean#rimuoviOrdinazione");
			return false;
		}
    }

}