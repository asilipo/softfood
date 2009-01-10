package it.softfood.session.bevanda;

import it.softfood.entity.Bevanda;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface BevandaSessionBeanRemote {

	it.softfood.entity.Bevanda inserisciBevanda(it.softfood.entity.Bevanda bevanda);
	
	it.softfood.entity.Bevanda selezionaBevandaPerId(Long id);
	
	java.util.List<Bevanda> selezionaBevandePerNome(String nome);
	
	boolean rimuoviBevanda(Long id);
	
}
