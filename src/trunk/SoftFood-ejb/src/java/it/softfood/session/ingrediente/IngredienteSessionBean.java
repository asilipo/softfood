package it.softfood.session.ingrediente;

import it.softfood.entity.Ingrediente;

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
public class IngredienteSessionBean implements IngredienteSessionBeanRemote, IngredienteSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public Ingrediente inserisciIngrediente(Ingrediente ingrediente) {
		try {
			em.persist(ingrediente);
            return ingrediente;
        } catch (Exception e) {
            System.err.println("IngredienteSessionBean#inserisciIngrediente");
            return null;
        }
	}
	
	public Ingrediente selezionaIngredientePerId(Long id) {
        try {
            return em.find(Ingrediente.class, id);
        } catch (Exception e) {
            System.err.println("IngredienteSessionBean#selezionaIngredientePerId");
            return null;
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<Ingrediente> selezionaIngredientePerNome(String nome) {
        try {
            return (ArrayList<Ingrediente>) em.createNamedQuery("Ingrediente.selezionaIngredientiPerNome")
                .setParameter("nome", nome).getResultList();
        } catch (Exception e) {
            System.err.println("IngredienteSessionBean#selezionaIngredientePerNome");
            return null;
        }
	}
	
    public boolean rimuoviIngrediente(Long id) {
        try {
        	Ingrediente ingrediente = em.find(Ingrediente.class, id);
            if (ingrediente != null) {
                em.remove(ingrediente);
                return true;
            }

            return false;
        } catch (Exception e) {
            System.err.println("IngredienteSessionBean#rimuoviIngrediente");
            return false;
        }
    }

}
