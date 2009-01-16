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
		if (registro != null)
			em.persist(registro);
		
		return registro;
	}
	
	public Registro selezionaRegistroPerId(Long id) {
		if (id != null) 
			return null;
        
        return em.find(Registro.class, id);
	}
	
	public Registro selezionaRegistroPerAnno(Integer anno) {
        if (anno == null) 
            return null;
       
       return (Registro) em.createNamedQuery("Registro.selezionaRegistroPerAnnoRiferimento")
        	.setParameter("anno_riferimento", anno).getSingleResult();
	}
	
    public boolean rimuoviRegistro(Long id) {
        if (id != null) {
        	Registro registro = em.find(Registro.class, id);
            if (registro != null) {
                em.remove(registro);
                return true;
            }
        }
        
        return false;
    }

}
