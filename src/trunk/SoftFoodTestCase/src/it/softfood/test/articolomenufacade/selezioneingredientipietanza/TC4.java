package it.softfood.test.articolomenufacade.selezioneingredientipietanza;

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

public class TC4  extends TestCase {
	
	private IArticoloMenuFacade articoloFacade;
	private IUserFacade userFacade;
	private User user;
	private Ingrediente ingrediente;

	@SuppressWarnings("deprecation")
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
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
			fail ("Exception");
		}
		
		user = userFacade.login(Ruolo.TEST, "test");
		
		ingrediente = new Ingrediente();
		ingrediente.setDescrizione("Ingrediente di test");
		ingrediente.setId(1000000L);
		ingrediente.setNome("Ingrediente di Test");
		ingrediente.setTipoIngrediente("IngredienteLungaConservazione");
		ingrediente.setScadenza(new Date(109,8,26));
		ingrediente.setUnitaMisura("g");
		ingrediente.setVariante(true);

		ingrediente = articoloFacade.inserisciIngrediente(user, ingrediente);
	}

	@After
	public void tearDown() throws Exception {
		articoloFacade.rimuoviIngrediente(user, ingrediente.getId());
		userFacade.logout(user);
	}

	@Test
	public void testSelezionaIngredientePietanza() {
		User user1 = null;
		try {
			user1 = userFacade.login(Ruolo.CAMERIERE, "1234");
		} catch (RemoteException e1) {
			fail ("RemoteException");
		}
		
		ArrayList<Ingrediente> ingredienti = null;
		try{			
			ingredienti = articoloFacade.selezionaIngredientiPietanza(user1, null);
		} catch(AccessControlException ace){
			ingredienti = null;
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		try {
			userFacade.logout(user1);
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		Assert.assertNull(ingredienti);
	}
	
}
