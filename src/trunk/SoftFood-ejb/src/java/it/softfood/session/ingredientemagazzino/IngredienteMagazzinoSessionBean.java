package it.softfood.session.ingredientemagazzino;

import it.softfood.entity.IngredienteMagazzino;

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
public class IngredienteMagazzinoSessionBean implements IngredienteMagazzinoSessionBeanRemote, IngredienteMagazzinoSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public IngredienteMagazzino inserisciIngredienteMagazzino(IngredienteMagazzino ingredienteMagazzino) {
		if (ingredienteMagazzino != null) 
			em.persist(ingredienteMagazzino);
		
		return ingredienteMagazzino;
	}
	
	public IngredienteMagazzino selezionaIngredienteMagazzinoPerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(IngredienteMagazzino.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<IngredienteMagazzino> selezionaIngredientiLungaConservazione() {       
       return em.createNamedQuery("IngredienteMagazzino.selezionaIngredientiLungaConservazione")
        	.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<IngredienteMagazzino> selezionaIngredientiLungaConservazionePerQuantita(Integer quantita) {
        if (quantita == null || quantita < 0) 
            quantita = 0;
       
       return em.createNamedQuery("IngredienteMagazzino.selezionaIngredientiLungaConservazionePerQuantita")
        	.setParameter("quantita", quantita).getResultList();
	}
	
    public boolean rimuoviIngredienteMagazzino(Long id) {
        if (id != null) {
        	IngredienteMagazzino ingredienteMagazzino = em.find(IngredienteMagazzino.class, id);
            if (ingredienteMagazzino != null) {
                em.remove(ingredienteMagazzino);
                return true;
            }
        }
        
        return false;
    }

}
