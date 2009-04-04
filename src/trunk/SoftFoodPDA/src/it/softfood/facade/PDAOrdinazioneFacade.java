package it.softfood.facade;

import java.rmi.RemoteException;
import java.util.ArrayList;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.Variante;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.handler.IOrdinazioneFacade;
import it.softfood.handler.OrdinazioneFacade;

import java.lang.Boolean;

public class PDAOrdinazioneFacade {

	public Ordinazione inserisciOrdinazione(Ordinazione ordine) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		Ordinazione ord = null;
		try {
			ord = ordineFacade.inserisciOrdinazione(ordine);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ord;
	}

	public Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(String riferimentoTavolo, Boolean terminato) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		Ordinazione ord = null;
		try {
			ord = ordineFacade.selezionaOrdinazioneGiornalieraPerTavolo(riferimentoTavolo, terminato);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ord;
	}

	public Ordinazione selezionaOrdinazionePerId(Long tavolo) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		Ordinazione ord = null;
		try {
			ord = ordineFacade.selezionaOrdinazionePerId(tavolo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ord;
	}

	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(
			Ordinazione selezionaOrdinazionePerId) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		ArrayList<LineaOrdinazione> ord = null;
		try {
			ord = (ArrayList<LineaOrdinazione>) ordineFacade.selezionaLineeOrdinazionePerOrdinazione(selezionaOrdinazionePerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ord;
	}

	public void rimuoviOrdinazione(Long tavolo, Boolean b) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		boolean result = false;
		try {
			result = ordineFacade.rimuoviOrdinazione(tavolo, b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!result)
			new Exception();
	}

	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(
			Ordinazione ordine, TipoPietanza tipo_pietanza) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		ArrayList<LineaOrdinazione> linee=null;
	
		try {
			linee = ordineFacade.selezionaLineeOrdinazionePerOrdinazione(ordine, tipo_pietanza);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return linee;
	}

	public void rimuoviLineaOrdinazione(Long id) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		boolean result=false;
	
		try {
			result = ordineFacade.rimuoviLineaOrdinazione(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!result)
			new Exception();
		
	}

	public LineaOrdinazione selezionaLineaOrdinazionePerId(Long id_linea) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		LineaOrdinazione ord = null;
		try {
			ord = ordineFacade.selezionaLineaOrdinazionePerId(id_linea);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ord;
	}

	public ArrayList<Variante> selezionaVariantiPerLineaOrdinazione(
			LineaOrdinazione lineaOrdinazione) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		ArrayList<Variante> linee=null;
	
		try {
			linee = ordineFacade.selezionaVariantiPerLineaOrdinazione(lineaOrdinazione);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return linee;
	}

}
