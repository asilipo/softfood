package it.softfood.session;

import it.softfood.entity.Articolo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class ArticoloSession {
    
	private Session session;
	private static ArticoloSession articoloSession;

	public synchronized static ArticoloSession getInstance() {
		if(articoloSession == null)
			articoloSession = new ArticoloSession();
		return articoloSession;
	}

	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Articolo");
			List list = q.list();
		    Long id = ((Long)list.get(0));
		    if (id == null)
		    	id = 0L;
		    return (id + 1);
		} catch(Exception e) {
			System.out.println("ArticoloSession#getNewId");
			return null;
		}		
	}
	
	public Articolo inserisciArticolo(Articolo articolo) {
		try {
			Long id = this.getNewId();
			articolo.setId(id);
			session.persist(articolo);
			articolo = (Articolo) session.get(Articolo.class, articolo);
			
			return articolo;
		} catch (Exception e) {
			System.err.println("ArticoloSession#inserisciArticolo");
			return null;
		}	
	}
	
	public Articolo selezionaArticoloPerId(Long id) {
		try {
			Articolo articolo = (Articolo) session.get(Articolo.class, id); 
			return articolo;
		} catch (Exception e) {
			System.err.println("ArticoloSession#selezionaArticoloPerId");
			return null;
		}
	}

    public boolean rimuoviArticolo(Long id) {
		try {
			Articolo articolo = this.selezionaArticoloPerId(id);
			if (articolo != null) {
				session.delete(articolo);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("ArticoloSession#rimuoviArticolo");
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
	
	public void update(Articolo articolo) {
		session.update(articolo);
	}
	
}
