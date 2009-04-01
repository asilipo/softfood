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
			Query q = session.createQuery("select max(id) from it.softfood.entity.IngredienteSession");
		    return (((Long)q.list().get(0)) + 1);
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
			
			return (Ingrediente) session.get(Ingrediente.class, ingrediente);
		} catch (Exception e) {
			System.err.println("IngredienteSession#inserisciIngrediente");
			return null;
		}
	}
	
	public Ingrediente selezionaIngredientePerId(Long id) {
		try {
			return (Ingrediente) session.get(Ingrediente.class, id);
		} catch (Exception e) {
			System.err.println("IngredienteSession#selezionaIngredientePerId");
			return null;
		}
	}
	
	public List<Ingrediente> selezionaIngredientePerNome(String nome) {
		try {
			Query q = session.createQuery("from it.softfood.entity.Ingrediente i where i.nome = ?");
			q.setString(0, nome);
			return (List<Ingrediente>) q.list();	
		} catch (Exception e) {
			System.err.println("IngredienteSession#selezionaIngredientePerNome");
			return null;
		}
	}

	public List<Ingrediente> selezionaIngredientePerVariante() {
		try {
			Query q = session.createQuery("from it.softfood.entity.Ingrediente i");
			return (List<Ingrediente>) q.list();	
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

}
