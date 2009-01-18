package it.softfood.session.registro;

import it.softfood.entity.Registro;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class RegistroSessionBean implements RegistroSessionBeanRemote, RegistroSessionBeanLocal {

	@PersistenceContext
	private EntityManager em;
	
	public Registro inserisciRegistro(Registro registro) {
        try {
			em.persist(registro);
            return registro;
        } catch (Exception e) {
            System.err.println("RegistroSessionBean#inserisciRegistro");
            return null;
        }
	}
	
	public Registro selezionaRegistroPerId(Long id) {
		try {
            return em.find(Registro.class, id);
        } catch (Exception e) {
            System.err.println("RegistroSessionBean#selezionaRegistroPerId");
            return null;
        }
	}
	
	public Registro selezionaRegistroPerAnno(Integer anno) {
        try {
            return (Registro) em.createNamedQuery("Registro.selezionaRegistroPerAnnoRiferimento")
                .setParameter("anno_riferimento", anno).getSingleResult();
        } catch (Exception e) {
            System.err.println("RegistroSessionBean#selezionaRegistroPerAnno");
            return null;
        }
	}
	
    public boolean rimuoviRegistro(Long id) {
        try {
        	Registro registro = em.find(Registro.class, id);
            if (registro != null) {
                em.remove(registro);
                return true;
            }
            
            return false;
        } catch (Exception e) {
            System.err.println("RegistroSessionBean#selezionaRegistroPerAnno");
            return false;
        }
    }

}
