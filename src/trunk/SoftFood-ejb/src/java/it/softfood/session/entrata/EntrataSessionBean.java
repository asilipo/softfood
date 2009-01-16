package it.softfood.session.entrata;

import it.softfood.entity.Entrata;
import it.softfood.entity.Ordinazione;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class EntrataSessionBean implements EntrataSessionBeanRemote, EntrataSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public Entrata inserisciEntrata(Entrata entrata) {
		if (entrata != null) 
			em.persist(entrata);
		
		return entrata;
	}
	
	public Entrata selezionaEntrataPerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(Entrata.class, id);
	}

	public Ordinazione selezionaBevandeMagazzinoEntrataPerOrdinazione(Ordinazione ordinazione) {
		if (ordinazione != null)
			return null;
			
       return (Ordinazione) em.createNamedQuery("Entrata.selezionaEntrataPerOrdinazione")
        	.getSingleResult();
	}
	
    public boolean rimuoviEntrata(Long id) {
        if (id != null) {
        	Entrata entrata = em.find(Entrata.class, id);
            if (entrata != null) {
                em.remove(entrata);
                return true;
            }
        }
        
        return false;
    }
    
}
