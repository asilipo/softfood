package it.softfood.test.articolomenufacade.inserimentoingredientipietanza;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.IngredientePietanza;
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
import java.sql.Date;
import java.util.HashSet;

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

	private IUserFacade userFacade;
	private IArticoloMenuFacade articoloFacade;
	private User user;
	private IngredientePietanza ingredientePietanza=null;
	private Ingrediente ingrediente = new Ingrediente();
	private Pietanza pietanza = new Pietanza();

	@SuppressWarnings("deprecation")
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
			fail ("Exception");
		}
		
		user = userFacade.login(Ruolo.TEST, "test");
		
		ingrediente.setNome("Test Ingrediente");		
		ingrediente.setScadenza(new Date(109,4,31));
		ingrediente.setVariante(true);		
		
		ingrediente = articoloFacade.inserisciIngrediente(user, ingrediente);
		
		pietanza.setNome("Pietanza Test");
		pietanza.setTipoPietanza(TipoPietanza.PRIMO_PIATTO.ordinal());
	
		pietanza = articoloFacade.inserisciPietanzaMenu(user, pietanza);
	}

	@After
	public void tearDown() throws Exception {
		articoloFacade.rimuoviIngrediente(user, ingrediente.getId());
		articoloFacade.rimuoviPietanzaMenu(user, pietanza.getId());
		userFacade.logout(user);
	}

	@Test
	public void testInserimentoIngredientiPietanza() {
		User user_test = null;
		try {
			user_test = userFacade.login(Ruolo.CASSIERE, "1234567");
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		HashSet<IngredientePietanza> ingredienti=new HashSet<IngredientePietanza>();
		ingredientePietanza = new IngredientePietanza();
		ingredientePietanza.setQuantita(5);
		ingredientePietanza.setIngrediente(ingrediente);
		ingredientePietanza.setArticolo(pietanza);
		ingredienti.add(ingredientePietanza);
		
		try {
			ingredienti = articoloFacade.inserisciIngredientiPietanze(user_test, ingredienti);
		} catch (AccessControlException e) {
			ingredienti = null;
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		try {
			userFacade.logout(user_test);
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		assertNull(ingredienti);
	}

}
