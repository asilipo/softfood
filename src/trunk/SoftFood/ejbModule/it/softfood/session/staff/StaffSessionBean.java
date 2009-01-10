package it.softfood.session.staff;

import it.softfood.entity.Staff;
import it.softfood.enumeration.TipoStaff;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class StaffSessionBean implements StaffSessionBeanRemote, StaffSessionBeanLocal {

	@PersistenceContext
    private EntityManager em;
    
	public Staff inserisciStaff(Staff staff) {
		if (staff != null) 
			em.persist(staff);
		
		return staff;
	}
	
	public Staff selezionaStaffPerId(Long id) {
        if (id == null) 
            return null;
        
        return em.find(Staff.class, id);
	}
	
	public Staff selezionaStaffPerTipo(TipoStaff tipoStaff) {
        if (tipoStaff == null) 
            return null;
       
       return (Staff) em.createNamedQuery("Staff.selezionaStaffPerTipo")
        	.setParameter("tipo_staff", tipoStaff).getSingleResult();
	}
	
    public boolean rimuoviStaff(Long id) {
        if (id != null) {
        	Staff staff = em.find(Staff.class, id);
            if (staff != null) {
                em.remove(staff);
                return true;
            }
        }
        
        return false;
    }
}
