package it.softfood.facade;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.User;
import it.softfood.entity.Variante;
import it.softfood.handler.IOrdinazioneFacade;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class POSOrdinazioneFacade {
	
	private IOrdinazioneFacade ordinazionefacade;

	public ArrayList<LineaOrdinazione> selezionaOrdinazioniGiornaliereNoData(User role) {
		ArrayList<LineaOrdinazione> non_evasi=null;;
		try {
			non_evasi = ordinazionefacade.selezionaOrdinazioniGiornaliere(role);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#selezionaOrdinazioniGiornaliereNoData");
		}
		
		return non_evasi;
	}

	public LineaOrdinazione selezionaLineaOrdinazionePerId(User role,Long id){
		LineaOrdinazione linea = null;
		try {
			linea = ordinazionefacade.selezionaLineaOrdinazionePerId(role, id);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#selezionaLineaOrdinazionePerId");
		}

		return linea;
	}
	
	public ArrayList<Variante> selezionaVariantiPerLineaOrdinazione(User role,LineaOrdinazione lin){
		ArrayList<Variante> linea = null;
		try {
			linea = ordinazionefacade.selezionaVariantiPerLineaOrdinazione(role, lin);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#selezionaVariantiPerLineaOrdinazione");
		}
		
		return linea;
	}
	
	public void setLineaEvasa(User role, LineaOrdinazione linea){
		try {
			ordinazionefacade.setLineaEvasa(role, linea);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#setLineaEvasa");
		}
	}
	
}
