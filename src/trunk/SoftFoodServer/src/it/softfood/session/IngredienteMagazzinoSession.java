package it.softfood.session;

import it.softfood.entity.IngredienteMagazzino;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class IngredienteMagazzinoSession {
    
	private Session session;
	private static IngredienteMagazzinoSession ingredienteMagazzinoSession;

	public synchronized static IngredienteMagazzinoSession getInstance() {
		if(ingredienteMagazzinoSession == null)
			ingredienteMagazzinoSession = new IngredienteMagazzinoSession();
		return ingredienteMagazzinoSession;
	}

	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.IngredienteMagazzino");
			List list = q.list();
		    return (((Long)list.get(0)) + 1);
		} catch(Exception e) {
			System.out.println("IngredienteMagazzinoSession#getNewId");
			return null;
		}		
	}
	public IngredienteMagazzino inserisciIngredienteMagazzino(IngredienteMagazzino ingredienteMagazzino) {
		try {
			Long id = this.getNewId();
			ingredienteMagazzino.setId(id);
			session.persist(ingredienteMagazzino);
			
			return (IngredienteMagazzino) session.get(IngredienteMagazzino.class, ingredienteMagazzino);
		} catch (Exception e) {
			System.err.println("IngredienteMagazzinoSession#ingredienteMagazzino");
			return null;
		}
	}
	
	public IngredienteMagazzino selezionaIngredienteMagazzinoPerId(Long id) {
		try {
			return (IngredienteMagazzino) session.get(IngredienteMagazzino.class, id);
		} catch (Exception e) {
			System.err.println("IngredienteMagazzinoSession#selezionaIngredienteMagazzinoPerId");
			return null;
		}
	}

	public List<IngredienteMagazzino> selezionaIngredientiMagazzino() {
		try {
			Query q = session.createQuery("from it.softfood.entity.IngredienteMagazzino i");
			return (List<IngredienteMagazzino>) q.list();			
		} catch (Exception e) {
			System.err.println("IngredienteMagazzinoSession#selezionaIngredientiMagazzino");
			return null;
		}
	}
	
	public List<IngredienteMagazzino> selezionaIngredientiLungaConservazionePerQuantita(Integer quantita) {
		try {
			Query q = session.createQuery("from it.softfood.entity.IngredienteMagazzino i where i.quantita = ?");
			q.setInteger(0, quantita);
			return (List<IngredienteMagazzino>) q.list();			
		} catch (Exception e) {
			System.err.println("IngredienteMagazzinoSession#selezionaIngredientiLungaConservazionePerQuantita");
			return null;
		}
	}
	
    public boolean rimuoviIngredienteMagazzino(Long id) {
    	try {
    		IngredienteMagazzino ingredienteMagazzino = this.selezionaIngredienteMagazzinoPerId(id);
			if (ingredienteMagazzino != null) {
				session.delete(ingredienteMagazzino);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("IngredienteMagazzino#rimuoviIngredienteMagazzino");
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
