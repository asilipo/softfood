package it.softfood.facade;

import it.softfood.entity.Tavolo;
import it.softfood.handler.TavoloFacade;

import java.util.ArrayList;
import java.util.List;

public class PDATavoloFacade {
	
	public List<Tavolo> selezionaTavoliLiberi(){
		System.out.println("GIUS");
		TavoloFacade tavolo = TavoloFacade.getInstance();
		return tavolo.selezionaTavoliLiberi();
	}

	public List<Tavolo> selezionaTavoliOccupati() {
		TavoloFacade tavolo = TavoloFacade.getInstance();
		return tavolo.selezionaTavoliOccupati();
	}

}
