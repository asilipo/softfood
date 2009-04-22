package it.softfood;

import java.util.ArrayList;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.User;
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
		
		User u=userFacade.login("cuoco", "1234");
		
		ArrayList<LineaOrdinazione> array=ordiniFacade.selezionaOrdinazioni(u);

		System.out.println("PIPPO "+array.size());
		System.out.println("PIPPO "+array);
		
		userFacade.logout(u);
	}

}
