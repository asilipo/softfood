package it.softfood.session.ingredientemagazzino;

import it.softfood.entity.IngredienteMagazzino;

import java.util.ArrayList;
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
		try {
			em.persist(ingredienteMagazzino);
            return ingredienteMagazzino;
        } catch (Exception e) {
            System.err.println("IngredienteMagazzinoSessionBean#inserisciIngredienteMagazzino");
            return null;
        }
	}
	
	public IngredienteMagazzino selezionaIngredienteMagazzinoPerId(Long id) {
        try {
            return em.find(IngredienteMagazzino.class, id);
        } catch (Exception e) {
            System.err.println("IngredienteMagazzinoSessionBean#selezionaIngredienteMagazzinoPerId");
            return null;
        }
	}

	@SuppressWarnings("unchecked")
	public List<IngredienteMagazzino> selezionaIngredientiMagazzino() {
        try {
            return (ArrayList<IngredienteMagazzino>) em.createNamedQuery("IngredienteMagazzino.selezionaIngredientiMagazzino")
                .getResultList();
        } catch (Exception e) {
            System.err.println("IngredienteMagazzinoSessionBean#selezionaIngredientiLungaConservazione");
            return null;
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<IngredienteMagazzino> selezionaIngredientiLungaConservazionePerQuantita(Integer quantita) {
        try {
            if (quantita == null || quantita < 0)
                quantita = 0;

            return (ArrayList<IngredienteMagazzino>) em.createNamedQuery("IngredienteMagazzino.selezionaIngredientiLungaConservazionePerQuantita")
                .setParameter("quantita", quantita).getResultList();
        } catch (Exception e) {
            System.err.println("IngredienteMagazzinoSessionBean#selezionaIngredientiLungaConservazionePerQuantita");
            return null;
        }
	}
	
    public boolean rimuoviIngredienteMagazzino(Long id) {
        try {
            IngredienteMagazzino ingredienteMagazzino = em.find(IngredienteMagazzino.class, id);
            if (ingredienteMagazzino != null) {
                em.remove(ingredienteMagazzino);
                return true;
            }

            return false;
        } catch (Exception e) {
            System.err.println("IngredienteMagazzinoSessionBean#rimuoviIngredienteMagazzino");
            return false;
        }
    }

}
