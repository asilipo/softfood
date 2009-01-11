package it.softfood.session.variante;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Variante;

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
public class VarianteSessionBean implements VarianteSessionBeanRemote, VarianteSessionBeanLocal {

	@PersistenceContext
	private EntityManager em;
	
	public Variante inserisciVariante(Variante variante) {
		if (variante != null)
			em.persist(variante);
		
		return variante;
	}
	
	public Variante selezionaVariantePerId(Long id) {
		if (id != null) 
			return null;
        
        return em.find(Variante.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Variante> selezionaVariantiPerIngrediente(Ingrediente ingrediente) {
        if (ingrediente == null) 
            return null;
       
       return em.createNamedQuery("Variante.selezionaVariantiPerIngrediente")
        	.setParameter("ingrediente", ingrediente).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Variante> selezionaVariantiPerLineaOrdinazione(LineaOrdinazione lineaOrdinazione) {
        if (lineaOrdinazione == null) 
            return null;
       
       return em.createNamedQuery("Variante.selezionaVariantiPerIngrediente")
        	.setParameter("linea_ordinazione", lineaOrdinazione).getResultList();
	}
	
    public boolean rimuoviVariante(Long id) {
        if (id != null) {
        	Variante variante = em.find(Variante.class, id);
            if (variante != null) {
                em.remove(variante);
                return true;
            }
        }
        
        return false;
    }
    
}
