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
import java.util.Date;

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
			articoloFacade = (IArticoloMenuFacade) registry.lookup("ArticoloFacade"); 
			userFacade = (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
		
		user = userFacade.login(Ruolo.TEST, "test");
		
		ingrediente=new Ingrediente();
		ingrediente.setId(1000000L);
		ingrediente.setNome("INGREDIENTE TEST");
		ingrediente.setScadenza(new Date(109,4,31));
		ingrediente.setVariante(true);
		
		ingrediente=articoloFacade.inserisciIngrediente(user, ingrediente);
		
		}

	@After
	public void tearDown() throws Exception {
		articoloFacade.rimuoviIngrediente(user, ingrediente.getId());
		userFacade.logout(user); 
	}

	@Test
	public void testInserimentoIngredienteMagazzino() throws RemoteException {
		
		User user_test = userFacade.login(Ruolo.AMMINISTRATORE, "123456");

		
		IngredienteMagazzino ingredienteAttuale = null;
		try{	
			ingredienteAttuale = articoloFacade.inserisciIngredienteMagazzino(user_test, 1000000L, 100);
		}catch(AccessControlException e){
			System.out.println(e);
			ingredienteAttuale = null;
		}
	
			userFacade.logout(user_test);
		
		Assert.assertNull(ingredienteAttuale);
	}
}
