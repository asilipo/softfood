package it.softfood.test.articolomenufacade.aggiornamentoingrediente;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.handler.IArticoloMenuFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.AccessControlException;
import java.util.Date;

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

	private IUserFacade userFacade;
	private IArticoloMenuFacade articoloFacade;
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
		
		user=userFacade.login(Ruolo.TEST, "test");
		
		ingrediente=new Ingrediente();
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
	public void testUpdateIngrediente() {
		User user_test=null;
		try {
			user_test=userFacade.login(Ruolo.CAMERIERE, "1234");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			user_test=null;
		}
		
		Ingrediente ingrediente_test=ingrediente;
		ingrediente_test.setNome("INGREDIENTE TEST");
		ingrediente_test.setScadenza(new Date(109,4,31));
		ingrediente_test.setVariante(true);
		boolean test_return = false;
		try {
			test_return=articoloFacade.updateIngrediente(user_test, ingrediente_test);
		} catch (AccessControlException e) {
			test_return = false;
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
		
		assertFalse(test_return);
	}
}
