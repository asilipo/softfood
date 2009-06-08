package it.softfood.test.userfacade.inserimentoutente;

import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.handler.IUserFacade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.AccessControlException;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TC3 extends TestCase {

	private IUserFacade userFacade;
	private User user;
	private User userInserito;
	
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
		
		user = userFacade.login(Ruolo.CAMERIERE, "1234");
	}

	@After
	public void tearDown() throws Exception {
		userFacade.logout(user);
	}

	@Test
	public void testInserisciUtente() {
		userInserito = new User();
		userInserito.setPassword("10000");
		userInserito.setRuolo(Ruolo.CAMERIERE.toString());
		userInserito.setUserName("Cameriere Test");
		User userAttuale = null;
		try {
			userAttuale = userFacade.inserisciUtente(user, userInserito);
		} catch (RemoteException e) {
			userAttuale = null;
		} catch (AccessControlException ace) {
			userAttuale = null;
		} catch (NullPointerException npe) {
			userAttuale = null;
		}
		
		assertNull(userAttuale);
	}
}
