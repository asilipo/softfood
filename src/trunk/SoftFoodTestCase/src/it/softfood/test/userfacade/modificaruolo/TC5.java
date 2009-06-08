package it.softfood.test.userfacade.modificaruolo;

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

public class TC5 extends TestCase {

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
		
		user = userFacade.login(Ruolo.AMMINISTRATORE, "123456");
		
		userInserito = new User();
		userInserito.setPassword("10000");
		userInserito.setRuolo(Ruolo.CUOCO.toString());
		userInserito.setUserName("Cameriere Test");
		userFacade.inserisciUtente(user, userInserito);
	}

	@After
	public void tearDown() throws Exception {
		userFacade.eliminaUtente(user, userInserito);
		userFacade.logout(user);
	}
	
	@Test
	public void testModificaRuolo() {
		Boolean valoreAttuale = false;
		try {
			valoreAttuale = userFacade.modificaRuolo(null, userInserito, Ruolo.CAMERIERE);
		} catch (RemoteException e) {
			valoreAttuale = false;
		} catch (NullPointerException npe) {
			valoreAttuale = false;
		}
		
		assertFalse(valoreAttuale);
	}

}
