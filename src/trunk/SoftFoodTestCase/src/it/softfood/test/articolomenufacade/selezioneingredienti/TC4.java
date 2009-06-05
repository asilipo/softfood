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

public class TC4 extends TestCase {
	
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
		} catch (AccessControlException e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
		
		user = userFacade.login(Ruolo.TEST, "test");//da togliere
		
		}

	@After
	public void tearDown() throws Exception {
	
		userFacade.logout(user); //da togliere
	}

	@Test
	public void testSelezionaIngredienti() throws RemoteException {
		
		User user1 = userFacade.login(Ruolo.CASSIERE, "1234567");
		//user1.setUserName("cameriere 1");
		
		
		ArrayList<Ingrediente> listaIngrediente = null;
		try{			
			listaIngrediente = articoloFacade.selezionaIngredienti(user1);
		}catch(Exception e){
			listaIngrediente = null;
		}
		System.out.println(listaIngrediente);
		if(user1 != null)
			userFacade.logout(user1);
		//dovrebbe essere non nullo
		Assert.assertNull(listaIngrediente);
	}
}
