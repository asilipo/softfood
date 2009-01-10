package it.softfood.session.pietanza;

import it.softfood.entity.Pietanza;

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
public class PietanzaSessionBean implements PietanzaSessionBeanRemote, PietanzaSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;
    
	public Pietanza inserisciPietanza(Pietanza pietanza) {
		if (pietanza != null) 
			em.persist(pietanza);
		
		return pietanza;
	}
	
	public Pietanza selezionaPietanzaPerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(Pietanza.class, id);
	}
	
	public List<Pietanza> selezionaPietanzePerNome(String nome) {
        if (nome == null) 
            return null;
       
       return em.createNamedQuery("Pietanza.selezionaPietanzePerNome")
        	.setParameter("nome", nome).getResultList();
	}

    public boolean rimuoviPietanza(Long id) {
        if (id != null) {
        	Pietanza pietanza = em.find(Pietanza.class, id);
            if (pietanza != null) {
                em.remove(pietanza);
                return true;
            }
        }
        
        return false;
    }
    
}

