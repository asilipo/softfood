package it.softfood.session.ingredientepietanza;

import it.softfood.entity.IngredientePietanza;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class IngredientePietanzaSessionBean implements IngredientePietanzaSessionBeanRemote, IngredientePietanzaSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public IngredientePietanza inserisciIngredientePietanza(IngredientePietanza ingredientePietanza) {
		if (ingredientePietanza != null) 
			em.persist(ingredientePietanza);
		
		return ingredientePietanza;
	}
	
	public IngredientePietanza selezionaIngredientePietanzaPerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(IngredientePietanza.class, id);
	}
	
    public boolean rimuoviIngredientePietanza(Long id) {
        if (id != null) {
        	IngredientePietanza ingredientePietanza = em.find(IngredientePietanza.class, id);
            if (ingredientePietanza != null) {
                em.remove(ingredientePietanza);
                return true;
            }
        }
        
        return false;
    }
    
}
