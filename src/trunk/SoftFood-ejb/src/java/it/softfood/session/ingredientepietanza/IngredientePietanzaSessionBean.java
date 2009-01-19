package it.softfood.session.ingredientepietanza;

import it.softfood.entity.IngredientePietanza;

import java.util.List;
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
		try {
			em.persist(ingredientePietanza);
            return ingredientePietanza;
        } catch (Exception e) {
            System.err.println("IngredientePietanzaSessionBean#inserisciIngredientePietanza");
            return null;
        }
	}
	
	public IngredientePietanza selezionaIngredientePietanzaPerId(Long id) {
        try {
            return em.find(IngredientePietanza.class, id);
        } catch (Exception e) {
            System.err.println("IngredientePietanzaSessionBean#selezionaIngredientePietanzaPerId");
            return null;
        }
	}

    public List<IngredientePietanza> selezionaIngredientiPietanze() {
        try {
            return em.createNamedQuery("IngredientePietanza.selezionaIngredientiPietanze")
	        	.getResultList();
        } catch (Exception e) {
            System.err.println("IngredientePietanzaSessionBean#selezionaIngredientiPietanza");
            return null;
        }
	}
	
    public boolean rimuoviIngredientePietanza(Long id) {
        try {
        	IngredientePietanza ingredientePietanza = em.find(IngredientePietanza.class, id);
            if (ingredientePietanza != null) {
                em.remove(ingredientePietanza);
                return true;
            }

            return false;
        } catch (Exception e) {
            System.err.println("IngredientePietanzaSessionBean#rimuoviIngredientePietanza");
            return false;
        }
    }
    
}
