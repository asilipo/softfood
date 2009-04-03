package it.softfood.session;

import it.softfood.entity.Pietanza;
import it.softfood.enumeration.TipoPietanza;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class PietanzaSession {

	private Session session;
	private static PietanzaSession pietanzaSession;

	public synchronized static PietanzaSession getInstance() {
		if(pietanzaSession == null)
			pietanzaSession = new PietanzaSession();
		return pietanzaSession;
	}

	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Pietanza");
		    return (((Long)q.list().get(0)) + 1);
		} catch(Exception e) {
			System.out.println("PietanzaSession#getNewId");
			return null;
		}		
	}
	
	public Pietanza inserisciPietanza(Pietanza pietanza) {
		try {
			Long id = this.getNewId();
			pietanza.setId(id);
			session.persist(pietanza);
			
			return (Pietanza) session.get(Pietanza.class, pietanza);
		} catch (Exception e) {
			System.err.println("PietanzaSession#inserisciPietanza");
			return null;
		}
	}
	
	public Pietanza selezionaPietanzaPerId(Long id) {
		try {
			return (Pietanza) session.get(Pietanza.class, id);
		} catch (Exception e) {
			System.err.println("PietanzaSession#selezionaPietanzaPerId");
			return null;
		}
	}

    public List<Pietanza> selezionaPietanzePerTipo(TipoPietanza tipoPietanza) {
    	try {
			Query q = session.createQuery("from it.softfood.entity.Pietanza o where tipoPietanza = ?");
			q.setString(0, tipoPietanza.toString());
			List<Pietanza> list = (List<Pietanza>) q.list();
			return list;
		} catch (Exception e) {
			System.err.println("PietanzaSession#selezionaPietanzePerTipo");
			return null;
		}
	}

    public List<Pietanza> selezionaPietanze() {
    	try {
			Query q = session.createQuery("from it.softfood.entity.Pietanza o where tipoArticolo = 'Pietanza'");
			List<Pietanza> list = (List<Pietanza>) q.list();
			return list;
		} catch (Exception e) {
			System.err.println("PietanzaSession#selezionaPietanze");
			return null;
		}
	}

    public boolean rimuoviPietanza(Long id) {
	  	try {
	  		Pietanza pietanza = this.selezionaPietanzaPerId(id);
			if (pietanza != null) {
				session.delete(pietanza);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("PietanzaSession#rimuoviPietanza");
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

