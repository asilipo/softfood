package it.softfood.session;

import it.softfood.entity.IngredientePietanza;
import it.softfood.entity.IngredientePietanzaPK;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class IngredientePietanzaSession {
	
	private Session session;
	private static IngredientePietanzaSession ingredientePietanzaSession;

	public synchronized static IngredientePietanzaSession getInstance() {
		if(ingredientePietanzaSession == null)
			ingredientePietanzaSession = new IngredientePietanzaSession();
		return ingredientePietanzaSession;
	}

	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.IngredientePietanza");
			List list = q.list();
		    return (((Long)list.get(0)) + 1);
		} catch(Exception e) {
			System.out.println("IngredientePietanzaSession#getNewId");
			return null;
		}		
	}
    
	public IngredientePietanza inserisciIngredientePietanza(IngredientePietanza ingredientePietanza) {
		try {
			Long id = this.getNewId();
			IngredientePietanzaPK ingredientePietanzaPK = new IngredientePietanzaPK();
			ingredientePietanzaPK.setIngrediente(ingredientePietanza.getIngrediente().getId());
			ingredientePietanzaPK.setPietanza(ingredientePietanza.getArticolo().getId());
			ingredientePietanza.setId(ingredientePietanzaPK);
			session.persist(ingredientePietanza);
			
			return (IngredientePietanza) session.get(IngredientePietanza.class, ingredientePietanza);
		} catch (Exception e) {
			System.err.println("IngredientePietanzaSession#inserisciIngredientePietanza");
			return null;
		}
	}
	
	public IngredientePietanza selezionaIngredientePietanzaPerId(Long id) {
		try {
			return (IngredientePietanza) session.get(IngredientePietanza.class, id);
		} catch (Exception e) {
			System.err.println("IngredientePietanzaSession#selezionaIngredientePietanzaPerId");
			return null;
		}
	}

    public List<IngredientePietanza> selezionaIngredientiPietanze() {
    	try {
			Query q = session.createQuery("from it.softfood.entity.IngredientePietanza i");
			return (List<IngredientePietanza>) q.list();			
		} catch (Exception e) {
			System.err.println("IngredientePietanzaSession#selezionaIngredientePietanzaPerId");
			return null;
		}
	}
	
    public boolean rimuoviIngredientePietanza(Long id) {
    	try {
    		IngredientePietanza ingredientePietanza = this.selezionaIngredientePietanzaPerId(id);
			if (ingredientePietanza != null) {
				session.delete(ingredientePietanza);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("IngredientePietanzaSession#rimuoviIngredientePietanza");
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
