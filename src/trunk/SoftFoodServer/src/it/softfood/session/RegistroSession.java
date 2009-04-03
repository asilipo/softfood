package it.softfood.session;

import it.softfood.entity.Registro;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class RegistroSession {

	private Session session;
	private static RegistroSession registroSession;

	public synchronized static RegistroSession getInstance() {
		if(registroSession == null)
			registroSession = new RegistroSession();
		return registroSession;
	}

	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Registro");
			List list = q.list();
			return (((Long)list.get(0)) + 1);
		} catch(Exception e) {
			System.out.println("RegistroSession#getNewId");
			return null;
		}		
	}
	
	public Registro inserisciRegistro(Registro registro) {
		try {
			Long id = this.getNewId();
			registro.setId(id);
			session.persist(registro);

			return (Registro) session.get(Registro.class, registro);
		} catch (Exception e) {
			System.err.println("RegistroSession#inserisciRegistro");
			return null;
		}
	}
	
	public Registro selezionaRegistroPerId(Long id) {
		try {
			return (Registro) session.get(Registro.class, id);
		} catch (Exception e) {
			System.err.println("RegistroSession#selezionaRegistroPerId");
			return null;
		}
	}
	
	public Registro selezionaRegistroPerAnno(Integer anno) {
		try {
			Query q = session.createQuery("from it.softfood.entity.Registro r where annoRiferimento = ?");
			q.setInteger(0, anno);
			Registro registro = (Registro) q.list().get(0);
			return registro;
		} catch (Exception e) {
			System.err.println("RegistroSession#selezionaRegistroPerAnno");
			return null;
		}
	}
	
    public boolean rimuoviRegistro(Long id) {
    	try {
			Registro registro = this.selezionaRegistroPerId(id);
			session.delete(registro);
			return true;
		} catch (Exception e) {
			System.err.println("RegistroSession#rimuoviRegistro");
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
