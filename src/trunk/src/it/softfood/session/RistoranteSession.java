package it.softfood.session;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.softfood.entity.Ristorante;
import it.softfood.entity.Tavolo;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class RistoranteSession {

	public Session session;

	public Ristorante inserisciRistorante(Ristorante ristorante) {
		try {
			session.persist(ristorante);
			return ristorante;
		} catch (Exception e) {
			System.err.println("RistoranteSessionBean#inserisciRistorante");
			return null;
		}
	}

	public Ristorante selezionaRistorantePerRagioneSociale(String ragioneSociale) {
		try {
			Ristorante r;

			String str = " from it.softfood.entity.Ristorante r where r.ragione_sociale = ?";
			Query q = this.getSession().createQuery(str);
			q.setString(0, ragioneSociale);
			r = (Ristorante) q.uniqueResult();
			return r;

		} catch (Exception e) {
			System.err
					.println("RistoranteSessionBean#selezionaRistorantePerRagioneSociale");
			return null;
		}
	}

	public Ristorante selezionaRistorantePerPartitaIva(String partitaIva) {
		try {
			Ristorante r;

			String str = " from it.softfood.entity.Ristorante r where r.partita_iva = ?";
			Query q = this.getSession().createQuery(str);
			q.setString(0, partitaIva);
			r = (Ristorante) q.uniqueResult();
			return r;
		} catch (Exception e) {
			System.err
					.println("RistoranteSessionBean#selezionaRistorantePerPartitaIva");
			return null;
		}
	}

	public boolean rimuoviRistorante(String ragioneSociale) {
		try {
			Ristorante ristorante = (Ristorante) session.get(Ristorante.class,
					ragioneSociale);
			if (ristorante != null) {
				session.delete(ristorante);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("RistoranteSessionBean#rimuoviRistorante");
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
