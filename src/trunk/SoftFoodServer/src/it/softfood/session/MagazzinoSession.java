package it.softfood.session;

import it.softfood.entity.Magazzino;

import org.hibernate.Query;
import org.hibernate.Session;

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
		    Long id = (Long)q.list().get(0);
			
			return (id + 1);
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
			magazzino = (Magazzino) session.get(Magazzino.class, magazzino);
			
			return magazzino;
		} catch (Exception e) {
			System.err.println("MagazzinoSession#inserisciMagazzino");
			return null;
		}
	}
	
	public Magazzino selezionaMagazzinoPerId(Long id) {
		try {
			Magazzino magazzino = (Magazzino) session.get(Magazzino.class, id);
			
			return magazzino;
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

	public void update(Magazzino magazzino) {
		session.update(magazzino);
	}
	
}
