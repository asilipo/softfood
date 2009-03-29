package it.softfood.session;

import it.softfood.aspect.HibernateUtil;
import it.softfood.entity.Tavolo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TavoloSession {

	private Session session;

	// @PersistenceContext
	// private EntityManager em;

	private Long getNewId(){
		Long id = new Long(0);
		try{
			String str ="select max(id) from it.softfood.entity.Tavolo";
			Query q = session.createQuery(str);
			 List list = q.list();
		     id = ((Long)list.get(0));
		    
		}catch(Exception e){
			System.out.println("Error in getNewId");
		}
		id++;
		return id;
	}
		public Tavolo inserisciTavolo(Tavolo tavolo) {
		
		try {
			Long id = this.getNewId();
			System.out.println("Inserisci Tavolo "+id);
			tavolo.setId(id);
			
			session.persist(tavolo);
			return tavolo;
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#inserisciTavolo");
			System.err.println(e);
			return null;
		}
	}

	public Tavolo selezionaTavoloPerId(Long id) {
		try {
			return (Tavolo) session.get(Tavolo.class, id);
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#selezionaTavoloPerId");
			return null;
		}
	}

	public Tavolo selezionaTavoloPerRiferimento(String riferimento) {
		try {
			Tavolo result;
			String str = " from it.softfood.entity.Tavolo t where t.riferimento = ? ";
			Query q = session.createQuery(str);
			q.setString(0, riferimento);
			result = (Tavolo) q.uniqueResult();

			return result;
		} catch (Exception e) {
			System.err
					.println("TavoloSessionBean#selezionaTavoloPerRiferimento");
			return null;
		}
	}

	public List<Tavolo> selezionaTavoliPerNumeroPosti(Integer numeroPosti) {
		try {
			List<Tavolo> result;
			String str = " from it.softfood.entity.Tavolo t where t.numeroPosti = ? ";
			Query q = session.createQuery(str);
			q.setInteger(0, numeroPosti);
			result = q.list();
			return result;
			
		} catch (Exception e) {
			System.err
					.println("TavoloSessionBean#selezionaTavoliPerNumeroPosti");
			return null;
		}
	}

	public List<Tavolo> selezionaTavoliLiberi() {
		try {
			Tavolo t;
			List<Tavolo> result;
			String str = " from it.softfood.entity.Tavolo t where t.occupato = ?";
			Query q = session.createQuery(str);
			q.setBoolean(0, false);
			result = q.list();
			return result;
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#selezionaTavoliLiberi");
			System.err.println(e);
			return null;
		} catch (Throwable e) {
			return null;
		}
	}

	public List<Tavolo> selezionaTavoliOccupati() {
		try {
			List<Tavolo> result;
			String str = " from it.softfood.entity.Tavolo t where t.occupato = ? ";
			Query q = session.createQuery(str);
			q.setBoolean(0, true);
			result = q.list();

			return result;
			
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#selezionaTavoliOccupati");
			return null;
		}
	}

	public List<Tavolo> selezionaTavoliNonAttivi() {
		try {
			List<Tavolo> result;
			String str = " from it.softfood.entity.Tavolo t where t.attivo = ? ";
			Query q = session.createQuery(str);
			q.setBoolean(0, false);
			result = q.list();

			return result;
			
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#selezionaTavoliNonAttivi");
			return null;
		}
	}

	public boolean modificaStatoTavolo(Tavolo tavolo, Boolean occupato) {
		try {
			tavolo.setOccupato(occupato);

			tavolo = (Tavolo) session.merge(tavolo);

			return true;
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#modificaStatoTavolo");
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
			System.err.println("TavoloSessionBean#rimuoviTavolo");
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
