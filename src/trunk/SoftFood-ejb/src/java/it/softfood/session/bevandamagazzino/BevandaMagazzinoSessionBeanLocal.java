package it.softfood.session.bevandamagazzino;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface BevandaMagazzinoSessionBeanLocal {

	it.softfood.entity.BevandaMagazzino inserisciBevandaMagazzino(it.softfood.entity.BevandaMagazzino bevandaMagazzino);
	
	it.softfood.entity.BevandaMagazzino selezionaBevandaMagazzinoPerId(Long id);
	
	java.util.List<it.softfood.entity.BevandaMagazzino> selezionaBevandeMagazzino();
	
	java.util.List<it.softfood.entity.BevandaMagazzino> selezionaBevandeMagazzinoPerQuantita(Integer quantita);
	
	boolean rimuoviBevandaMagazzino(Long id);
	
}
