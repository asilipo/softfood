package it.softfood.facade;

import it.softfood.entity.Tavolo;
import it.softfood.handler.TavoloFacade;

import java.util.ArrayList;
import java.util.List;

public class PDATavoloFacade {
	
	public List<Tavolo> selezionaTavoliLiberi(){
		TavoloFacade tavolo = TavoloFacade.getInstance();
		List<Tavolo> list = null;
		try {
			list = tavolo.selezionaTavoliLiberi();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Tavolo> selezionaTavoliOccupati() {
		TavoloFacade tavolo = TavoloFacade.getInstance();
		List<Tavolo> list=tavolo.selezionaTavoliOccupati();
		
		return list;
	}

	public Long occupaTavoli(ArrayList<String> tav) {
		Long id = null;
		try {
			TavoloFacade tavolo = TavoloFacade.getInstance();
			id = tavolo.occupaTavoli(tav);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public Tavolo selezionaTavolo(Long tavoloSelezionato) {
		TavoloFacade tavolo = TavoloFacade.getInstance();
		Tavolo tav=tavolo.selezionaTavolo(tavoloSelezionato);
		return tav;
	}

}
