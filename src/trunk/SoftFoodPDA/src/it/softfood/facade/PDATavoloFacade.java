package it.softfood.facade;

import it.softfood.entity.Tavolo;
import it.softfood.handler.ITavoloFacade;
import it.softfood.handler.TavoloFacade;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class PDATavoloFacade {
	
	
	public List<Tavolo> selezionaTavoliLiberi(){
		TavoloFacade tavolo = TavoloFacade.getInstance();
		System.out.println("GIUS");
		List<Tavolo> list = null;
		try {
			
			list = tavolo.selezionaTavoliLiberi();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
			System.out.println(tav.getClass());
			id = tavolo.occupaTavoli(tav);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
