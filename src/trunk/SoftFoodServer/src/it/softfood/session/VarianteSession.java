package it.softfood.session;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Variante;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class VarianteSession  {

	private Session session;
	private static VarianteSession varianteSession;

	public synchronized static VarianteSession getInstance() {
		if(varianteSession == null)
			varianteSession = new VarianteSession();
		return varianteSession;
	}

	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Variante");
			Long id = (Long)q.uniqueResult();
		    if(id==null)
		    	return new Long(0);
			return (id + 1);
		} catch(Exception e) {
			System.out.println("VarianteSession#getNewId");
			return null;
		}		
	}

	public Variante inserisciVariante(Variante variante) {
		try {		
			Long id = this.getNewId();
			variante.setId(id);
			session.persist(variante);
			variante = (Variante) this.selezionaVariantePerId(variante.getId());
			
			return variante; 
		} catch (Exception e) {
			System.err.println("VarianteSession#inserisciVariante");
			return null;
		}
	}
	
	public Variante selezionaVariantePerId(Long id) {
		try {
			Variante variante = (Variante) session.get(Variante.class, id);
			
			return variante;
		} catch (Exception e) {
			System.err.println("VarianteSession#selezionaVariantePerId");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Variante> selezionaVariantiPerIngrediente(Ingrediente ingrediente) {
		try {
			Query q = session.createQuery("from it.softfood.entity.Variante v where v.ingrediente = ?");
			q.setLong(0, ingrediente.getId());
			List<Variante> list = (List<Variante>) q.list();
			
			return list;
		} catch (Exception e) {
			System.err.println("VarianteSession#selezionaVariantiPerIngrediente");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Variante> selezionaVariantiPerLineaOrdinazione(LineaOrdinazione lineaOrdinazione) {
		try {
			Query q = session.createQuery("from it.softfood.entity.Variante v where v.lineaOrdinazione = ?");
			q.setLong(0, lineaOrdinazione.getId());			
			List<Variante> list = (List<Variante>) q.list();
			
			return list;
		} catch (Exception e) {
			System.err.println("VarianteSession#selezionaVariantiPerLineaOrdinazione");
			return null;
		}
	}
	
    public boolean rimuoviVariante(Long id) {
    	try {
    		Variante variante = this.selezionaVariantePerId(id);
			if (variante != null) {
				session.delete(variante);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("VarianteSession#rimuoviVariante");
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
	
	public void update(Variante variante) {
		session.update(variante);
	}
    
}
