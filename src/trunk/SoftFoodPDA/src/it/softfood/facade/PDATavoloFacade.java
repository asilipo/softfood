package it.softfood.facade;

import it.softfood.entity.Tavolo;
import it.softfood.handler.ITavoloFacade;
import it.softfood.handler.TavoloFacade;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class PDATavoloFacade {
	
	public List<Tavolo> selezionaTavoliLiberi(){
		System.out.println("GIUS");
		ITavoloFacade tavolo = TavoloFacade.getInstance();
		try {
			return tavolo.selezionaTavoliLiberi();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Tavolo> selezionaTavoliOccupati() {
		ITavoloFacade tavolo = TavoloFacade.getInstance();
		try {
			return tavolo.selezionaTavoliOccupati();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Long occupaTavoli(List<String> tav) {
		ITavoloFacade tavolo = TavoloFacade.getInstance();
		try {
			return tavolo.occupaTavoli(tav);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Tavolo selezionaTavolo(Long tavoloSelezionato) {
		ITavoloFacade tavolo = TavoloFacade.getInstance();
		try {
			return tavolo.selezionaTavolo(tavoloSelezionato);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
