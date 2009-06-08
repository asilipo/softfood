package it.softfood.test.userfacade.logout;

import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
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

public class TC2 extends TestCase {

	private IUserFacade userFacade;
	@SuppressWarnings("unused")
	private User user;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			userFacade = (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
		
		user = userFacade.login(Ruolo.AMMINISTRATORE, "123456");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogout() {
		boolean state = true;
		try {
			userFacade.logout(null);
		} catch (RemoteException e) {
			state = false;
		} catch (Exception e) {
			state = false;
		}
		
		assertTrue(state);
	}

}
