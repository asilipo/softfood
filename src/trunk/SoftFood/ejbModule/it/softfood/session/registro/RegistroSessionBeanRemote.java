package it.softfood.session.registro;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface RegistroSessionBeanRemote {

	it.softfood.entity.Registro inserisciRegistro(it.softfood.entity.Registro registro);
	
	it.softfood.entity.Registro selezionaRegistroPerId(Long id);
	
	it.softfood.entity.Registro selezionaRegistroPerAnno(Integer anno);
	
	boolean rimuoviRegistro(Long id);
	
}
