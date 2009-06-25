package it.softfood.test.articolomenufacade.inserimentobevandamagazzino;

import it.softfood.entity.Bevanda;
import it.softfood.entity.BevandaMagazzino;
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

public class TC3 extends TestCase {
	
	private IArticoloMenuFacade articoloFacade;
	private IUserFacade userFacade;
	private User user;
	private BevandaMagazzino bevandaMagazzino;
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
			fail("Exception");
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
		articoloFacade.rimuoviBevandaMagazzino(user, 1000000L);
		userFacade.logout(user);
	}

	@Test
	public void testInserimentoBevandaMagazzino() {
		User user_test = null;
		try {
			user_test = userFacade.login(Ruolo.CAMERIERE, "1234");
		} catch (RemoteException e1) {
			fail("RemoteException");
		}

		try {	
			bevandaMagazzino = articoloFacade.inserisciBevandaMagazzino(user_test, bevanda, 1000000L, 100);
		} catch(AccessControlException e){
			bevandaMagazzino = null;
		} catch (RemoteException e) {
			fail("RemoteException");
		}
		
		try {
			userFacade.logout(user_test);
		} catch (RemoteException e) {
			fail("RemoteException");
		}
		
		Assert.assertNull(bevandaMagazzino);
	}
	
}
