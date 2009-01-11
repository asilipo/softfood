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
		if (ordinazione != null) 
			em.persist(ordinazione);
		
		return ordinazione;
	}
	
	public Ordinazione selezionaOrdinazionePerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(Ordinazione.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Ordinazione> selezionaOrdinazioni() {       
       return em.createNamedQuery("Ordinazione.selezionaOrdinazioni")
        	.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Ordinazione> selezionaOrdinazioniPerData(Date data) {
        if (data == null) 
            return null;
       
       return em.createNamedQuery("Ordinazione.selezionaOrdinazioniPerData")
        	.setParameter("data", data).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Ordinazione> selezionaOrdinazioniGionalierePerTavolo(Tavolo tavolo, Boolean terminato) {
        if (tavolo == null || terminato == null) 
            return null;
       
       return em.createNamedQuery("Ordinazione.selezionaOrdinazioniGionalierePerTavolo")
        	.setParameter("tavolo", tavolo).setParameter("terminato", terminato).getResultList();
	}
	
    public boolean rimuoviOrdinazione(Long id) {
        if (id != null) {
        	Ordinazione ordinazione = em.find(Ordinazione.class, id);
            if (ordinazione != null) {
                em.remove(ordinazione);
                return true;
            }
        }
        
        return false;
    }

}
