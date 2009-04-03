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
        try {
			em.persist(staff);
            return staff;
        } catch (Exception e) {
            System.err.println("StaffSessionBean#inserisciStaff");
            return null;
        }
	}
	
	public Staff selezionaStaffPerId(Long id) {
        try {
            return em.find(Staff.class, id);
        } catch (Exception e) {
            System.err.println("StaffSessionBean#selezionaStaffPerId");
            return null;
        }
	}
	
	public Staff selezionaStaffPerTipo(TipoStaff tipoStaff) {
        try {
            return (Staff) em.createNamedQuery("Staff.selezionaStaffPerTipo")
                .setParameter("tipo_staff", tipoStaff).getSingleResult();
        } catch (Exception e) {
            System.err.println("StaffSessionBean#selezionaStaffPerTipo");
            return null;
        }
	}
	
    public boolean rimuoviStaff(Long id) {
        try {
        	Staff staff = em.find(Staff.class, id);
            if (staff != null) {
                em.remove(staff);
                return true;
            }

            return false;
        } catch (Exception e) {
            System.err.println("StaffSessionBean#rimuoviStaff");
            return false;
        }
    }
    
}
