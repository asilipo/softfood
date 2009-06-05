package it.softfood.test.articolomenufacade.selezionebevande;

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
import java.util.ArrayList;

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
		}
		
		user=userFacade.login(Ruolo.TEST, "test");
		
		
	}

	@After
	public void tearDown() throws Exception {
		userFacade.logout(user);		
	}

	@Test
	public void testSelezioneBevande() {
		User user_test = null;
		try {
			user_test = userFacade.login(Ruolo.CAMERIERE, "1234");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			user_test=null;
		}
		
		
		
		ArrayList<Bevanda> bevanda = null;
		
		try {
			bevanda = articoloFacade.selezionaBevande(user_test);
		} catch (AccessControlException e) {
			System.out.println(e);
			bevanda = null;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			userFacade.logout(user_test);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(bevanda);
	}

}
