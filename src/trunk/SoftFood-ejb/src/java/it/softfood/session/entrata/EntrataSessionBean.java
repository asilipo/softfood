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
		try {
			em.persist(entrata);
            return entrata;
        } catch (Exception e) {
            System.err.println("EntrataSessionBean#inserisciEntrata");
            return null;
        }
	}
	
	public Entrata selezionaEntrataPerId(Long id) {
        try {
            return em.find(Entrata.class, id);
        } catch (Exception e) {
            System.err.println("EntrataSessionBean#selezionaEntrataPerId");
            return null;
        }
	}

	public Ordinazione selezionaBevandeMagazzinoEntrataPerOrdinazione(Ordinazione ordinazione) {
		try {
            return (Ordinazione) em.createNamedQuery("Entrata.selezionaEntrataPerOrdinazione")
                .getSingleResult();
        } catch (Exception e) {
            System.err.println("EntrataSessionBean#selezionaBevandeMagazzinoEntrataPerOrdinazione");
            return null;
        }
	}
	
    public boolean rimuoviEntrata(Long id) {
        try {
        	Entrata entrata = em.find(Entrata.class, id);
            if (entrata != null) {
                em.remove(entrata);
                return true;
            }

            return false;
        } catch (Exception e) {
            System.err.println("EntrataSessionBean#rimuoviEntrata");
            return false;
        }
    }
    
}
