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
		try {
			em.persist(ingredienteLungaConservazione);
            return ingredienteLungaConservazione;
        } catch (Exception e) {
            System.err.println("IngredienteLungaConservazioneSessionBean#inserisciIngredienteLungaConservazione");
            return null;
        }
	}
	
	public IngredienteLungaConservazione selezionaIngredienteLungaConservazionePerId(Long id) {
        try {
            return em.find(IngredienteLungaConservazione.class, id);
        } catch (Exception e) {
            System.err.println("IngredienteLungaConservazioneSessionBean#selezionaIngredienteLungaConservazionePerId");
            return null;
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<IngredienteLungaConservazione> selezionaIngredientiLungaConservazione() {
        try {
            return em.createNamedQuery("IngredienteLungaConservazione.selezionaIngredientiLungaConservazione")
                .getResultList();
        } catch (Exception e) {
            System.err.println("IngredienteLungaConservazioneSessionBean#selezionaIngredientiLungaConservazione");
            return null;
        }
	}
	
    public boolean rimuoviIngredienteLungaConservazione(Long id) {
        try {
        	IngredienteLungaConservazione ingredienteLungaConservazione = em.find(IngredienteLungaConservazione.class, id);
            if (ingredienteLungaConservazione != null) {
                em.remove(ingredienteLungaConservazione);
                return true;
            }

            return false;
        } catch (Exception e) {
            System.err.println("IngredienteLungaConservazioneSessionBean#rimuoviIngredienteLungaConservazione");
            return false;
        }
    }
    
}
