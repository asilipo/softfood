package it.softfood.test.articolomenufacade.selezioneingredienti;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.handler.IArticoloMenuFacade;
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

public class TC3 extends TestCase {
	
	private IArticoloMenuFacade articoloFacade;
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
			articoloFacade = (IArticoloMenuFacade) registry.lookup("ArticoloFacade"); //CONTROLLARE
			userFacade = (IUserFacade) registry.lookup("UserFacade");
		} catch (AccessControlException e) {
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
	public void testSelezionaIngredienti() {
		User user1 = null;
		try {
			user1 = userFacade.login(Ruolo.CASSIERE, "1234567");
		} catch (RemoteException e1) {
			fail ("RemoteException");
		}
		
		ArrayList<Ingrediente> listaIngrediente = null;
		try {			
			listaIngrediente = articoloFacade.selezionaIngredienti(user1);
		} catch(AccessControlException ace){
			listaIngrediente = null;
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		if(user1 != null) {
			try {
				userFacade.logout(user1);
			} catch (RemoteException e) {
				fail ("RemoteException");
			}
		}
		
		Assert.assertNull(listaIngrediente);
	}
	
}
