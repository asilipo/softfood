package it.softfood.test.articolomenufacade.rimozionepietanzamenu;

import it.softfood.entity.Pietanza;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.handler.IArticoloMenuFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.AccessControlException;

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

	private IArticoloMenuFacade articoloFacade;
	private IUserFacade userFacade;
	private User user;
	private Pietanza pietanza;

	@Before
	public void setUp() throws Exception {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			articoloFacade = (IArticoloMenuFacade) registry.lookup("ArticoloFacade");
			userFacade = (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
			fail ("Exception");
		}
		
		user = userFacade.login(Ruolo.TEST, "test");
		pietanza = new Pietanza();
		pietanza.setNome("PIETANZA TEST");
		pietanza.setTipoPietanza(TipoPietanza.PRIMO_PIATTO.ordinal());
		pietanza = articoloFacade.inserisciPietanzaMenu(user, pietanza);
	}

	@After
	public void tearDown() throws Exception {
		articoloFacade.rimuoviPietanzaMenu(user, pietanza.getId());
		userFacade.logout(user);
	}

	@Test
	public void testRimozionePietanzaMenu() {
		User user_test = null;
		try {
			user_test = userFacade.login(Ruolo.CUOCO, "12345");
		} catch (RemoteException e1) {
			fail ("RemoteException");
		}
		
		boolean rimozione = false;
		try {	
			rimozione = articoloFacade.rimuoviPietanzaMenu(user_test, pietanza.getId());
		} catch(AccessControlException e) {
			rimozione = false;
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		try {
			userFacade.logout(user_test);
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		Assert.assertTrue(rimozione);
	}

}
