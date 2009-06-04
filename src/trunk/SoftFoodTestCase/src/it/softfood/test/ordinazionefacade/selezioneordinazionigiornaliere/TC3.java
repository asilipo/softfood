package it.softfood.test.ordinazionefacade.selezioneordinazionigiornaliere;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.handler.IOrdinazioneFacade;
import it.softfood.handler.ITavoloFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.AccessControlException;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TC3 {
	private IOrdinazioneFacade ordinazioneFacade;
	private IUserFacade userFacade;
	private User user;
	private ITavoloFacade tavoloFacade;
	private long id;

	@Before
	public void setUp() throws Exception {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			ordinazioneFacade = (IOrdinazioneFacade) registry.lookup("OrdineFacade"); //CONTROLLARE
			//ristoranteFacade = (IRistoranteFacade) registry.lookup("RistoranteFacade");
			tavoloFacade = (ITavoloFacade) registry.lookup("TavoloFacade");
			userFacade = (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
		
		user = userFacade.login(Ruolo.TEST, "test");
		
		
		}

	@After
	public void tearDown() throws Exception {

		userFacade.logout(user); //da togliere
	}

	@Test
	public void testSelezioniOrdinazioniGiornaliere() throws RemoteException {
		
		User user1 = userFacade.login(Ruolo.CAMERIERE, "1234");
	//	user1.setUserName("cuoco");
		
		ArrayList<LineaOrdinazione> ordinazioneAttuale = null;
		try{	
			ordinazioneAttuale = ordinazioneFacade.selezionaOrdinazioniGiornaliere(user1);
			
		}catch(AccessControlException e){
			ordinazioneAttuale = null;
		}
	
		if(user1 != null)
			userFacade.logout(user1);
		
		Assert.assertNull(ordinazioneAttuale);
	}
}