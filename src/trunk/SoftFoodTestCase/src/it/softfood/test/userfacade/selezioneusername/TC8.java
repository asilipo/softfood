package it.softfood.test.userfacade.selezioneusername;

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

public class TC8 extends TestCase {

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
			fail("Exception");
		}
		
		user = userFacade.login(Ruolo.AMMINISTRATORE, "123456");
		
		userInserito = new User();
		userInserito.setPassword("10000");
		userInserito.setRuolo(Ruolo.CAMERIERE.toString());
		userInserito.setUserName("Cameriere Test");
		userFacade.inserisciUtente(user, userInserito);
	}

	@After
	public void tearDown() throws Exception {
		userInserito.setPassword("");
		userFacade.eliminaUtente(user, userInserito);
		userFacade.logout(user);
	}
	
	@Test
	public void testSelezionaUserName() {
		User userAttuale = null;
		try {
			userInserito.setPassword("");
			userAttuale = userFacade.selezionaUserName(user, userInserito);
		} catch (RemoteException e) {
			fail("RemoteException");
		} catch (Exception e) {
			userAttuale = null;
		}
		
		assertNull(userAttuale);
	}
	
}
