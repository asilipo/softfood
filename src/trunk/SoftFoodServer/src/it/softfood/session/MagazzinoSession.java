package it.softfood.session;

import org.hibernate.Query;
import org.hibernate.Session;

import it.softfood.entity.Listino;
import it.softfood.entity.Magazzino;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class MagazzinoSession {

	private Session session;
	private static MagazzinoSession magazzinoSession;

	public synchronized static MagazzinoSession getInstance() {
		if(magazzinoSession == null)
			magazzinoSession = new MagazzinoSession();
		return magazzinoSession;
	}
	
	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Magazzino");
		    return (((Long)q.list().get(0)) + 1);
		} catch(Exception e) {
			System.out.println("MagazzinoSession#getNewId");
			return null;
		}		
	}
	
	public Magazzino inserisciMagazzino(Magazzino magazzino) {
		try {
			Long id = this.getNewId();
			magazzino.setId(id);
			session.persist(magazzino);
			
			return (Magazzino) session.get(Magazzino.class, magazzino);
		} catch (Exception e) {
			System.err.println("MagazzinoSession#inserisciMagazzino");
			return null;
		}
	}
	
	public Magazzino selezionaMagazzinoPerId(Long id) {
		try {
			return (Magazzino) session.get(Magazzino.class, id);
		} catch (Exception e) {
			System.err.println("MagazzinoSession#selezionaMagazzinoPerId");
			return null;
		}
	}
	
    public boolean rimuoviMagazzino(Long id) {
    	try {
    		Magazzino magazzino = this.selezionaMagazzinoPerId(id);
			if (magazzino != null) {
				session.delete(magazzino);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("MagazzinoSession#rimuoviMagazzino");
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
