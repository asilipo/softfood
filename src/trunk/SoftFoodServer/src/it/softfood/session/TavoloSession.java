package it.softfood.session;

import it.softfood.entity.Tavolo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TavoloSession {

	private Session session;
	private static TavoloSession tavoloSession;

	public synchronized static TavoloSession getInstance() {
		if(tavoloSession == null)
			tavoloSession = new TavoloSession();
		return tavoloSession;
	}

	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Tavolo");
			List list = q.list();
			Long id = (Long)list.get(0);
		    return (id + 1);
		} catch(Exception e) {
			System.out.println("TavoloSession#getNewId");
			return null;
		}		
	}
	
	public Tavolo inserisciTavolo(Tavolo tavolo) {
		try {
			Long id = this.getNewId();
			tavolo.setId(id);
			session.persist(tavolo);
			tavolo = this.selezionaTavoloPerId(id);
			
			return tavolo;
		} catch (Exception e) {
			System.err.println("TavoloSession#inserisciTavolo");
			return null;
		}
	}

	public Tavolo selezionaTavoloPerId(Long id) {
		try {
			Tavolo tavolo = (Tavolo) session.get(Tavolo.class, id);
			
			return tavolo;
		} catch (Exception e) {
			System.err.println("TavoloSession#selezionaTavoloPerId");
			return null;
		}
	}

	public Tavolo selezionaTavoloPerRiferimento(String riferimento, Boolean attivo) {
		try {
			Query q = session.createQuery("from it.softfood.entity.Tavolo t where t.riferimento = ? AND t.attivo = ?");
			q.setString(0, riferimento);
			q.setBoolean(1, attivo);			
			Tavolo tav = (Tavolo) q.uniqueResult();
	
			return tav;
		} catch (Exception e) {
			System.err.println("TavoloSession#selezionaTavoloPerRiferimento");
			return null;
		}
	}

	public List<Tavolo> selezionaTavoliPerNumeroPosti(Integer numeroPosti) {
		try {
			Query q = session.createQuery("from it.softfood.entity.Tavolo t where t.numeroPosti = ? AND t.attivo = ?");
			q.setInteger(0, numeroPosti);
			q.setBoolean(1, true);
			List<Tavolo> list = (List<Tavolo>) q.list();
			
			return list;			
		} catch (Exception e) {
			System.err.println("TavoloSession#selezionaTavoliPerNumeroPosti");
			return null;
		}
	}

	public List<Tavolo> selezionaTavoliLiberi() {
		try {
			Query q = session.createQuery("from it.softfood.entity.Tavolo t where t.occupato = ? AND t.attivo = ? order by t.riferimento");
			q.setBoolean(0, false);
			q.setBoolean(1, true);
			List<Tavolo> list = (List<Tavolo>) q.list();
			
			return list;
		} catch (Exception e) {
			System.err.println("TavoloSession#selezionaTavoliLiberi");
			return null;
		}
	}

	public List<Tavolo> selezionaTavoliOccupati() {
		try {
			Query q = session.createQuery("from it.softfood.entity.Tavolo t where t.occupato = ? AND t.attivo = ? order by t.riferimento");
			q.setBoolean(0, true);
			q.setBoolean(1, true);
			List<Tavolo> list = (List<Tavolo>) q.list();
			
			return list;			
		} catch (Exception e) {
			System.err.println("TavoloSession#selezionaTavoliOccupati");
			return null;
		}
	}

	public List<Tavolo> selezionaTavoliNonAttivi() {
		try {
			Query q = session.createQuery("from it.softfood.entity.Tavolo t where t.attivo = ?");
			q.setBoolean(0, false);
			List<Tavolo> list = (List<Tavolo>) q.list();
			
			return list;			
		} catch (Exception e) {
			System.err.println("TavoloSession#selezionaTavoliNonAttivi");
			return null;
		}
	}

	public boolean modificaStatoTavolo(Tavolo tavolo, Boolean occupato) {
		try {
			tavolo.setOccupato(occupato);
			tavolo = (Tavolo) session.merge(tavolo);

			return true;
		} catch (Exception e) {
			System.err.println("TavoloSession#modificaStatoTavolo");
			return false;
		}
	}

	public boolean rimuoviTavolo(Long id) {
		try {
			Tavolo tavolo = this.selezionaTavoloPerId(id);
			if (tavolo != null) {
				session.delete(tavolo);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("TavoloSession#rimuoviTavolo");
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

	public void update(Tavolo tavolo) {
		session.update(tavolo);
	}
	
}
