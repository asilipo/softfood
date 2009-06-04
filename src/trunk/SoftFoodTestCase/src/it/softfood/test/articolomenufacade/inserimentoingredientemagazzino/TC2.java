package it.softfood.test.articolomenufacade.inserimentoingredientemagazzino;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.IngredienteMagazzino;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
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

public class TC2 extends TestCase {
	
	private IArticoloMenuFacade articoloFacade;
	private IUserFacade userFacade;
	private User user;
	private Ingrediente ingrediente;

	@Before
	public void setUp() throws Exception {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			articoloFacade = (IArticoloMenuFacade) registry.lookup("ArticoloFacade"); //CONTROLLARE
			//ristoranteFacade = (IRistoranteFacade) registry.lookup("RistoranteFacade");
			userFacade = (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
		
		user = userFacade.login(Ruolo.TEST, "test");//da togliere
		
		}

	@After
	public void tearDown() throws Exception {
		userFacade.logout(user); //da togliere
	}

	@Test
	public void testInserimentoIngredienteMagazzino() throws RemoteException {
		
		User user1 = userFacade.login(Ruolo.AMMINISTRATORE, "123456");
	//	user1.setUserName("cuoco");
		
		IngredienteMagazzino ingredienteAttuale = null;
		try{	
			ingredienteAttuale = articoloFacade.inserisciIngredienteMagazzino(user1, (long) 1000000L, 1000000);
		}catch(AccessControlException e){
			ingredienteAttuale = null;
		}
	
		if(user1 != null)
			userFacade.logout(user1);
		
		Assert.assertNull(ingredienteAttuale);
	}
}
