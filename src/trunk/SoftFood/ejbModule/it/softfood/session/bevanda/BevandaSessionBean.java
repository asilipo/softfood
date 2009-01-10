package it.softfood.session.bevanda;

import it.softfood.entity.Bevanda;

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
public class BevandaSessionBean implements BevandaSessionBeanRemote, BevandaSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;
    
	public Bevanda inserisciBevanda(Bevanda bevanda) {
		if (bevanda != null) 
			em.persist(bevanda);
		
		return bevanda;
	}
	
	public Bevanda selezionaBevandaPerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(Bevanda.class, id);
	}
	
	public List<Bevanda> selezionaBevandePerNome(String nome) {
        if (nome == null) 
            return null;
       
       return em.createNamedQuery("Bevanda.selezionaBevandePerNome")
        	.setParameter("nome", nome).getResultList();
	}

    public boolean rimuoviBevanda(Long id) {
        if (id != null) {
            Bevanda bevanda = em.find(Bevanda.class, id);
            if (bevanda != null) {
                em.remove(bevanda);
                return true;
            }
        }
        
        return false;
    }
    
}
