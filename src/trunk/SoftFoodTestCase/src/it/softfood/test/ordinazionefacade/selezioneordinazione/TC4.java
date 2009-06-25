package it.softfood.test.ordinazionefacade.selezioneordinazione;

import it.softfood.entity.Ordinazione;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.handler.IOrdinazioneFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TC4 extends TestCase {
	
	private IOrdinazioneFacade ordinazioneFacade;
	private IUserFacade userFacade;
	private User user;	

	@Before
	public void setUp() throws Exception {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			ordinazioneFacade =(IOrdinazioneFacade) registry.lookup("OrdineFacade");	
			userFacade = (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
			fail ("Exception");
		}
		
		user = userFacade.login(Ruolo.TEST, "test");
	}

	@After
	public void tearDown() throws Exception {
		userFacade.logout(user);		
	}

	@Test
	public void testSelezioneOrdinazione() throws Exception {
		User user_test = null;
		try {
			user_test = userFacade.login(Ruolo.AMMINISTRATORE, "123456");
		} catch (RemoteException e) {
			fail ("RemoteException");
		}	
		
		Ordinazione ordinazione2 = null;
		try {
			ordinazione2 = ordinazioneFacade.selezionaOrdinazionePerId(user_test, null);
		} catch (Exception e) {
			ordinazione2 = null;
		} 
		
		try {
			userFacade.logout(user_test);
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		assertNull(ordinazione2);
	}
	
}
