package it.softfood.test.ordinazionefacade.selezioneordinazioni;

import it.softfood.entity.Ordinazione;
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
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TC1 extends TestCase {
	
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
	public void testSelezioniOrdinazioni() throws RemoteException {
		
		User user1 = userFacade.login(Ruolo.CUOCO, "4321");
	//	user1.setUserName("cuoco");
		
		ArrayList<Ordinazione> ordinazioneAttuale = null;
		try{	
			ordinazioneAttuale = ordinazioneFacade.selezionaOrdinazioni(user1);
			
		}catch(AccessControlException e){
			System.out.println(e);
			ordinazioneAttuale = null;
		}
	
		if(user1 != null)
			userFacade.logout(user1);
		
		Assert.assertNotNull(ordinazioneAttuale);
	}
}
