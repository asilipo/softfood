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

	public Tavolo inserisciTavolo(Tavolo tavolo) {
		try {
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
			System.out.println("dfsadfgadgf");
			System.out.println("RICHIESTA DA ID "+id+" DI UN TAVOLOOOOOOOOOO");
			System.out.println("ATTENZIONE PROBLEMA SULLA SESSION "+session.isConnected());
			return (Tavolo) session.get(Tavolo.class, id);
		} catch (Exception e) {
			System.err.println("TavoloSessionBean#selezionaTavoloPerId");
			return null;
		}
	}

	public Tavolo selezionaTavoloPerRiferimento(String riferimento) {
		try {
			Tavolo result;
			String str = " from softfood.tavolo where riferimento = ? ";
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
			String str = " from softfood.tavolo where numero_posti = ? ";
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
	
			System.out.println("++++++++INIT QUERY NOT NULLLLLLLLLLLLLLLLLL-"+ this.getSession().isConnected());
			
			if(session.isConnected())
				System.out.println("Tavolo---SESSION CONNECTEDDDDDDDDDDDDDDDDD");
			
			if(session.isOpen())
				System.out.println("Tavolo---SESSION OPENEDDDDDDDDDDDDDDDDDDDDD");
			

			Query q = this.getSession().createQuery(str);
			
			System.out.println("Tavolo -- SESSION NOT NULLLLLLLLLLLLLLLLLL");
			
//			Problema sulla query
			q.setBoolean(0, false);
			
			System.out.println("Tavolo -- QUERY NOT NULLLLLLLLLLLLLLLLLL");
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
			String str = " from softfood.tavolo where occupato = ? ";
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
			String str = " from it.softfood.entity.Tavolo where attivo = ? ";
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

			//tavolo = (Tavolo) this.getSession().merge(tavolo);

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
				//this.getSession().delete(tavolo);
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
