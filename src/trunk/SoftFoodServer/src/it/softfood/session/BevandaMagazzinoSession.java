package it.softfood.session;

import it.softfood.entity.BevandaMagazzino;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class BevandaMagazzinoSession {

	private Session session;
	private static BevandaMagazzinoSession bevandaMagazzino;

	public synchronized static BevandaMagazzinoSession getInstance() {
		if(bevandaMagazzino == null)
			bevandaMagazzino = new BevandaMagazzinoSession();
		return(bevandaMagazzino);
	}
	
	@SuppressWarnings("unchecked")
	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.BevandaMagazzino");
			List list = q.list();
			Long id = ((Long)list.get(0)); 
		    return (id + 1);
		} catch(Exception e) {
			System.out.println("BevandaMagazzinoSession#getNewId");
			return null;
		}		
	}
	
	public BevandaMagazzino inserisciBevandaMagazzino(BevandaMagazzino bevandaMagazzino) {
		try {
			Long id = this.getNewId();
			bevandaMagazzino.setId(id);
			session.persist(bevandaMagazzino);
			bevandaMagazzino = (BevandaMagazzino) session.get(BevandaMagazzino.class, bevandaMagazzino.getId());
			
			return bevandaMagazzino; 
		} catch (Exception e) {
			System.err.println("BevandaMagazzinoSession#inserisciBevandaMagazzino");
			return null;
		}
	}
	
	public BevandaMagazzino selezionaBevandaMagazzinoPerId(Long id) {
		try {			
			Query q = session.createQuery("from it.softfood.entity.BevandaMagazzino b where b.id = ? "); 
			q.setLong(0, id);
			BevandaMagazzino bevandaMagazzino = (BevandaMagazzino) q.uniqueResult();
			
		    return bevandaMagazzino;
		} catch (Exception e) {
			System.err.println("BevandaMagazzinoSession#selezionaBevandaMagazzinoPerId");
			return null;
		}
	}
	
	public BevandaMagazzino selezionaBevandaMagazzinoPerIdBevanda(Long id) {
		try {			
			Query q = session.createQuery("from it.softfood.entity.BevandaMagazzino b where b.articolo = ? "); 
			q.setLong(0, id);
			BevandaMagazzino bevandaMagazzino = (BevandaMagazzino) q.uniqueResult();
			
		    return bevandaMagazzino;
		} catch (Exception e) {
			System.err.println("BevandaMagazzinoSession#selezionaBevandaMagazzinoPerIdBevanda");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<BevandaMagazzino> selezionaBevandeMagazzino() {
		try {
			Query q = session.createQuery("from it.softfood.entity.BevandaMagazzino b");
			List<BevandaMagazzino> list = (List<BevandaMagazzino>) q.list();
			
			return list;
		} catch (Exception e) {
			System.err.println("BevandaMagazzinoSession#selezionaBevandeMagazzino");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<BevandaMagazzino> selezionaBevandeMagazzinoPerQuantita(Integer quantita) {
		try {
			Query q = session.createQuery("from it.softfood.entity.BevandaMagazzino b where b.quantita >= ?");
			q.setInteger(0, quantita);
			List<BevandaMagazzino> list = (List<BevandaMagazzino>) q.list();
			
			return list;
		} catch (Exception e) {
			System.err.println("BevandaMagazzinoSession#selezionaBevandeMagazzinoPerQuantita");
			return null;
		}
	}
	
    public boolean rimuoviBevandaMagazzino(Long id) {
    	try {
    		BevandaMagazzino bevandaMagazzino = this.selezionaBevandaMagazzinoPerId(id);

			if (bevandaMagazzino != null) {
				session.delete(bevandaMagazzino);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("BevandaMagazzinoSession#rimuoviBevandaMagazzino");
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

	public void update(BevandaMagazzino bevandaMagazzino) {
		session.update(bevandaMagazzino);
	}
	
}

