package it.softfood.facade.tavolo;

import java.util.List;

import it.softfood.entity.Tavolo;
import it.softfood.session.tavolo.TavoloSessionBeanLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class TavoloFacade implements TavoloFacadeRemote, TavoloFacadeLocal {

	@EJB(beanName = "TavoloSessionBean", mappedName = "it.sofdfood.session.tavolo.TavoloSessionBean")
	private TavoloSessionBeanLocal tavoloSessionBean;
	
	public Tavolo inserisciTavolo(Tavolo tavolo) {
		if (tavolo != null)
			return tavoloSessionBean.inserisciTavolo(tavolo);
		
		return null;
	}
	
	public boolean liberaTavolo(Tavolo tavolo) {
		if (tavolo != null)
			return tavoloSessionBean.modificaStatoTavolo(tavolo, false);
		
		return false;
	}
	
	public boolean occupaTavolo(Tavolo tavolo) {
		if (tavolo != null)
			return tavoloSessionBean.modificaStatoTavolo(tavolo, true);	
		
		return false;
	}
	
	public Tavolo selezionaTavolo(Long id) {
		if (id != null)
			return tavoloSessionBean.selezionaTavoloPerId(id);
		
		return null;
	}
	
	public List<Tavolo> selezionaTavoliLiberi() {
		return tavoloSessionBean.selezionaTavoliLiberi();
	}
	
	public boolean rimuoviTavolo(Long id) {
		if (id != null)
			return tavoloSessionBean.rimuoviTavolo(id);
		
		return false;
	}

}
