package it.softfood.session.bevandamagazzino;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface BevandaMagazzinoSessionBeanRemote {

	it.softfood.entity.BevandaMagazzino inserisciBevandaMagazzino(it.softfood.entity.BevandaMagazzino bevandaMagazzino);
	
	it.softfood.entity.BevandaMagazzino selezionaBevandaMagazzinoPerId(Long id);
	
	java.util.List<it.softfood.entity.BevandaMagazzino> selezionaBevandeMagazzino();
	
	java.util.List<it.softfood.entity.BevandaMagazzino> selezionaBevandeMagazzinoPerQuantita(Integer quantita);
	
	boolean rimuoviBevandaMagazzino(Long id);
	
}
