package it.softfood.session.bevanda;

import it.softfood.entity.Bevanda;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface BevandaSessionBeanLocal {

	it.softfood.entity.Bevanda inserisciBevanda(it.softfood.entity.Bevanda bevanda);
	
	it.softfood.entity.Bevanda selezionaBevandaPerId(Long id);
	
	java.util.List<Bevanda> selezionaBevandePerNome(String nome);
	
	boolean rimuoviBevanda(Long id);
	
}
