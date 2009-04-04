package it.softfood.facade;

import java.rmi.RemoteException;
import java.util.ArrayList;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.handler.IOrdinazioneFacade;
import it.softfood.handler.OrdinazioneFacade;

import java.lang.Boolean;

public class PDAOrdinazioneFacade {

	public Ordinazione inserisciOrdinazione(Ordinazione ordine) {
		IOrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		Ordinazione ord = null;
		try {
			ord = ordineFacade.inserisciOrdinazione(ordine);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ord;
	}

	public Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(String riferimentoTavolo,
			Boolean terminato) {
		IOrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		Ordinazione ord = null;
		try {
			ord = ordineFacade.selezionaOrdinazioneGiornalieraPerTavolo(riferimentoTavolo, terminato);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ord;
	}

	public Ordinazione selezionaOrdinazionePerId(Long tavolo) {
		IOrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		Ordinazione ord = null;
		try {
			ord = ordineFacade.selezionaOrdinazionePerId(tavolo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ord;
	}

	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(
			Ordinazione selezionaOrdinazionePerId) {
		IOrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		ArrayList<LineaOrdinazione> ord = null;
		try {
			ord = (ArrayList<LineaOrdinazione>) ordineFacade.selezionaLineeOrdinazionePerOrdinazione(selezionaOrdinazionePerId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ord;
	}

	public void rimuoviOrdinazione(Long tavolo, Boolean b) {
		IOrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		boolean result = false;
		try {
			result = ordineFacade.rimuoviOrdinazione(tavolo, b);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!result)
			new Exception();
		
	}

}
