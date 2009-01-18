package it.softfood.session.ingredientefresco;

import it.softfood.entity.IngredienteFresco;

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
public class IngredienteFrescoSessionBean implements IngredienteFrescoSessionBeanRemote, IngredienteFrescoSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public IngredienteFresco inserisciIngredienteFresco(IngredienteFresco ingredienteFresco) {
		try {
			em.persist(ingredienteFresco);
            return ingredienteFresco;
        } catch (Exception e) {
            System.err.println("IngredienteFrescoSessionBean#inserisciIngredienteFresco");
            return null;
        }
	}
	
	public IngredienteFresco selezionaIngredienteFrescoPerId(Long id) {
        try {
            return em.find(IngredienteFresco.class, id);
        } catch (Exception e) {
            System.err.println("IngredienteFrescoSessionBean#selezionaIngredienteFrescoPerId");
            return null;
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<IngredienteFresco> selezionaIngredientiFreschi() {
        try {
            return em.createNamedQuery("IngredienteFresco.selezionaIngredientiFreschi")
                .getResultList();
        } catch (Exception e) {
            System.err.println("IngredienteFrescoSessionBean#selezionaIngredientiFreschi");
            return null;
        }
	}
	
    public boolean rimuoviIngredienteFresco(Long id) {
        try {
        	IngredienteFresco ingredienteFresco = em.find(IngredienteFresco.class, id);
            if (ingredienteFresco != null) {
                em.remove(ingredienteFresco);
                return true;
            }

            return false;
        } catch (Exception e) {
            System.err.println("IngredienteFrescoSessionBean#rimuoviIngredienteFresco");
            return false;
        }
    }
    
}
