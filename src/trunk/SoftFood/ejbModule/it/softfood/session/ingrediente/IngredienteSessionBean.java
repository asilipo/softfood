package it.softfood.session.ingrediente;

import it.softfood.entity.Ingrediente;

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
		if (ingrediente != null) 
			em.persist(ingrediente);
		
		return ingrediente;
	}
	
	public Ingrediente selezionaIngredientePerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(Ingrediente.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Ingrediente> selezionaIngredientePerNome(String nome) {
        if (nome == null) 
            return null;
       
       return em.createNamedQuery("Ingrediente.selezionaIngredientiPerNome")
        	.setParameter("nome", nome).getResultList();
	}
	
    public boolean rimuoviIngrediente(Long id) {
        if (id != null) {
        	Ingrediente ingrediente = em.find(Ingrediente.class, id);
            if (ingrediente != null) {
                em.remove(ingrediente);
                return true;
            }
        }
        
        return false;
    }

}
