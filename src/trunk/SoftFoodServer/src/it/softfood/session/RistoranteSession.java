package it.softfood.session;

import it.softfood.entity.Ristorante;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class RistoranteSession {

	public Session session;
	private static RistoranteSession ristorante;

	public synchronized static RistoranteSession getInstance() {
		if(ristorante == null)
			ristorante = new RistoranteSession();
		return(ristorante);
	}
	
	public Ristorante inserisciRistorante(Ristorante ristorante) {
		try {
			session.persist(ristorante);
			ristorante = (Ristorante) session.get(Ristorante.class, ristorante);
			
			return ristorante;
		} catch (Exception e) {
			System.err.println("RistoranteSession#inserisciRistorante");
			return null;
		}
	}

	public Ristorante selezionaRistorantePerRagioneSociale(String ragioneSociale) {
		try {			
			Query q = session.createQuery("from it.softfood.entity.Ristorante r where r.ragioneSociale =  ?");
			q.setString(0, ragioneSociale);
			Ristorante ristorante = (Ristorante) q.uniqueResult();
			
		    return ristorante; 
		} catch (Exception e) {
			System.err.println("RistoranteSession#selezionaRistorantePerRagioneSociale");
			return null;
		}
	}

	public Ristorante selezionaRistorantePerPartitaIva(String partitaIva) {
		try {
			Query q = session.createQuery("from it.softfood.entity.Ristorante r where r.partitaIva = ?");
			q.setString(0, partitaIva);
			Ristorante ristorante = (Ristorante) q.uniqueResult();
			
			return ristorante;
		} catch (Exception e) {
			System.err.println("RistoranteSession#selezionaRistorantePerPartitaIva");
			return null;
		}
	}

	public boolean rimuoviRistorante(String ragioneSociale) {
		try {
			Ristorante ristorante = (Ristorante) session.get(Ristorante.class, ragioneSociale);
			if (ristorante != null) {
				session.delete(ristorante);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("RistoranteSession#rimuoviRistorante");
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
	
	public void update(Ristorante ristorante) {
		session.update(ristorante);
	}

}
