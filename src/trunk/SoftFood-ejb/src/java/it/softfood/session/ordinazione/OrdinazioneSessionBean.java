package it.softfood.session.ordinazione;

import it.softfood.entity.Ordinazione;
import it.softfood.entity.Tavolo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import prova.MyInter;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class OrdinazioneSessionBean implements OrdinazioneSessionBeanRemote, OrdinazioneSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;

    @Interceptors({MyInter.class})
	public Ordinazione inserisciOrdinazione(Ordinazione ordinazione) {
		try {
            em.persist(ordinazione);
			return ordinazione;
		} catch (Exception e) {
			System.err.println("OrdinazioneSessionBean#inserisciOrdinazione");
			return null;
		}
	}
	
	public Ordinazione selezionaOrdinazionePerId(Long id) {
		try {        
	        return em.find(Ordinazione.class, id);
		} catch (Exception e) {
			System.err.println("OrdinazioneSessionBean#selezionaOrdinazionePerId");
			return null;
		}
	}

	public List<Ordinazione> selezionaOrdinazioni() {    
		try {
	       return (ArrayList<Ordinazione>) em.createNamedQuery("Ordinazione.selezionaOrdinazioni")
	        	.getResultList();
		} catch (Exception e) {
			System.err.println("OrdinazioneSessionBean#selezionaOrdinazioni");
			return null;
		}
	}
	
	public List<Ordinazione> selezionaOrdinazioniPerData(Date data) {
		try {
        	return (ArrayList<Ordinazione>) em.createNamedQuery("Ordinazione.selezionaOrdinazioniPerData")
	        	.setParameter("data", data).getResultList();
		} catch (Exception e) {
			System.err.println("OrdinazioneSessionBean#selezionaOrdinazioniPerData");
			return null;
		}
	}

	public List<Ordinazione> selezionaOrdinazioniGionalierePerTavolo(Tavolo tavolo, Boolean terminato) {
        try {
	        return (ArrayList<Ordinazione>) em.createNamedQuery("Ordinazione.selezionaOrdinazioniGionalierePerTavolo")
	        	.setParameter("tavolo", tavolo).setParameter("terminato", terminato).getResultList();
		} catch (Exception e) {
			System.err.println("OrdinazioneSessionBean#selezionaOrdinazioniGionalierePerTavolo");
			return null;
		}
	}
	
    public boolean rimuoviOrdinazione(Long id) {
    	try {
            Ordinazione ordinazione = em.find(Ordinazione.class, id);
            if (ordinazione != null) {
                em.remove(ordinazione);
                return true;
            }

	        return false;
		} catch (Exception e) {
			System.err.println("OrdinazioneSessionBean#rimuoviOrdinazione");
			return false;
		}
    }

}
