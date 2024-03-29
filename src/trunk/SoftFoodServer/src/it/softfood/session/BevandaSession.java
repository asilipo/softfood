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

	@SuppressWarnings("unchecked")
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
			Long id=0L;
			if(bevanda.getId()==null)
				id = this.getNewId();
			else
				id = bevanda.getId();
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
				bevanda.setBevandaMagazzinos(articolo.getBevandaMagazzinos());
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

	@SuppressWarnings("unchecked")
	public ArrayList<Bevanda> selezionaBevande() {
		try {
			Query q = session.createQuery("from it.softfood.entity.Articolo o where tipoArticolo = 'Bevanda'");
			List<Articolo> articoli = (List<Articolo>) q.list();
			ArrayList<Bevanda> bevande = new ArrayList<Bevanda>();
			
			for (Articolo articolo : articoli) {
				if (articolo.getTipoArticolo().equals("Bevanda")) {
					Bevanda bevanda = new Bevanda();
					bevanda.setDescrizione(articolo.getDescrizione());
					bevanda.setId(articolo.getId());
					bevanda.setLineaOrdinaziones(articolo.getLineaOrdinaziones());
					bevanda.setBevandaMagazzinos(articolo.getBevandaMagazzinos());
					bevanda.setListino(articolo.getListino());
					bevanda.setNome(articolo.getNome());
					bevanda.setCapacita(articolo.getCapacita());
					bevanda.setTipoArticolo(articolo.getTipoArticolo());
					bevanda.setTipoPietanza(articolo.getTipoPietanza());
					
					bevande.add(bevanda);
				}
			}
			
			return bevande;
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
		if (bevanda != null) {
			Articolo articolo = (Articolo) session.get(Articolo.class, bevanda.getId());
			if(articolo != null) {
				articolo.setDescrizione(bevanda.getDescrizione());
				articolo.setId(bevanda.getId());
				articolo.setLineaOrdinaziones(bevanda.getLineaOrdinaziones());
				articolo.setListino(bevanda.getListino());
				articolo.setNome(bevanda.getNome());
				articolo.setTipoArticolo(bevanda.getTipoArticolo());
				articolo.setTipoPietanza(bevanda.getTipoPietanza());
				articolo.setCapacita(bevanda.getCapacita());
				articolo.setBevandaMagazzinos(bevanda.getBevandaMagazzinos());
				session.update(articolo);
			}
		}
	}
	
}
