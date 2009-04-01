package it.softfood.session;

import it.softfood.entity.BevandaMagazzino;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class BevandaMagazzinoSession {

	private Session session;
	private static BevandaMagazzinoSession bevandaMagazzino;

	public synchronized static BevandaMagazzinoSession getInstance() {
		if(bevandaMagazzino == null)
			bevandaMagazzino = new BevandaMagazzinoSession();
		return(bevandaMagazzino);
	}
	
	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.BevandaMagazzino");
			List list = q.list();
		    return (((Long)list.get(0)) + 1);
		} catch(Exception e) {
			System.out.println("BevandaMagazzinoSession#getNewId");
			return null;
		}		
	}
	
	public BevandaMagazzino inserisciBevandaMagazzino(BevandaMagazzino bevandaMagazzino) {
		try {
			Long id = this.getNewId();
			bevandaMagazzino.setId(id);
			session.persist(bevandaMagazzino);
			
			return (BevandaMagazzino) session.get(BevandaMagazzino.class, bevandaMagazzino);
		} catch (Exception e) {
			System.err.println("BevandaMagazzinoSession#inserisciBevandaMagazzino");
			return null;
		}
	}
	
	public BevandaMagazzino selezionaBevandaMagazzinoPerId(Long id) {
		try {			
			//problema sulla sessione
			Query q = session.createQuery("from it.softfood.entity.BevandaMagazzino b where b.id = ? "); 
			q.setLong(0, id);
			
		    return (BevandaMagazzino) q.uniqueResult();
		} catch (Exception e) {
			System.err.println("BevandaMagazzinoSession#selezionaBevandaMagazzinoPerId");
			return null;
		}
	}

	public List<BevandaMagazzino> selezionaBevandeMagazzino() {
		try {
			Query q = session.createQuery("from it.softfood.entity.BevandaMagazzino b");
			return (List<BevandaMagazzino>) q.list();
		} catch (Exception e) {
			System.err.println("BevandaMagazzinoSession#selezionaBevandeMagazzino");
			return null;
		}
	}
	
	public List<BevandaMagazzino> selezionaBevandeMagazzinoPerQuantita(Integer quantita) {
		try {
			Query q = session.createQuery("from it.softfood.entity.BevandaMagazzino b where b.quantita = ?");
			q.setInteger(0, quantita);
			return (List<BevandaMagazzino>) q.list();
		} catch (Exception e) {
			System.err.println("BevandaMagazzinoSession#selezionaBevandeMagazzinoPerQuantita");
			return null;
		}
	}
	
    public boolean rimuoviBevandaMagazzino(Long id) {
    	try {
    		BevandaMagazzino bevandaMagazzino = this.selezionaBevandaMagazzinoPerId(id);
			if (bevandaMagazzino != null) {
				session.delete(bevandaMagazzino);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("BevandaMagazzinoSession#rimuoviBevandaMagazzino");
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
