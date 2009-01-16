package it.softfood.session.staff;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface StaffSessionBeanLocal {
	
	it.softfood.entity.Staff inserisciStaff(it.softfood.entity.Staff staff);

	it.softfood.entity.Staff selezionaStaffPerId(Long id);
	
	it.softfood.entity.Staff selezionaStaffPerTipo(it.softfood.enumeration.TipoStaff tipoStaff);
	
	boolean rimuoviStaff(Long id);
	
}
