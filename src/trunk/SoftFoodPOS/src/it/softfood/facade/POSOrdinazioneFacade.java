package it.softfood.facade;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.User;
import it.softfood.entity.Variante;
import it.softfood.handler.IOrdinazioneFacade;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class POSOrdinazioneFacade {
	
	private IOrdinazioneFacade ordinazionefacade;

	public ArrayList<LineaOrdinazione> selezionaOrdinazioniGiornaliere(User role) {
//		OrdinazioneFacade ordinazionefacade=OrdinazioneFacade.getInstance();
		ArrayList<LineaOrdinazione> non_evasi=null;;
		try {
			non_evasi = ordinazionefacade.selezionaOrdinazioniGiornaliere(role);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return non_evasi;
	}

	public LineaOrdinazione selezionaLineaOrdinazionePerId(User role,Long id){
//		OrdinazioneFacade ordinazionefacade=OrdinazioneFacade.getInstance();
		LineaOrdinazione linea=null;
		try {
			linea = ordinazionefacade.selezionaLineaOrdinazionePerId(role, id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("LINEA POS "+linea);
		return linea;
	}
	
	public ArrayList<Variante> selezionaVariantiPerLineaOrdinazione(User role,LineaOrdinazione lin){
//		OrdinazioneFacade ordinazionefacade=OrdinazioneFacade.getInstance();
		ArrayList<Variante> linea = null;
		try {
			linea = ordinazionefacade.selezionaVariantiPerLineaOrdinazione(role, lin);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return linea;
	}
	
	public void setLineaEvasa(User role, LineaOrdinazione linea){
//		OrdinazioneFacade ordinazionefacade=OrdinazioneFacade.getInstance();
		try {
			ordinazionefacade.setLineaEvasa(role, linea);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
