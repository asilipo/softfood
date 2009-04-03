package it.softfood.session;

import it.softfood.entity.Ordinazione;
import it.softfood.entity.Tavolo;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class OrdinazioneSession {
          
	private Session session;
	private static OrdinazioneSession ordinazioneSession;

	public synchronized static OrdinazioneSession getInstance() {
		if(ordinazioneSession == null)
			ordinazioneSession = new OrdinazioneSession();
		return ordinazioneSession;
	}

	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Ordinazione");
		    return (((Long)q.list().get(0)) + 1);
		} catch(Exception e) {
			System.out.println("OrdinazioneSession#getNewId");
			return null;
		}		
	}
	
	public Ordinazione inserisciOrdinazione(Ordinazione ordinazione) {
		try {
			Long id = this.getNewId();
			ordinazione.setId(id);
			session.persist(ordinazione);
			
			return (Ordinazione) session.get(Ordinazione.class, ordinazione);
		} catch (Exception e) {
			System.err.println("OrdinazioneSession#inserisciOrdinazione");
			return null;
		}
	}
	
	public Ordinazione selezionaOrdinazionePerId(Long id) {
		try {
			return (Ordinazione) session.get(Ordinazione.class, id);
		} catch (Exception e) {
			System.err.println("OrdinazioneSession#selezionaOrdinazionePerId");
			return null;
		}
	}

	public List<Ordinazione> selezionaOrdinazioni() {    
		try {
			Query q = session.createQuery("from it.softfood.entity.Ordinazioni o");
			List<Ordinazione> list = (List<Ordinazione>) q.list();
			return list;
		} catch (Exception e) {
			System.err.println("OrdinazioneSession#selezionaOrdinazioni");
			return null;
		}
	}
	
	public List<Ordinazione> selezionaOrdinazioniPerData(Date data) {
		try {
			Query q = session.createQuery("from it.softfood.entity.Ordinazioni o where data = ?");
			q.setDate(0, data);
			List<Ordinazione> list = (List<Ordinazione>) q.list();
			return list;
		} catch (Exception e) {
			System.err.println("OrdinazioneSession#selezionaOrdinazioniPerData");
			return null;
		}
	}

	public List<Ordinazione> selezionaOrdinazioniGionalierePerTavolo(Tavolo tavolo, Boolean terminato) {
		try {
			Query q = session.createQuery("from it.softfood.entity.Ordinazioni o where tavolo = ? and terminato = ?");
			q.setLong(0, tavolo.getId());
			q.setBoolean(1, terminato);
			List<Ordinazione> list = (List<Ordinazione>) q.list();
			return list;
		} catch (Exception e) {
			System.err.println("OrdinazioneSession#selezionaOrdinazioniGionalierePerTavolo");
			return null;
		}
	}
	
    public boolean rimuoviOrdinazione(Long id) {
    	try {
    		Ordinazione ordinazione = this.selezionaOrdinazionePerId(id);
			if (ordinazione != null) {
				session.delete(ordinazione);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("OrdinazioneSession#rimuoviOrdinazione");
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
