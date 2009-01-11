package it.softfood.session.registro;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface RegistroSessionBeanLocal {

	it.softfood.entity.Registro inserisciRegistro(it.softfood.entity.Registro registro);
	
	it.softfood.entity.Registro selezionaRegistroPerId(Long id);
	
	it.softfood.entity.Registro selezionaRegistroPerAnno(Integer anno);
	
	boolean rimuoviRegistro(Long id);
	
}
