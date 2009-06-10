package it.softfood.test.articolomenufacade.inserimentobevandamenu;

import it.softfood.entity.Bevanda;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.handler.IArticoloMenuFacade;
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

public class TC9 extends TestCase {

	private IUserFacade userFacade;
	private IArticoloMenuFacade articoloFacade;
	private User user;
	private Bevanda bevanda;
	
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
	}

	@After
	public void tearDown() throws Exception {
		userFacade.logout(user);		
	}

	@Test
	public void testInserimentoBevandaMenu() {
		User user_test = null;
		try {
			user_test = userFacade.login(Ruolo.CUOCO, "12345");
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		bevanda = new Bevanda();
		bevanda.setCapacita(-1000F);
		bevanda.setNome("BEVANDA TEST");
		bevanda.setTipoArticolo("BEVANDA");
		bevanda.setTipoPietanza(TipoPietanza.BEVANDA.ordinal());
		
		try {
			bevanda = articoloFacade.inserisciBevandaMenu(user_test, bevanda);
		} catch (AccessControlException e) {
			bevanda = null;
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		try {
			userFacade.logout(user_test);
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		assertNull(bevanda);
	}
}
