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
		if (ingredienteFresco != null) 
			em.persist(ingredienteFresco);
		
		return ingredienteFresco;
	}
	
	public IngredienteFresco selezionaIngredienteFrescoPerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(IngredienteFresco.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<IngredienteFresco> selezionaIngredientiFreschi() {
       return em.createNamedQuery("IngredienteFresco.selezionaIngredientiFreschi")
        	.getResultList();
	}
	
    public boolean rimuoviIngredienteFresco(Long id) {
        if (id != null) {
        	IngredienteFresco ingredienteFresco = em.find(IngredienteFresco.class, id);
            if (ingredienteFresco != null) {
                em.remove(ingredienteFresco);
                return true;
            }
        }
        
        return false;
    }
    
}
