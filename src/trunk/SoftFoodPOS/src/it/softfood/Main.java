package it.softfood;

import it.softfood.facade.POSArticoloMenuFacade;
import it.softfood.facade.POSOrdinazioneFacade;
import it.softfood.facade.POSUserFacade;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		POSUserFacade userFacade=new POSUserFacade();
		POSOrdinazioneFacade ordiniFacade=new POSOrdinazioneFacade();
		POSArticoloMenuFacade articoloFacade=new POSArticoloMenuFacade();
		
//		User u=userFacade.login("cuoco", "1234");
		
//		ArrayList<LineaOrdinazione> array=ordiniFacade.selezionaOrdinazioniGiornaliere(u);
//		
//		Long id=array.get(0).getId();
//		
//		LineaOrdinazione linea=ordiniFacade.selezionaLineaOrdinazionePerId(u, id);
//
//		System.out.println("PIPPO "+array.size());
//		System.out.println("PIPPO "+array);
//		System.out.println("LINEA "+linea);
		
//		ArrayList<Bevanda> bevande=articoloFacade.selezionaBevande(u);
//		Bevanda bevanda=bevande.get(0);
//		
//		articoloFacade.updateBevanda(u, bevanda);
////		
//		
//		
//		userFacade.logout(u);
	}

}
