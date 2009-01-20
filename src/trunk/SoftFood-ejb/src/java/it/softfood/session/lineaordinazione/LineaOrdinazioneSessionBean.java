package it.softfood.session.lineaordinazione;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;

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
public class LineaOrdinazioneSessionBean implements LineaOrdinazioneSessionBeanRemote, LineaOrdinazioneSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public LineaOrdinazione inserisciLineaOrdinazione(LineaOrdinazione lineaOrdinazione) {
        try {
            em.persist(lineaOrdinazione);
            return lineaOrdinazione;
        } catch (Exception e) {
            System.err.println("LineaOrdinazioneSessionBean#inserisciLineaOrdinazione");
            return null;
        }
	}
	
	public LineaOrdinazione selezionaLineaOrdinazionePerId(Long id) {
        try {
            return em.find(LineaOrdinazione.class, id);
        } catch (Exception e) {
            System.err.println("LineaOrdinazioneSessionBean#selezionaLineaOrdinazionePerId");
            return null;
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(Ordinazione ordinazione) {
        try {
           return (ArrayList<LineaOrdinazione>) em.createNamedQuery("LineaOrdinazione.selezionaLineeOrdinazionePerOrdinazione")
                .setParameter("ordinazione", ordinazione).getResultList();
        } catch (Exception e) {
            System.err.println("LineaOrdinazioneSessionBean#selezionaLineeOrdinazionePerOrdinazione");
            return null;
        }
	}
	
    public boolean rimuoviLineaOrdinazione(Long id) {
        try {
        	LineaOrdinazione lineaOrdinazione = em.find(LineaOrdinazione.class, id);
            if (lineaOrdinazione != null)
                em.remove(lineaOrdinazione);
                
            return true;
        } catch (Exception e) {
            System.err.println("LineaOrdinazioneSessionBean#rimuoviLineaOrdinazione");
            return false;
        }
    }
    
}
