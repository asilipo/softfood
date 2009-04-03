package it.softfood.session;

import it.softfood.entity.Menu;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class MenuSession {

	private Session session;
	private static MenuSession menuSession;

	public synchronized static MenuSession getInstance() {
		if(menuSession == null)
			menuSession = new MenuSession();
		return menuSession;
	}

	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Menu");
		    return (((Long)q.list().get(0)) + 1);
		} catch(Exception e) {
			System.out.println("MenuSession#getNewId");
			return null;
		}		
	}
    
	public Menu inserisciMenu(Menu menu) {
		try {
			Long id = this.getNewId();
			menu.setId(id.toString());
			session.persist(menu);
			
			return (Menu) session.get(Menu.class, menu);
		} catch (Exception e) {
			System.err.println("MenuSession#inserisciMenu");
			return null;
		}
	}
	
	public Menu selezionaMenuPerId(Long id) {
		try {
			return (Menu) session.get(Menu.class, id);
		} catch (Exception e) {
			System.err.println("MenuSession#selezionaMenuPerId");
			return null;
		}
	}
	
    public boolean rimuoviMenu(Long id) {
    	try {
    		Menu menu = this.selezionaMenuPerId(id);
			if (menu != null) {
				session.delete(menu);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("MenuSession#rimuoviMenu");
			return false;
		}
    }

    public void flush() {
		this.session.flush();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
}
