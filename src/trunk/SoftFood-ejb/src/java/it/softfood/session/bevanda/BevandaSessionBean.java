package it.softfood.session.bevanda;

import it.softfood.entity.Bevanda;

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
public class BevandaSessionBean implements BevandaSessionBeanRemote, BevandaSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;
    
	public Bevanda inserisciBevanda(Bevanda bevanda) {
		try {
			em.persist(bevanda);
            return bevanda;
        } catch (Exception e ) {
            System.err.println("BevandaSessionBean#inserisciBevanda");
            return null;
        }
	}
	
	public Bevanda selezionaBevandaPerId(Long id) {
        try {
            return em.find(Bevanda.class, id);
        } catch (Exception e ) {
            System.err.println("BevandaSessionBean#selezionaBevandaPerId");
            return null;
        }
	}

    @SuppressWarnings("unchecked")
    public List<Bevanda> selezionaBevande() {
		try {
	        return em.createNamedQuery("Bevanda.selezionaBevande")
	        	.getResultList();
		} catch (Exception e) {
			System.err.println("BevandaSessionBean#selezionaBevande");
			return null;
        }
	}

    public boolean rimuoviBevanda(Long id) {
        try {
            Bevanda bevanda = em.find(Bevanda.class, id);
            if (bevanda != null) {
                em.remove(bevanda);
                return true;
            }

            return false;
        } catch (Exception e ) {
            System.err.println("BevandaSessionBean#rimuoviBevanda");
            return false;
        }
    }
    
}
