package it.softfood.session;

import it.softfood.entity.Indirizzo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class IndirizzoSession {

	private Session session;
	private static IndirizzoSession indirizzoSession;

	public synchronized static IndirizzoSession getInstance() {
		if(indirizzoSession == null)
			indirizzoSession = new IndirizzoSession();
		return indirizzoSession;
	}

	@SuppressWarnings("unchecked")
	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Indirizzo");
			List list = q.list();
			Long id = (Long) list.get(0);
			
		    return (id + 1);
		} catch(Exception e) {
			System.out.println("IndirizzoSession#getNewId");
			return null;
		}		
	}
    
	public Indirizzo inserisciIndirizzo(Indirizzo indirizzo) {
		try {
			if (indirizzo.getId() == null) {
				Long id = this.getNewId();
				indirizzo.setId(id);
			}

			session.persist(indirizzo);
			indirizzo = (Indirizzo) session.get(Indirizzo.class, indirizzo.getId());
			
			return indirizzo; 
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("IndirizzoSession#inserisciIndirizzo");
			return null;
		}
	}
	
	public Indirizzo selezionaIndirizzoPerId(Long id) {
		try {
			Indirizzo indirizzo = (Indirizzo) session.get(Indirizzo.class, id);
			
			return indirizzo;
		} catch (Exception e) {
			System.err.println("IndirizzoSession#selezionaIndirizzoPerId");
			return null;
		}
	}
	
    public boolean rimuoviIndirizzo(Long id) {
		try {
			Indirizzo indirizzo = this.selezionaIndirizzoPerId(id);
			if (indirizzo != null) {
				session.delete(indirizzo);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("IndirizzoSession#rimuoviIndirizzo");
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
	
	public void update(Indirizzo indirizzo) {
		session.update(indirizzo);
	}

}
