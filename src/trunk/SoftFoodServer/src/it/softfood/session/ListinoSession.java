package it.softfood.session;

import it.softfood.entity.Listino;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class ListinoSession {

	private Session session;
	private static ListinoSession listinoSession;

	public synchronized static ListinoSession getInstance() {
		if(listinoSession == null)
			listinoSession = new ListinoSession();
		return listinoSession;
	}

	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Listino");
		    return (((Long)q.list().get(0)) + 1);
		} catch(Exception e) {
			System.out.println("ListinoSession#getNewId");
			return null;
		}		
	}
    
	public Listino inserisciListino(Listino listino) {
		try {
			Long id = this.getNewId();
			listino.setId(id);
			session.persist(listino);
			
			return (Listino) session.get(Listino.class, listino);
		} catch (Exception e) {
			System.err.println("ListinoSession#inserisciListino");
			return null;
		}
	}
	
	public Listino selezionaListinoPerId(Long id) {
		try {
			return (Listino) session.get(Listino.class, id);
		} catch (Exception e) {
			System.err.println("ListinoSession#selezionaListinoPerId");
			return null;
		}
	}
	
    public boolean rimuoviListino(Long id) {
    	try {
    		Listino listino = this.selezionaListinoPerId(id);
			if (listino != null) {
				session.delete(listino);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("ListinoSession#rimuoviListino");
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
