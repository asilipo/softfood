package it.softfood.facade;

import it.softfood.entity.Tavolo;
import it.softfood.entity.User;
import it.softfood.handler.TavoloFacade;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class PDATavoloFacade {
	
	public List<Tavolo> selezionaTavoliLiberi(User role){
		TavoloFacade tavolo = TavoloFacade.getInstance();
		List<Tavolo> list = null;
		try {
			list = tavolo.selezionaTavoliLiberi(role);
		} catch (Exception e) {
			System.err.println("PDATavoloFAcade#selezionaTavoliLiberi");
		}
		
		return list;
	}

	public List<Tavolo> selezionaTavoliOccupati(User role) {
		TavoloFacade tavolo = TavoloFacade.getInstance();
		List<Tavolo> list = tavolo.selezionaTavoliOccupati(role);
		
		return list;
	}

	public Long occupaTavoli(User role, ArrayList<String> tav) {
		Long id = null;
		try {
			TavoloFacade tavolo = TavoloFacade.getInstance();
			id = tavolo.occupaTavoli(role,tav);
		} catch (Exception e) {
			System.err.println("PDATavoloFAcade#occupaTavoli");
		}
		
		return id;
	}

	public Tavolo selezionaTavolo(User role,Long tavoloSelezionato) {
		TavoloFacade tavolo = TavoloFacade.getInstance();
		Tavolo tav = tavolo.selezionaTavolo(role,tavoloSelezionato);
		return tav;
	}

}
