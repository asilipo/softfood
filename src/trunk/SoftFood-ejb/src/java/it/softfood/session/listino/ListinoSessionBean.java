package it.softfood.session.listino;

import it.softfood.entity.Articolo;
import it.softfood.entity.Listino;

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
public class ListinoSessionBean implements ListinoSessionBeanRemote, ListinoSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public Listino inserisciListino(Listino listino) {
		if (listino != null) 
			em.persist(listino);
		
		return listino;
	}
	
	public Listino selezionaListinoPerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(Listino.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Articolo> selezionaArticoli() {       
       return em.createNamedQuery("Listino.selezionaArticoli")
        	.getResultList();
	}
	
    public boolean rimuoviListino(Long id) {
        if (id != null) {
        	Listino listino = em.find(Listino.class, id);
            if (listino != null) {
                em.remove(listino);
                return true;
            }
        }
        
        return false;
    }
}
