package it.softfood.facade;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.User;
import it.softfood.entity.Variante;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.exception.AggiornamentoIngredientiMagazzinoException;
import it.softfood.exception.DisponibilitaBevandaException;
import it.softfood.exception.DisponibilitaPietanzaException;
import it.softfood.exception.UserException;
import it.softfood.handler.OrdinazioneFacade;

import java.util.ArrayList;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class PDAOrdinazioneFacade {

	public Ordinazione inserisciOrdinazione(User role, Ordinazione ordine) throws Exception {
		OrdinazioneFacade ordineFacade = OrdinazioneFacade.getInstance();
		Ordinazione ord = null;
		ord = ordineFacade.inserisciOrdinazione(role, ordine);

		return ord;
	}

	public Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(User user, String riferimentoTavolo, Boolean terminato) {
		OrdinazioneFacade ordineFacade = OrdinazioneFacade.getInstance();
		Ordinazione ordinazione = null;		
		try {
			ordinazione = ordineFacade.selezionaOrdinazioneGiornalieraPerTavolo(user, riferimentoTavolo, terminato);
		} catch (Exception e) {
			System.err.println("PDATavoloFacade#selezionaOrdinazioneGiornalieraPerTavolo");
		}
		
		return ordinazione;
	}
	
	public Ordinazione selezionaOrdinazionePerTavolo(User user, String riferimentoTavolo, Boolean terminato) {
		OrdinazioneFacade ordineFacade = OrdinazioneFacade.getInstance();
		Ordinazione ordinazione = null;
		try {
			ordinazione = ordineFacade.selezionaOrdinazionePerTavolo(user, riferimentoTavolo, terminato);
		} catch (Exception e) {
			System.err.println("PDATavoloFacade#selezionaOrdinazionePerTavolo");
		}
		
		return ordinazione;
	}

	public Ordinazione selezionaOrdinazionePerId(User role,Long tavolo) {
		OrdinazioneFacade ordineFacade = OrdinazioneFacade.getInstance();
		Ordinazione ord = null;
		try {
			ord = ordineFacade.selezionaOrdinazionePerId(role,tavolo);
		} catch (Exception e) {
			System.err.println("PDATavoloFacade#selezionaOrdinazionePerId");
		}
		
		return ord;
	}

	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(User role, Ordinazione selezionaOrdinazionePerId) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		ArrayList<LineaOrdinazione> ord = null;
		try {
			ord = (ArrayList<LineaOrdinazione>) ordineFacade.selezionaLineeOrdinazionePerOrdinazione(role, selezionaOrdinazionePerId);
		} catch (Exception e) {
			System.err.println("PDATavoloFacade#selezionaLineeOrdinazionePerOrdinazione");
		}
		
		return ord;
	}

	public void rimuoviOrdinazione(User role, Long tavolo, Boolean b) {
		OrdinazioneFacade ordineFacade = OrdinazioneFacade.getInstance();
		boolean result = false;
		try {
			result = ordineFacade.rimuoviOrdinazione(role,tavolo, b);
		} catch (Exception e) {
			System.err.println("PDATavoloFacade#rimuoviOrdinazione");
		}
		if(!result)
			new Exception();
	}

	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazioneTipoPietanza(User role, Ordinazione ordine, TipoPietanza tipo_pietanza) {
		OrdinazioneFacade ordineFacade = OrdinazioneFacade.getInstance();
		ArrayList<LineaOrdinazione> linee = null;
	    
		try {
			linee = ordineFacade.selezionaLineeOrdinazionePerOrdinazioneTipoPietanza(role, ordine, tipo_pietanza);
		} catch (Exception e) {
			System.err.println("PDATavoloFacade#selezionaLineeOrdinazionePerOrdinazioneTipoPietanza");
		}
		
		return linee;
	}

	public void rimuoviLineaOrdinazione(User role, Long id) {
		OrdinazioneFacade ordineFacade = OrdinazioneFacade.getInstance();
		boolean result=false;
	
		try {
			result = ordineFacade.rimuoviLineaOrdinazione(role,id);
		} catch (Exception e) {
			System.err.println("PDATavoloFacade#rimuoviLineaOrdinazione");
		}
		if(!result)
			new Exception();
	}

	public LineaOrdinazione selezionaLineaOrdinazionePerId(User role,Long id_linea) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		LineaOrdinazione ord = null;
		try {
			ord = ordineFacade.selezionaLineaOrdinazionePerId(role,id_linea);
		} catch (Exception e) {
			System.err.println("PDATavoloFacade#selezionaLineaOrdinazionePerId");
		}
		
		return ord;
	}

	public ArrayList<Variante> selezionaVariantiPerLineaOrdinazione(User role, LineaOrdinazione lineaOrdinazione) {
		OrdinazioneFacade ordineFacade = OrdinazioneFacade.getInstance();
		ArrayList<Variante> linee = null;
	
		try {
			linee = ordineFacade.selezionaVariantiPerLineaOrdinazione(role, lineaOrdinazione);
		} catch (Exception e) {
			System.err.println("PDATavoloFacade#selezionaVariantiPerLineaOrdinazione");
		}
		
		return linee;
	}

	public ArrayList<Ingrediente> selezionaIngredientiPerVariante(User role) {
		OrdinazioneFacade ordineFacade = OrdinazioneFacade.getInstance();
		ArrayList<Ingrediente> ingrediente = null;
	
		try {
			ingrediente = ordineFacade.selezionaIngredientiPerVariante(role);
		} catch (Exception e) {
			System.err.println("PDATavoloFacade#selezionaIngredientiPerVariante");
		}
		
		return ingrediente;
	}

	public LineaOrdinazione inserisciLineaOrdinazione(User role, LineaOrdinazione linea) throws DisponibilitaBevandaException, DisponibilitaPietanzaException, AggiornamentoIngredientiMagazzinoException, UserException {
		OrdinazioneFacade ordineFacade = OrdinazioneFacade.getInstance();
		LineaOrdinazione lineaOrdinazione = null;
		lineaOrdinazione = ordineFacade.inserisciLineaOrdinazione(role, linea);
		
		return lineaOrdinazione;
	}

	public Ingrediente selezionaIngredientePerNome(User role, String nome) {
		OrdinazioneFacade ordineFacade = OrdinazioneFacade.getInstance();
		Ingrediente ingr = null;
		try {
			ingr = ordineFacade.selezionaIngredientePerNome(role,nome);
		} catch (Exception e) {
			System.err.println("PDATavoloFacade#selezionaIngredientePerNome");
		}
		
		return ingr;
	}

	public void inserisciVariante(User role, Variante variante) {
		OrdinazioneFacade ordineFacade = OrdinazioneFacade.getInstance();
		Variante v = ordineFacade.inserisciVariante(role,variante);
		if(v == null){
			new Exception();
		}	
	}

}
