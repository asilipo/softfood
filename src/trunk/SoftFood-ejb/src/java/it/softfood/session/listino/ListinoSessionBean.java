package it.softfood.session.listino;

import it.softfood.entity.Articolo;
import it.softfood.entity.Listino;

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
public class ListinoSessionBean implements ListinoSessionBeanRemote, ListinoSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public Listino inserisciListino(Listino listino) {
		try {
			em.persist(listino);
            return listino;
        } catch (Exception e) {
            System.err.println("ListinoSessionBean#inserisciListino");
            return null;
        }
	}
	
	public Listino selezionaListinoPerId(Long id) {
        try {
            return em.find(Listino.class, id);
        } catch (Exception e) {
            System.err.println("ListinoSessionBean#selezionaListinoPerId");
            return null;
        }
	}
	
	public List<Articolo> selezionaArticoli() {
        try {
            return (ArrayList<Articolo>) em.createNamedQuery("Listino.selezionaArticoli")
                .getResultList();
        } catch (Exception e) {
            System.err.println("ListinoSessionBean#selezionaArticoli");
            return null;
        }
	}
	
    public boolean rimuoviListino(Long id) {
        try {
        	Listino listino = em.find(Listino.class, id);
            if (listino != null) {
                em.remove(listino);
                return true;
            }

            return false;
        } catch (Exception e) {
            System.err.println("ListinoSessionBean#rimuoviListino");
            return false;
        }
    }

}
