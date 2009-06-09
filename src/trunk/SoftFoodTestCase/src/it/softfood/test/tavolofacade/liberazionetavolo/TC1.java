package it.softfood.test.tavolofacade.liberazionetavolo;

import it.softfood.entity.Ristorante;
import it.softfood.entity.Tavolo;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.handler.IRistoranteFacade;
import it.softfood.handler.ITavoloFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
	private ITavoloFacade tavoloFacade;
	private IRistoranteFacade ristoranteFacade;
	private User user;
	private Tavolo tavoloInserito;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			userFacade = (IUserFacade) registry.lookup("UserFacade");
			tavoloFacade = (ITavoloFacade) registry.lookup("TavoloFacade");
			ristoranteFacade = (IRistoranteFacade) registry.lookup("RistoranteFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
			fail("Exception");
		}
		
		user = userFacade.login(Ruolo.TEST, "test");
		
		Ristorante ristorante = ristoranteFacade.selezionaRistorantePerRagioneSociale(user, "La taverna");
		
		tavoloInserito = new Tavolo();
		tavoloInserito.setRiferimento("Tavolo Test");
		tavoloInserito.setAttivo(true);
		tavoloInserito.setId(1000000L);
		tavoloInserito.setNumeroPosti(4);
		tavoloInserito.setOccupato(false);
		tavoloInserito.setRistorante(ristorante);
		
		tavoloInserito = tavoloFacade.inserisciTavolo(user, tavoloInserito);
	}

	@After
	public void tearDown() throws Exception {
		tavoloFacade.rimuoviTavolo(user, 1000000L);
		userFacade.logout(user);
	}
	
	@Test
	public void testLiberaTavolo() {
		User user1 = null;
		Boolean stato = false;
		try {
			user1 = userFacade.login(Ruolo.CAMERIERE, "1234");
			stato = tavoloFacade.liberaTavolo(user1, 1000000L);
			userFacade.logout(user1);
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		assertTrue(stato);
	}

}
