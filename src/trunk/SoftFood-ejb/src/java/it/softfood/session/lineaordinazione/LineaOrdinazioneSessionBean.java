package it.softfood.session.lineaordinazione;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;

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
		if (lineaOrdinazione != null) 
			em.persist(lineaOrdinazione);
		
		return lineaOrdinazione;
	}
	
	public LineaOrdinazione selezionaLineaOrdinazionePerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(LineaOrdinazione.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(Ordinazione ordinazione) {
        if (ordinazione == null) 
            return null;
       
       return em.createNamedQuery("LineaOrdinazione.selezionaLineeOrdinazionePerOrdinazione")
        	.setParameter("ordinazione", ordinazione).getResultList();
	}
	
    public boolean rimuoviLineaOrdinazione(Long id) {
        if (id != null) {
        	LineaOrdinazione lineaOrdinazione = em.find(LineaOrdinazione.class, id);
            if (lineaOrdinazione != null) {
                em.remove(lineaOrdinazione);
                return true;
            }
        }
        
        return false;
    }
    
}
