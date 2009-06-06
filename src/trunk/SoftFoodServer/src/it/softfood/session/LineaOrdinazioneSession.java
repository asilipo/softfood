package it.softfood.session;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class LineaOrdinazioneSession {

	private Session session;
	private static LineaOrdinazioneSession lineaOrdinazioneSession;

	public synchronized static LineaOrdinazioneSession getInstance() {
		if(lineaOrdinazioneSession == null)
			lineaOrdinazioneSession = new LineaOrdinazioneSession();
		return lineaOrdinazioneSession;
	}

	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.LineaOrdinazione");
		    Long id = (Long)q.uniqueResult();
		    if(id==null)
		    	return new Long(0);
			return (id + 1);
		} catch(Exception e) {
			System.out.println("LineaOrdinazioneSession#getNewId");
			return null;
		}		
	}
	
	public LineaOrdinazione inserisciLineaOrdinazione(LineaOrdinazione lineaOrdinazione) {
		try {
			Long id = lineaOrdinazione.getId();
			if(id ==null){
				id = this.getNewId();
				lineaOrdinazione.setId(id);
			}
			session.persist(lineaOrdinazione);
			lineaOrdinazione = (LineaOrdinazione) this.selezionaLineaOrdinazionePerId(lineaOrdinazione.getId());
			
			return lineaOrdinazione;
		} catch (Exception e) {
			System.err.println("LineaOrdinazioneSession#inserisciLineaOrdinazione");
			return null;
		}
	}
	
	public LineaOrdinazione selezionaLineaOrdinazionePerId(Long id) {
		try {
			LineaOrdinazione lineaOrdinazione = (LineaOrdinazione) session.get(LineaOrdinazione.class, id);
			
			return lineaOrdinazione;
		} catch (Exception e) {
			System.err.println("LineaOrdinazioneSession#selezionaLineaOrdinazionePerId");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(Ordinazione ordinazione) {
		try {
			Query q = session.createQuery("from it.softfood.entity.LineaOrdinazione l where l.ordinazione = ? order by l.id");
			q.setLong(0, ordinazione.getId());
			List<LineaOrdinazione> list = (List<LineaOrdinazione>) q.list();
			
			return list;
		} catch (Exception e) {
			System.err.println("LineaOrdinazioneSession#selezionaLineeOrdinazionePerOrdinazione");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<LineaOrdinazione> selezionaLineeOrdinazioneEvasePerOrdinazione(Ordinazione ordinazione, Boolean evase) {
		try {
			Query q = session.createQuery("from it.softfood.entity.LineaOrdinazione l where l.ordinazione = ? and l.evaso = ?");
			q.setLong(0, ordinazione.getId());
			q.setBoolean(1, evase);
			List<LineaOrdinazione> list = (List<LineaOrdinazione>) q.list();
			
			return list;
		} catch (Exception e) {
			System.err.println("LineaOrdinazioneSession#selezionaLineeOrdinazionePerOrdinazione");
			return null;
		}
	}
	
    public boolean rimuoviLineaOrdinazione(Long id) {
		try {
			LineaOrdinazione lineaOrdinazione = this.selezionaLineaOrdinazionePerId(id);
			if (lineaOrdinazione != null) {
				session.delete(lineaOrdinazione);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("LineaOrdinazioneSession#rimuoviLineaOrdinazione");
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
    
	public void update(LineaOrdinazione lineaOrdinazione) {
		session.update(lineaOrdinazione);
	}
	
}
