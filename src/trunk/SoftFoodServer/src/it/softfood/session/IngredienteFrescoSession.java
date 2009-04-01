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

public class IngredienteFrescoSession {

	private Session session;
	private static IngredienteFrescoSession ingredienteFrescoSession;
	
	public synchronized static IngredienteFrescoSession getInstance() {
		if(ingredienteFrescoSession == null)
			ingredienteFrescoSession = new IngredienteFrescoSession();
		return ingredienteFrescoSession;
	}
	
	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Ingrediente");
			List list = q.list();
		    return (((Long)list.get(0)) + 1);
		} catch(Exception e) {
			System.out.println("IngredienteFrescoSession#getNewId");
			return null;
		}		
	}
	
	public Ingrediente inserisciIngredienteFresco(Ingrediente ingrediente) {
		try {
			Long id = this.getNewId();
			ingrediente.setId(id);
			session.persist(ingrediente);
			
			return (Ingrediente) session.get(Ingrediente.class, ingrediente);
		} catch (Exception e) {
			System.err.println("IngredienteFrescoSession#inserisciIngredienteFresco");
			return null;
		}
	}
	
	public Ingrediente selezionaIngredienteFrescoPerId(Long id) {
		try {
			return (Ingrediente) session.get(Ingrediente.class, id);
		} catch (Exception e) {
			System.err.println("IngredienteFrescoSession#selezionaIngredienteFrescoPerId");
			return null;
		}
	}
	
	public List<Ingrediente> selezionaIngredientiFreschi() {
		try {
			Query q = session.createQuery("from it.softfood.entity.Ingrediente");
			return (List<Ingrediente>) q.list();			
		} catch (Exception e) {
			System.err.println("IngredienteFrescoSession#selezionaIngredientiFreschi");
			return null;
		}
	}
	
    public boolean rimuoviIngredienteFresco(Long id) {
    	try {
    		Ingrediente ingrediente = this.selezionaIngredienteFrescoPerId(id);
			if (ingrediente != null) {
				session.delete(ingrediente);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("IngredienteFrescoSession#rimuoviIngredienteFresco");
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
