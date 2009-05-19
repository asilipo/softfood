package it.softfood.session;

import it.softfood.entity.Entrata;
import it.softfood.entity.Ordinazione;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class EntrataSession {
    
	private Session session;
	private static EntrataSession entrataSession;

	public synchronized static EntrataSession getInstance() {
		if(entrataSession == null)
			entrataSession = new EntrataSession();
		return entrataSession;
	}
	
	@SuppressWarnings("unchecked")
	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Entrata");
			List list = q.list();
			Long id = (Long) list.get(0);
			
			return (id + 1);
		} catch(Exception e) {
			System.out.println("EntrataSession#getNewId");
			return null;
		}		
	}
	
	public Entrata inserisciEntrata(Entrata entrata) {
		try {
			Long id = this.getNewId();
			entrata.setId(id);
			session.persist(entrata);
			entrata = (Entrata) session.get(Entrata.class, entrata);
			
			return entrata;
		} catch (Exception e) {
			System.err.println("EntrataSession#inserisciEntrata");
			return null;
		}
	}
	
	public Entrata selezionaEntrataPerId(Long id) {
		try {
			Entrata entrata = (Entrata) session.get(Entrata.class, id);
			
			return entrata;
		} catch (Exception e) {
			System.err.println("EntrataSession#selezionaEntrataPerId");
			return null;
		}
	}

	public Ordinazione selezionaBevandeMagazzinoEntrataPerOrdinazione(Ordinazione ordinazione) {
		try {
			Query q = session.createQuery("from it.softfood.entity.Entrata e where e.ordinazione = ?");
			q.setLong(0, ordinazione.getId());
			ordinazione = (Ordinazione) q.uniqueResult();
			
			return ordinazione; 
		} catch (Exception e) {
			System.err.println("EntrataSession#selezionaBevandeMagazzinoEntrataPerOrdinazione");
			return null;
		}
	}
	
    public boolean rimuoviEntrata(Long id) {
    	try {
    		Entrata entrata = this.selezionaEntrataPerId(id);
			if (entrata != null) {
				session.delete(entrata);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("EntrataSession#rimuoviEntrata");
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
    
	public void update(Entrata entrata) {
		session.update(entrata);
	}
	
}
