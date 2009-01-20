package it.softfood.facade.tavolo;

import it.softfood.entity.Tavolo;
import it.softfood.session.tavolo.TavoloSessionBeanRemote;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class TavoloFacade implements TavoloFacadeRemote, TavoloFacadeLocal {

	@EJB(beanName = "TavoloSessionBean")
	private TavoloSessionBeanRemote tavoloSessionBean;
	
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

    public List<Tavolo> selezionaTavoliOccupati() {
		return tavoloSessionBean.selezionaTavoliOccupati();
	}

    public List<Tavolo> selezionaTavoliNonAttivi() {
        return tavoloSessionBean.selezionaTavoliNonAttivi();
    }
	
	public boolean rimuoviTavolo(Long id) {
		if (id != null)
			return tavoloSessionBean.rimuoviTavolo(id);
		
		return false;
	}

}
