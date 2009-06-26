package it.softfood.session;

import it.softfood.entity.Articolo;
import it.softfood.entity.Pietanza;
import it.softfood.enumeration.TipoPietanza;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class PietanzaSession {

	private Session session;
	private static PietanzaSession pietanzaSession;

	public synchronized static PietanzaSession getInstance() {
		if(pietanzaSession == null)
			pietanzaSession = new PietanzaSession();
		return pietanzaSession;
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
			System.out.println("PietanzaSession#getNewId");
			return null;
		}		
	}
	
	public Pietanza inserisciPietanza(Pietanza pietanza) {
		try {
			Long id = this.getNewId();
			
			Articolo articolo = new Articolo();
			articolo.setDescrizione(pietanza.getDescrizione());
			articolo.setId(id);
			articolo.setListino(pietanza.getListino());
			articolo.setNome(pietanza.getNome());
			articolo.setTipoArticolo(pietanza.getTipoArticolo());
			articolo.setTipoPietanza(pietanza.getTipoPietanza());
			session.persist(articolo);
			pietanza = (Pietanza) this.selezionaPietanzaPerId(articolo.getId());

			return pietanza; 
		} catch (Exception e) {
			System.err.println("PietanzaSession#inserisciPietanza");
			return null;
		}
	}
	
	public Pietanza selezionaPietanzaPerId(Long id) {
		try {
			Articolo articolo = (Articolo) session.get(Articolo.class, id);
			if(articolo != null) {
				Pietanza pietanza = new Pietanza();
				pietanza.setBevandaMagazzinos(articolo.getBevandaMagazzinos());
				pietanza.setCapacita(articolo.getCapacita());
				pietanza.setDescrizione(articolo.getDescrizione());
				pietanza.setId(articolo.getId());
				pietanza.setIngredientePietanzas(articolo.getIngredientePietanzas());
				pietanza.setLineaOrdinaziones(articolo.getLineaOrdinaziones());
				pietanza.setListino(articolo.getListino());
				pietanza.setNome(articolo.getNome());
				pietanza.setTipoArticolo(articolo.getTipoArticolo());
				pietanza.setTipoPietanza(articolo.getTipoPietanza());	
				pietanza.setTipo(TipoPietanza.values()[articolo.getTipoPietanza()]);
				return pietanza;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("PietanzaSession#selezionaPietanzaPerId");
			return null;
		}
	}

    @SuppressWarnings("unchecked")
	public List<Pietanza> selezionaPietanzePerTipo(TipoPietanza tipoPietanza) {
    	try {
			Query q = session.createQuery("from it.softfood.entity.Articolo o where o.tipoPietanza = ?");
			q.setInteger(0, tipoPietanza.ordinal());
			List<Articolo> articoli = (List<Articolo>) q.list();
			List<Pietanza> pietanze = new ArrayList<Pietanza>();
			
			for (Articolo articolo : articoli) {
				if (articolo.getTipoArticolo().equals("Pietanza")) {
					Pietanza pietanza = new Pietanza();
					pietanza.setDescrizione(articolo.getDescrizione());
					pietanza.setId(articolo.getId());
					pietanza.setIngredientePietanzas(articolo.getIngredientePietanzas());
					pietanza.setLineaOrdinaziones(articolo.getLineaOrdinaziones());
					pietanza.setListino(articolo.getListino());
					pietanza.setNome(articolo.getNome());
					pietanza.setTipo(TipoPietanza.values()[articolo.getTipoPietanza()]);
					pietanza.setTipoArticolo(articolo.getTipoArticolo());
					pietanza.setTipoPietanza(articolo.getTipoPietanza());
					
					pietanze.add(pietanza);
				}
			}
			
			return pietanze;
		} catch (Exception e) {
			System.err.println("PietanzaSession#selezionaPietanzePerTipo" + e);
			return null;
		}
	}

    @SuppressWarnings("unchecked")
	public ArrayList<Pietanza> selezionaPietanze() {
    	try {
			Query q = session.createQuery("from it.softfood.entity.Articolo o where tipoArticolo = 'Pietanza'");
			List<Articolo> articoli = (List<Articolo>) q.list();
			ArrayList<Pietanza> pietanze = new ArrayList<Pietanza>();
			
			for (Articolo articolo : articoli) {
				if (articolo.getTipoArticolo().equals("Pietanza")) {
					Pietanza pietanza = new Pietanza();
					pietanza.setDescrizione(articolo.getDescrizione());
					pietanza.setId(articolo.getId());
					pietanza.setIngredientePietanzas(articolo.getIngredientePietanzas());
					pietanza.setLineaOrdinaziones(articolo.getLineaOrdinaziones());
					pietanza.setListino(articolo.getListino());
					pietanza.setNome(articolo.getNome());
					pietanza.setTipo(TipoPietanza.values()[articolo.getTipoPietanza()]);
					pietanza.setTipoArticolo(articolo.getTipoArticolo());
					pietanza.setTipoPietanza(articolo.getTipoPietanza());
					
					pietanze.add(pietanza);
				}
			}
			
			return pietanze;
		} catch (Exception e) {
			System.err.println("PietanzaSession#selezionaPietanze");
			return null;
		}
	}

    public boolean rimuoviPietanza(Long id) {
	  	try {
	  		Articolo articolo = (Articolo) session.get(Articolo.class, id);
	  		if (articolo == null) {
	  			Pietanza pietanza = this.selezionaPietanzaPerId(id);
	  			if (pietanza != null) {
		  			articolo = new Articolo();
					articolo.setDescrizione(pietanza.getDescrizione());
					articolo.setId(pietanza.getId());
					articolo.setListino(pietanza.getListino());
					articolo.setNome(pietanza.getNome());
					articolo.setTipoArticolo(pietanza.getTipoArticolo());
					articolo.setTipoPietanza(pietanza.getTipoPietanza());
	  			}
	  		}
			session.delete(articolo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
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
    
	public void update(Pietanza pietanza) {
		if (pietanza != null) {
			Articolo articolo = (Articolo) session.get(Articolo.class, pietanza.getId());
			if(articolo != null) {
				articolo.setDescrizione(pietanza.getDescrizione());
				articolo.setId(pietanza.getId());
				articolo.setIngredientePietanzas(pietanza.getIngredientePietanzas());
				articolo.setLineaOrdinaziones(pietanza.getLineaOrdinaziones());
				articolo.setListino(pietanza.getListino());
				articolo.setNome(pietanza.getNome());
				articolo.setTipoArticolo(pietanza.getTipoArticolo());
				articolo.setTipoPietanza(pietanza.getTipoPietanza());
				session.update(articolo);
			}
		}
	}
	
}

