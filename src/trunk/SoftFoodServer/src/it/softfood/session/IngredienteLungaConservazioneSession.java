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

public class IngredienteLungaConservazioneSession {

	private Session session;
	private static IngredienteLungaConservazioneSession ingredienteLungaConservazioneSession;

	public synchronized static IngredienteLungaConservazioneSession getInstance() {
		if(ingredienteLungaConservazioneSession == null)
			ingredienteLungaConservazioneSession = new IngredienteLungaConservazioneSession();
		return ingredienteLungaConservazioneSession;
	}

	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Ingrediente");
			List list = q.list();
		    return (((Long)list.get(0)) + 1);
		} catch(Exception e) {
			System.out.println("IngredienteLungaConservazioneSession#getNewId");
			return null;
		}		
	}
	
	public Ingrediente inserisciIngredienteLungaConservazione(Ingrediente ingrediente) {
		try {
			Long id = this.getNewId();
			ingrediente.setId(id);
			session.persist(ingrediente);
			
			return (Ingrediente) session.get(Ingrediente.class, ingrediente);
		} catch (Exception e) {
			System.err.println("IngredienteLungaConservazioneSession#inserisciIngredienteLungaConservazione");
			return null;
		}
	}
	
	public Ingrediente selezionaIngredienteLungaConservazionePerId(Long id) {
		try {
			return (Ingrediente) session.get(Ingrediente.class, id);
		} catch (Exception e) {
			System.err.println("IngredienteLungaConservazioneSession#selezionaIngredienteLungaConservazionePerId");
			return null;
		}
	}
	
	public List<Ingrediente> selezionaIngredientiLungaConservazione() {
		try {
			Query q = session.createQuery("from it.softfood.entity.Ingrediente t");
			return (List<Ingrediente>) q.list();			
		} catch (Exception e) {
			System.err.println("IngredienteLungaConservazioneSession#selezionaIngredientiLungaConservazione");
			return null;
		}
	}
	
    public boolean rimuoviIngredienteLungaConservazione(Long id) {
    	try {
			Ingrediente ingrediente = this.selezionaIngredienteLungaConservazionePerId(id);
			if (ingrediente != null) {
				session.delete(ingrediente);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("IngredienteLungaConservazioneSession#rimuoviIngredienteLungaConservazione");
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