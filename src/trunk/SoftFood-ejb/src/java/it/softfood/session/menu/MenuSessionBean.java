package it.softfood.session.menu;

import it.softfood.entity.Menu;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class MenuSessionBean implements MenuSessionBeanRemote, MenuSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public Menu inserisciMenu(Menu menu) {
		if (menu != null) 
			em.persist(menu);
		
		return menu;
	}
	
	public Menu selezionaMenuPerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(Menu.class, id);
	}
	
    public boolean rimuoviMenu(Long id) {
        if (id != null) {
        	Menu menu = em.find(Menu.class, id);
            if (menu != null) {
                em.remove(menu);
                return true;
            }
        }
        
        return false;
    }

}
