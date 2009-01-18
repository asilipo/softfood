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
		try {
			em.persist(menu);
            return menu;
        } catch (Exception e) {
            System.err.println("MenuSessionBean#inserisciMenu");
            return null;
        }
	}
	
	public Menu selezionaMenuPerId(Long id) {
        try {
            return em.find(Menu.class, id);
        } catch (Exception e) {
            System.err.println("MenuSessionBean#selezionaMenuPerId");
            return null;
        }
	}
	
    public boolean rimuoviMenu(Long id) {
        try {
        	Menu menu = em.find(Menu.class, id);
            if (menu != null) {
                em.remove(menu);
                return true;
            }

            return false;
        } catch (Exception e) {
            System.err.println("MenuSessionBean#rimuoviMenu");
            return false;
        }
    }

}
