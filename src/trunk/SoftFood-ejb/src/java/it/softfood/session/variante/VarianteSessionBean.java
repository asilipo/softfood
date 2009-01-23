package it.softfood.session.variante;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Variante;

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
public class VarianteSessionBean implements VarianteSessionBeanRemote, VarianteSessionBeanLocal {

	@PersistenceContext
	private EntityManager em;
	
	public Variante inserisciVariante(Variante variante) {
        try {
            em.persist(variante);
            return variante;
         } catch (Exception e) {
            System.err.println("VarianteSessionBean#inserisciVariante");
            return null;
         }
	}
	
	public Variante selezionaVariantePerId(Long id) {
        try {
            return em.find(Variante.class, id);
         } catch (Exception e) {
            System.err.println("VarianteSessionBean#selezionaVariantePerId");
            return null;
         }
	}
	
	public List<Variante> selezionaVariantiPerIngrediente(Ingrediente ingrediente) {
        try {
            return (ArrayList<Variante>) em.createNamedQuery("Variante.selezionaVariantiPerIngrediente")
                .setParameter("ingrediente", ingrediente).getResultList();
         } catch (Exception e) {
            System.err.println("VarianteSessionBean#selezionaVariantiPerIngrediente");
            return null;
         }
	}
	
	public List<Variante> selezionaVariantiPerLineaOrdinazione(LineaOrdinazione lineaOrdinazione) {
        try {
            return (ArrayList<Variante>) em.createNamedQuery("Variante.selezionaVariantiPerIngrediente")
                .setParameter("linea_ordinazione", lineaOrdinazione).getResultList();
         } catch (Exception e) {
            System.err.println("VarianteSessionBean#selezionaVariantiPerLineaOrdinazione");
            return null;
         }
	}
	
    public boolean rimuoviVariante(Long id) {
        try {
        	Variante variante = em.find(Variante.class, id);
            if (variante != null) {
                em.remove(variante);
                return true;
            }

            return false;
        } catch (Exception e) {
            System.err.println("VarianteSessionBean#rimuoviVariante");
            return false;
        }
    }
    
}
