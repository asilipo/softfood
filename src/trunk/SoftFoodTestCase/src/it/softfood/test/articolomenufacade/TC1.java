package it.softfood.test.articolomenufacade;

import it.softfood.entity.Pietanza;
import it.softfood.entity.User;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.handler.IArticoloMenuFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TC1 {

	private IArticoloMenuFacade articoloMenuFacade;
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
			articoloMenuFacade = (IArticoloMenuFacade) registry.lookup("ArticoloFacade");
			userFacade = (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
		
		user = userFacade.login("test", "test");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInserisciPietanzaMenu() {
		Pietanza pietanza = new Pietanza();
		pietanza.setId(1000000L);
		pietanza.setNome("pietanza_test");
		pietanza.setTipo(TipoPietanza.ANTIPASTI);
		pietanza.setTipoPietanza(TipoPietanza.ANTIPASTI.ordinal());
		pietanza.setTipoArticolo("Pietanza");
		
		try {
			articoloMenuFacade.inserisciPietanzaMenu(user, pietanza);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*@Test
	public void testInserisciBevandaMenu() {
		fail("Not yet implemented");
	}

	@Test
	public void testInserisciIngrediente() {
		fail("Not yet implemented");
	}

	@Test
	public void testInserisciIngredientiPietanze() {
		fail("Not yet implemented");
	}

	@Test
	public void testInserisciBevandaMagazzino() {
		fail("Not yet implemented");
	}

	@Test
	public void testInserisciIngredienteMagazzino() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaArticoloMenuPerId() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaIngredientePerNome() {
		fail("Not yet implemented");
	}

	@Test
	public void testInserisciIngredientePietanza() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaPietanzePerTipo() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaPietanze() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaPietanzeDisponibili() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaPietanzeDisponibiliPerTipo() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaDisponibilitaPietanze() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaDisponibilitaPietanza() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaDisponibilitaBevanda() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaDisponibilitaPietanzePerTipo() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerificaIngredientiPietanza() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaBevande() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateIndredientiPietanza() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBevanda() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePietanza() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateIngrediente() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateIngredienteMagazzino() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateIngredientePietanza() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBevandaMagazzino() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaBevandeDisponibili() {
		fail("Not yet implemented");
	}

	@Test
	public void testRimuoviPietanzaMenu() {
		fail("Not yet implemented");
	}

	@Test
	public void testRimuoviIngrediente() {
		fail("Not yet implemented");
	}

	@Test
	public void testRimuoviBevandaMenu() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaIngredientiPietanza() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaIngredienti() {
		fail("Not yet implemented");
	}
*/
}
