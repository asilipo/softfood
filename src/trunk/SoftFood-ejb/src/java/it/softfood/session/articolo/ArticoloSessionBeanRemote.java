package it.softfood.session.articolo;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface ArticoloSessionBeanRemote {

    it.softfood.entity.Articolo inserisciArticolo(it.softfood.entity.Articolo articolo);

	it.softfood.entity.Articolo selezionaArticoloPerId(Long id);

	boolean rimuoviBevanda(Long id);

}
