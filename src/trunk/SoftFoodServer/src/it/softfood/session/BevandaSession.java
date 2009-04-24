package it.softfood.session;

import it.softfood.entity.Articolo;
import it.softfood.entity.Bevanda;
import it.softfood.entity.Pietanza;

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
			Query q = session.createQuery("select max(id) from it.softfood.entity.Articolo");
			List list = q.list();
		    Long id = ((Long)list.get(0));
		    if (id == null)
		    	id = 0L;
		    return (id + 1);
		} catch(Exception e) {
			
			System.out.println("BevandaSession#getNewId");
			return null;
		}		
	}

	public Bevanda inserisciBevanda(Bevanda bevanda) {
		try {
			Long id = this.getNewId();
			Articolo articolo = new Articolo();
			articolo.setDescrizione(bevanda.getDescrizione());
			articolo.setId(id);
			articolo.setListino(bevanda.getListino());
			articolo.setNome(bevanda.getNome());
			articolo.setTipoArticolo(bevanda.getTipoArticolo());
			articolo.setTipoPietanza(bevanda.getTipoPietanza());
			articolo.setCapacita(bevanda.getCapacita());
			session.persist(articolo);
			bevanda = (Bevanda) this.selezionaBevandaPerId(articolo.getId());
			
			return bevanda; 
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("BevandaSession#inserisciBevanda");
			return null;
		}
	}

	public Bevanda selezionaBevandaPerId(Long id) {
		try {
			Articolo articolo = (Articolo) session.get(Articolo.class, id);
			if (articolo != null) {
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
			}
			return null; 
		} catch (Exception e) {
			System.err.println("BevandaSession#selezionaBevandaPerId ");
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
	  		Articolo articolo = (Articolo) session.get(Articolo.class, id);
	  		if (articolo == null) {
	  			Bevanda bevanda = this.selezionaBevandaPerId(id);
	  			if (bevanda != null) {
		  			articolo = new Articolo();
		  			articolo.setDescrizione(bevanda.getDescrizione());
					articolo.setId(id);
					articolo.setListino(bevanda.getListino());
					articolo.setNome(bevanda.getNome());
					articolo.setTipoArticolo(bevanda.getTipoArticolo());
					articolo.setTipoPietanza(bevanda.getTipoPietanza());
					articolo.setCapacita(bevanda.getCapacita());
	  			}
	  		}
			session.delete(articolo);
			return true;
		} catch (Exception e) {
			System.err.println("PietanzaSession#rimuoviPietanza");
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
