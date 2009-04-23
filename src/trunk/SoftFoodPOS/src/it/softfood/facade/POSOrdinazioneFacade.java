package it.softfood.facade;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.User;
import it.softfood.entity.Variante;
import it.softfood.handler.OrdinazioneFacade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class POSOrdinazioneFacade {

	public ArrayList<LineaOrdinazione> selezionaOrdinazioniGiornaliere(User role) {
		OrdinazioneFacade ordinazionefacade=OrdinazioneFacade.getInstance();
		ArrayList<LineaOrdinazione> non_evasi=ordinazionefacade.selezionaOrdinazioniGiornaliere(role);
		return non_evasi;
	}

	public LineaOrdinazione selezionaLineaOrdinazionePerId(User role,Long id){
		OrdinazioneFacade ordinazionefacade=OrdinazioneFacade.getInstance();
		LineaOrdinazione linea=ordinazionefacade.selezionaLineaOrdinazionePerId(role, id);
		System.out.println("LINEA POS "+linea);
		return linea;
	}
	
	public ArrayList<Variante> selezionaVariantiPerLineaOrdinazione(User role,LineaOrdinazione lin){
		OrdinazioneFacade ordinazionefacade=OrdinazioneFacade.getInstance();
		ArrayList<Variante> linea=ordinazionefacade.selezionaVariantiPerLineaOrdinazione(role, lin);
		return linea;
	}
	
	public void setLineaEvasa(User role, LineaOrdinazione linea){
		OrdinazioneFacade ordinazionefacade=OrdinazioneFacade.getInstance();
		ordinazionefacade.setLineaEvasa(role, linea);
	}
}
