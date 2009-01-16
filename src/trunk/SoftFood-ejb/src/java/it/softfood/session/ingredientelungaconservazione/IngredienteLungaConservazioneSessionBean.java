package it.softfood.session.ingredientelungaconservazione;

import it.softfood.entity.IngredienteLungaConservazione;

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
public class IngredienteLungaConservazioneSessionBean implements IngredienteLungaConservazioneSessionBeanRemote, IngredienteLungaConservazioneSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public IngredienteLungaConservazione inserisciIngredienteLungaConservazione(
			IngredienteLungaConservazione ingredienteLungaConservazione) {
		if (ingredienteLungaConservazione != null) 
			em.persist(ingredienteLungaConservazione);
		
		return ingredienteLungaConservazione;
	}
	
	public IngredienteLungaConservazione selezionaIngredienteLungaConservazionePerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(IngredienteLungaConservazione.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<IngredienteLungaConservazione> selezionaIngredientiLungaConservazione() {
       return em.createNamedQuery("IngredienteLungaConservazione.selezionaIngredientiLungaConservazione")
        	.getResultList();
	}
	
    public boolean rimuoviIngredienteLungaConservazione(Long id) {
        if (id != null) {
        	IngredienteLungaConservazione ingredienteLungaConservazione = em.find(IngredienteLungaConservazione.class, id);
            if (ingredienteLungaConservazione != null) {
                em.remove(ingredienteLungaConservazione);
                return true;
            }
        }
        
        return false;
    }
    
}
