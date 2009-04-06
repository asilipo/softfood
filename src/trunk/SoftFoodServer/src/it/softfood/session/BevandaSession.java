package it.softfood.session;

import it.softfood.entity.Articolo;
import it.softfood.entity.Bevanda;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class BevandaSession {

	private Session session;
	private static BevandaSession bevandaSession;

	public synchronized static BevandaSession getInstance() {
		if(bevandaSession == null)
			bevandaSession = new BevandaSession();
		return bevandaSession;
	}

	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Bevanda");
			List list = q.list();
			Long id = (Long) list.get(0);
			
			return (id + 1);
		} catch(Exception e) {
			System.out.println("BevandaSession#getNewId");
			return null;
		}		
	}

	public Bevanda inserisciBevanda(Bevanda bevanda) {
		try {
			Long id = this.getNewId();
			bevanda.setId(id);
			session.persist(bevanda);
			bevanda = (Bevanda) session.get(Bevanda.class, bevanda);
			
			return bevanda; 
		} catch (Exception e) {
			System.err.println("BevandaSession#inserisciBevanda");
			return null;
		}
	}

	public Bevanda selezionaBevandaPerId(Long id) {
		try {
			Articolo articolo = (Articolo) session.get(Articolo.class, id);
			Bevanda bevanda = new Bevanda();
			bevanda.setBevandaMagazzinos(articolo.getBevandaMagazzinos());
			bevanda.setCapacita(articolo.getCapacita());
			bevanda.setDescrizione(articolo.getDescrizione());
			bevanda.setId(articolo.getId());
			bevanda.setLineaOrdinaziones(articolo.getLineaOrdinaziones());
			bevanda.setListino(articolo.getListino());
			bevanda.setNome(articolo.getNome());
			bevanda.setTipoArticolo(articolo.getTipoArticolo());
			
			return bevanda; 
		} catch (Exception e) {
			System.err.println("BevandaSession#selezionaBevandaPerId " + e);
			return null;
		}
	}

	public ArrayList<Bevanda> selezionaBevande() {
		try {
			Query q = session.createQuery("from it.softfood.entity.Bevanda b");
			ArrayList<Bevanda> list = (ArrayList<Bevanda>) q.list();
			
			return list;			
		} catch (Exception e) {
			System.err.println("BevandaSession#selezionaBevande");
			return null;
		}
	}

	public boolean rimuoviBevanda(Long id) {
		try {
			Bevanda bevanda = this.selezionaBevandaPerId(id);
			if (bevanda != null) {
				session.delete(bevanda);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("BevandaSession#selezionaBevandaPerId");
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

	public void update(Bevanda bevanda) {
		session.update(bevanda);
	}
	
}
