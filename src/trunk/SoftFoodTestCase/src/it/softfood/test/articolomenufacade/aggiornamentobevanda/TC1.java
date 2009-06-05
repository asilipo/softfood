package it.softfood.test.articolomenufacade.aggiornamentobevanda;

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
	

	@Before
	public void setUp() throws Exception {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			articoloFacade = (IArticoloMenuFacade) registry.lookup("ArticoloMenuFacade");
			userFacade = (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
		
		Bevanda bevanda=new Bevanda();
	}

	@After
	public void tearDown() throws Exception {
		
		
	}

	@Test
	public void testUpdateBevanda() {
		fail("Not yet implemented");
	}

}
