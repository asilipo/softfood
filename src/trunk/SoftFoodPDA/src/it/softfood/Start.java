package it.softfood;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.facade.PDAArticoloMenuFacade;
import it.softfood.facade.PDAOrdinazioneFacade;
import it.softfood.facade.PDATavoloFacade;
import it.softfood.facade.PDAUserFacade;
import it.softfood.handler.IUserFacade;
import it.softfood.handler.UserFacade;

public class Start {

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 */
	public static void main(String[] args) throws RemoteException, NotBoundException {
		PDATavoloFacade pda = new PDATavoloFacade();
        PDAOrdinazioneFacade pdaOrd=new PDAOrdinazioneFacade();
        PDAArticoloMenuFacade articolo = new PDAArticoloMenuFacade();
        PDAUserFacade user=new PDAUserFacade();
//      Tavolo tav=pda.selezionaTavolo(new Long(0));
//      Ordinazione ordine = pdaOrd.selezionaOrdinazioneGiornalieraPerTavolo(tav.getRiferimento(), new Boolean("false"));
//
//      System.out.println("TERMINATO OK! "+ordine.getId());

//      ArrayList<Pietanza> array= articolo.selezionaPietanzeDisponibiliPerTipo(TipoPietanza.DOLCE);

//      System.out.println(array.size());

        User u=user.login(Ruolo.CAMERIERE, "1234");

        pda.selezionaTavoliLiberi(u);
        System.out.println();

        user.logout(u);
	}

}
