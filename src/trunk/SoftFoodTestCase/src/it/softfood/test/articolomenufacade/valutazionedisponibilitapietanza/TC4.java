package it.softfood.test.articolomenufacade.valutazionedisponibilitapietanza;

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

public class TC4 extends TestCase {

	private IUserFacade userFacade;
	private IArticoloMenuFacade articoloFacade;
	private User user;
	private Pietanza pietanza;
	private Ingrediente ingrediente;
	private IngredientePietanza ingrediente_pietanza;
	
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
		
		pietanza = new Pietanza();
		pietanza.setNome("BEVANDA TEST");
		pietanza.setTipoPietanza(TipoPietanza.PRIMO_PIATTO.ordinal());
		
		pietanza = articoloFacade.inserisciPietanzaMenu(user, pietanza);
		
		ingrediente = new Ingrediente();
		ingrediente.setNome("Ingrediente test");
		ingrediente.setScadenza(new Date(109,10,30));
		
		ingrediente = articoloFacade.inserisciIngrediente(user, ingrediente);
		
		ingrediente_pietanza = new IngredientePietanza();
		ingrediente_pietanza.setArticolo(pietanza);
		ingrediente_pietanza.setIngrediente(ingrediente);
		ingrediente_pietanza.setQuantita(5);
		
		ingrediente_pietanza = articoloFacade.inserisciIngredientePietanza(user, ingrediente_pietanza);
		
		articoloFacade.inserisciIngredienteMagazzino(user, ingrediente.getId(), 10);	
	}

	@After
	public void tearDown() throws Exception {
		articoloFacade.rimuoviIngrediente(user, ingrediente.getId());
		articoloFacade.rimuoviPietanzaMenu(user, pietanza.getId());
		userFacade.logout(user);
	}

	@Test
	public void testValutazioneDisponibilitaPietanza() {
		User user_test = null;
		try {
			user_test=userFacade.login(Ruolo.CAMERIERE, "1234");
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		int test_return = 0;
		try {
			test_return = articoloFacade.selezionaDisponibilitaPietanza(user_test, null);
		} catch (AccessControlException e) {
			test_return = 0;
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		try {
			userFacade.logout(user_test);
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		assertFalse(test_return>0);
	}

}
