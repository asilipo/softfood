package it.softfood.session;

import it.softfood.entity.Staff;
import it.softfood.enumeration.TipoStaff;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class StaffSession {

	private Session session;
	private static StaffSession staffSession;

	public synchronized static StaffSession getInstance() {
		if(staffSession == null)
			staffSession = new StaffSession();
		return staffSession;
	}

	private Long getNewId() {
		try {
			Query q = session.createQuery("select max(id) from it.softfood.entity.Staff");
		    return (((Long)q.list().get(0)) + 1);
		} catch(Exception e) {
			System.out.println("StaffSession#getNewId");
			return null;
		}		
	}

	public Staff inserisciStaff(Staff staff) {
		try {
			Long id = this.getNewId();
			staff.setId(id);
			session.persist(staff);
			
			return (Staff) session.get(Staff.class, staff);
		} catch (Exception e) {
			System.err.println("StaffSession#inserisciStaff");
			return null;
		}
	}
	
	public Staff selezionaStaffPerId(Long id) {
		try {
			return (Staff) session.get(Staff.class, id);
		} catch (Exception e) {
			System.err.println("StaffSession#selezionaStaffPerId");
			return null;
		}
	}
	
	public Staff selezionaStaffPerTipo(TipoStaff tipoStaff) {
		try {
			Query q = session.createQuery("from it.softfood.entity.Staff s where tipoStaff = ?");
			q.setString(0, tipoStaff.toString());
			Staff staff = (Staff) q.list().get(0);
			return staff;
		} catch (Exception e) {
			System.err.println("StaffSession#selezionaStaffPerTipo");
			return null;
		}
	}
	
    public boolean rimuoviStaff(Long id) {
    	try {
    		Staff staff = this.selezionaStaffPerId(id);
			if (staff != null) {
				session.delete(staff);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("StaffSession#rimuoviStaff");
			return false;
		}
    }
    
    public void flush() {
		this.session.flush();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
}
