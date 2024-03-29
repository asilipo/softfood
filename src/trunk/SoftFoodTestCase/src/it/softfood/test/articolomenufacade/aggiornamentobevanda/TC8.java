package it.softfood.test.articolomenufacade.aggiornamentobevanda;

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

public class TC8 extends TestCase {

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

		bevanda = new Bevanda();
		bevanda.setCapacita(1000F);
		bevanda.setNome("BEVNDA TEST");
		bevanda.setTipoArticolo("BEVANDA");
		bevanda.setTipoPietanza(TipoPietanza.BEVANDA.ordinal());
		
		bevanda = articoloFacade.inserisciBevandaMenu(user, bevanda);
	}

	@After
	public void tearDown() throws Exception {
		articoloFacade.rimuoviBevandaMenu(user, bevanda.getId());
		userFacade.logout(user);
	}

	@Test
	public void testUpdateBevanda() {
		User user_test=null;
		try {
			user_test = userFacade.login(Ruolo.CUOCO, "12345");
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		Bevanda bevanda_test = bevanda;
		bevanda_test.setCapacita(null);
		bevanda_test.setNome("BEVANDA TEST");
		bevanda_test.setTipoArticolo("BEVANDA");
		bevanda_test.setTipoPietanza(TipoPietanza.BEVANDA.ordinal());
		boolean test_return = false;
		try {
			test_return=articoloFacade.updateBevanda(user_test, bevanda_test);
		} catch (AccessControlException e) {
			test_return = false;
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		try {
			userFacade.logout(user_test);
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		assertFalse(test_return);
	}
	
}
