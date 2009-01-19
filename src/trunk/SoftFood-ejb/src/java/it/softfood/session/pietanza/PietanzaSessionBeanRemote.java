package it.softfood.session.pietanza;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface PietanzaSessionBeanRemote {

	it.softfood.entity.Pietanza inserisciPietanza(it.softfood.entity.Pietanza pietanza);
	
	it.softfood.entity.Pietanza selezionaPietanzaPerId(Long id);
	
	boolean rimuoviPietanza(Long id);

    @java.lang.SuppressWarnings(value = {"unchecked"})
    java.util.List<it.softfood.entity.Pietanza> selezionaPietanzePerTipo(it.softfood.enumeration.TipoPietanza tipoPietanza);

    @java.lang.SuppressWarnings(value = {"unchecked"})
    java.util.List<it.softfood.entity.Pietanza> selezionaPietanze();
    
}
