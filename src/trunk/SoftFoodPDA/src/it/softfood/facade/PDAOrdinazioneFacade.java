package it.softfood.facade;

import java.util.ArrayList;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.handler.OrdinazioneFacade;

public class PDAOrdinazioneFacade {

	public Ordinazione inserisciOrdinazione(Ordinazione ordine) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		Ordinazione ord=ordineFacade.inserisciOrdinazione(ordine);
		return ord;
	}

	public Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(String riferimentoTavolo,
			Boolean terminato) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		Ordinazione ord=ordineFacade.selezionaOrdinazioneGiornalieraPerTavolo(riferimentoTavolo, terminato);
		return ord;
	}

	public Ordinazione selezionaOrdinazionePerId(Long tavolo) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		Ordinazione ord=ordineFacade.selezionaOrdinazionePerId(tavolo);
		return ord;
	}

	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(
			Ordinazione selezionaOrdinazionePerId) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		ArrayList<LineaOrdinazione> ord=(ArrayList<LineaOrdinazione>) ordineFacade.selezionaLineeOrdinazionePerOrdinazione(selezionaOrdinazionePerId);
		return ord;
	}

	public void rimuoviOrdinazione(Long tavolo, Boolean b) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		boolean result=ordineFacade.rimuoviOrdinazione(tavolo, b);
		if(!result)
			new Exception();
		
	}

}
