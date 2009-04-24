package it.softfood.session;

import it.softfood.entity.Ingrediente;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class IngredienteSession {

	private Session session;
	private static IngredienteSession ingredienteSession;

	public synchronized static IngredienteSession getInstance() {
		if(ingredienteSession == null)
			ingredienteSession = new IngredienteSession();
		return ingredienteSession;
	}

	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Ingrediente");
			List list = q.list();
		    Long id = ((Long)list.get(0));
		    if (id == null)
		    	id = 0L;
		    return (id + 1);
		} catch(Exception e) {
			System.out.println("IngredienteSession#getNewId");
			return null;
		}		
	}
	
	public Ingrediente inserisciIngrediente(Ingrediente ingrediente) {
		try {
			Long id = this.getNewId();
			ingrediente.setId(id);
			session.persist(ingrediente);
			ingrediente = (Ingrediente) session.get(Ingrediente.class, ingrediente.getId());
			
			return ingrediente;
		} catch (Exception e) {
			System.err.println("IngredienteSession#inserisciIngrediente");
			return null;
		}
	}
	
	public Ingrediente selezionaIngredientePerId(Long id) {
		try {
			Ingrediente ingrediente = (Ingrediente) session.get(Ingrediente.class, id);
			
			return ingrediente;
		} catch (Exception e) {
			System.err.println("IngredienteSession#selezionaIngredientePerId");
			return null;
		}
	}
	
	public List<Ingrediente> selezionaIngredientePerNome(String nome) {
		try {
			Query q = session.createQuery("from it.softfood.entity.Ingrediente i where i.nome = ?");
			q.setString(0, nome);
			List<Ingrediente> list = (List<Ingrediente>) q.list();
			
			return list; 	
		} catch (Exception e) {
			System.err.println("IngredienteSession#selezionaIngredientePerNome");
			return null;
		}
	}
	
	public List<Ingrediente> selezionaIngredienti() {
		try {
			Query q = session.createQuery("from it.softfood.entity.Ingrediente i");
			List<Ingrediente> list = (List<Ingrediente>) q.list();
			
			return list; 	
		} catch (Exception e) {
			System.err.println("IngredienteSession#selezionaIngredienti");
			return null;
		}
	}

	public List<Ingrediente> selezionaIngredientePerVariante() {
		try {
			Query q = session.createQuery("from it.softfood.entity.Ingrediente i");
			List<Ingrediente> list = (List<Ingrediente>) q.list();
			
			return list; 	
		} catch (Exception e) {
			System.err.println("IngredienteSession#selezionaIngredientePerVariante");
			return null;
		}
	}
	
    public boolean rimuoviIngrediente(Long id) {
    	try {
    		Ingrediente ingrediente = this.selezionaIngredientePerId(id);
			if (ingrediente != null) {
				session.delete(ingrediente);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("IngredienteSession#rimuoviIngrediente");
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

	public void update(Ingrediente ingrediente) {
		session.update(ingrediente);
	}
	
}
