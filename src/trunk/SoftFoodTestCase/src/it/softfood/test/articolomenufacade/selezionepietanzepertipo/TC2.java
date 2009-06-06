package it.softfood.test.articolomenufacade.selezionepietanzepertipo;

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

public class TC2 extends TestCase {

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
		}
		
		user = userFacade.login(Ruolo.TEST, "test");
		
		}

	@After
	public void tearDown() throws Exception {
		userFacade.logout(user);
	}

	@Test
	public void testSelezionePietanze() throws RemoteException {
		
		User user_test = userFacade.login(Ruolo.CUOCO, "12345");
		
		
		ArrayList<Pietanza> pietanza = null;
		
		try{	
			pietanza = articoloFacade.selezionaPietanzePerTipo(user_test, TipoPietanza.ANTIPASTI);
		}catch(AccessControlException e){
			System.out.println(e);
			pietanza = null;
		}
		
		userFacade.logout(user_test);
		
		Assert.assertNull(pietanza);
	}

}
