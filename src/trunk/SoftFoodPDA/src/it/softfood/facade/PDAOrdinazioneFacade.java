package it.softfood.facade;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.User;
import it.softfood.entity.Variante;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.handler.OrdinazioneFacade;

import java.util.ArrayList;

public class PDAOrdinazioneFacade {

	public Ordinazione inserisciOrdinazione(User role, Ordinazione ordine) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		Ordinazione ord = null;
		try {
			ord = ordineFacade.inserisciOrdinazione(role, ordine);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ord;
	}

	public Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(User role, String riferimentoTavolo, Boolean terminato) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		Ordinazione ord = null;
		try {
			ord = ordineFacade.selezionaOrdinazioneGiornalieraPerTavolo(role, riferimentoTavolo, terminato);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ord;
	}

	public Ordinazione selezionaOrdinazionePerId(User role,Long tavolo) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		Ordinazione ord = null;
		try {
			ord = ordineFacade.selezionaOrdinazionePerId(role,tavolo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ord;
	}

	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(User role, Ordinazione selezionaOrdinazionePerId) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		ArrayList<LineaOrdinazione> ord = null;
		try {
			ord = (ArrayList<LineaOrdinazione>) ordineFacade.selezionaLineeOrdinazionePerOrdinazione(role,selezionaOrdinazionePerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ord;
	}

	public void rimuoviOrdinazione(User role, Long tavolo, Boolean b) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		boolean result = false;
		try {
			result = ordineFacade.rimuoviOrdinazione(role,tavolo, b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!result)
			new Exception();
	}

	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazioneTipoPietanza(User role,Ordinazione ordine, TipoPietanza tipo_pietanza) {
		OrdinazioneFacade ordineFacade = OrdinazioneFacade.getInstance();
		ArrayList<LineaOrdinazione> linee = null;
	    
		try {
			linee = ordineFacade.selezionaLineeOrdinazionePerOrdinazioneTipoPietanza(role,ordine, tipo_pietanza);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return linee;
	}

	public void rimuoviLineaOrdinazione(User role,Long id) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		boolean result=false;
	
		try {
			result = ordineFacade.rimuoviLineaOrdinazione(role,id);
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
		
		return ord;
	}

	public ArrayList<Variante> selezionaVariantiPerLineaOrdinazione(User role, LineaOrdinazione lineaOrdinazione) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		ArrayList<Variante> linee=null;
	
		try {
			linee = ordineFacade.selezionaVariantiPerLineaOrdinazione(role,lineaOrdinazione);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return linee;
	}

	public ArrayList<Ingrediente> selezionaIngredientiPerVariante(User role) {
		OrdinazioneFacade ordineFacade = OrdinazioneFacade.getInstance();
		ArrayList<Ingrediente> ingrediente = null;
	
		try {
			ingrediente = ordineFacade.selezionaIngredientiPerVariante(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ingrediente;
	}

	public LineaOrdinazione inserisciLineaOrdinazione(User role, LineaOrdinazione linea) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		LineaOrdinazione lineaOrdinazione = null;
		try {
			lineaOrdinazione = ordineFacade.inserisciLineaOrdinazione(role, linea);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lineaOrdinazione;
	}

	public Ingrediente selezionaIngredientePerNome(User role,String nome) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		Ingrediente ingr = null;
		try {
			ingr = ordineFacade.selezionaIngredientePerNome(role,nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ingr;
	}

	public void inserisciVariante(User role,Variante variante) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		Variante v = ordineFacade.inserisciVariante(role,variante);
		if(v == null){
			new Exception();
		}
		
	}

}
