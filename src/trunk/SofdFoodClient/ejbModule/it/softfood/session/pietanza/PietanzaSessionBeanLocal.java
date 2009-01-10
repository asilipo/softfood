package it.softfood.session.pietanza;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface PietanzaSessionBeanLocal {

	it.softfood.entity.Pietanza inserisciPietanza(it.softfood.entity.Pietanza pietanza);
	
	it.softfood.entity.Pietanza selezionaPietanzaPerId(Long id);
	
	java.util.List<	it.softfood.entity.Pietanza> selezionaPietanzePerNome(String nome);
	
	boolean rimuoviPietanza(Long id);
	
}