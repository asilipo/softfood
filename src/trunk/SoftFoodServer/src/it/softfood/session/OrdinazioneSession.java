package it.softfood.session;

import it.softfood.entity.Ordinazione;
import it.softfood.entity.Tavolo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		    Long id = (Long)q.uniqueResult();
		    if(id==null)
		    	return new Long(0);
			return (id + 1);
		} catch(Exception e) {
			System.err.println("OrdinazioneSession#getNewId");
			return null;
		}		
	}
	
	public Ordinazione inserisciOrdinazione(Ordinazione ordinazione) {
		try {		    
			Long id = this.getNewId();
			ordinazione.setId(id);
			session.persist(ordinazione);
			ordinazione = this.selezionaOrdinazionePerId(ordinazione.getId());
			return ordinazione;
		} catch (Exception e) {
			System.err.println("OrdinazioneSession#inserisciOrdinazione");
			return null;
		}
	}
	
	public Ordinazione selezionaOrdinazionePerId(Long id) {
		try {
			Ordinazione ordinazione = (Ordinazione) session.get(Ordinazione.class, id);
			if (ordinazione == null) {
				Query q = session.createQuery("from it.softfood.entity.Ordinazione o where o.id = ?");
				q.setLong(0, id);
				ordinazione = (Ordinazione) q.uniqueResult();
			}
			
			return ordinazione;
		} catch (Exception e) {
			System.err.println("OrdinazioneSession#selezionaOrdinazionePerId");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Ordinazione> selezionaOrdinazioni() {    
		try {
			Query q = session.createQuery("from it.softfood.entity.Ordinazione o");
			List<Ordinazione> list = (List<Ordinazione>) q.list();
			return list;
		} catch (Exception e) {
			System.err.println("OrdinazioneSession#selezionaOrdinazioni");
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ordinazione> selezionaOrdinazioniGiornaliere() {
		try {
		    Date date = new Date(System.currentTimeMillis()); 
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String a = sdf.format(date);
		    date = sdf.parse(a);

			Query q = session.createQuery("from it.softfood.entity.Ordinazione o where o.terminato = false order by o.data");
			List<Ordinazione> list = (List<Ordinazione>) q.list();
			List<Ordinazione> list1 = new ArrayList<Ordinazione>();
			
			for (Ordinazione ordinazione : list) {
				if (ordinazione.getData().after(date)) {
					list1.add(ordinazione);
				}
			}
			
			return list1;
		} catch (Exception e) {
			System.err.println("OrdinazioneSession#selezionaOrdinazioniGiornaliere");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Ordinazione> selezionaOrdinazioniGionalierePerTavolo(Tavolo tavolo, Boolean terminato) {
		try {
		    Date date = new Date(System.currentTimeMillis()); 
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String a = sdf.format(date);
		    date = sdf.parse(a);
		    
			Query q = session.createQuery("from it.softfood.entity.Ordinazione o where o.tavolo = ? and o.terminato = ? order by o.data");
			q.setLong(0, tavolo.getId());
			q.setBoolean(1, terminato);
			List<Ordinazione> list = (List<Ordinazione>) q.list();
			List<Ordinazione> list1 = new ArrayList<Ordinazione>();
			
			for (Ordinazione ordinazione : list) {
				if (ordinazione.getData().after(date)) {
					list1.add(ordinazione);
				}
			}
			
			return list1;
		} catch (Exception e) {
			System.err.println("OrdinazioneSession#selezionaOrdinazioniGionalierePerTavolo");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ordinazione> selezionaOrdinazioniPerTavolo(Tavolo tavolo, Boolean terminato) {
		try {
		    Date date = new Date(System.currentTimeMillis()); 
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String a = sdf.format(date);
		    date = sdf.parse(a);
		    
			Query q = session.createQuery("from it.softfood.entity.Ordinazione o where o.tavolo = ? and o.terminato = ? order by o.data");
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
	
	public void update(Ordinazione ordinazione) {
		session.update(ordinazione);
	}

}
