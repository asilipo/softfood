package it.softfood.test.tavolofacade.SelezioneTavolo;

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
	
	private ITavoloFacade tavoloFacade;
	private IRistoranteFacade ristoranteFacade;
	private IUserFacade userFacade;
	private User user;
	private Tavolo tavolo;

	@Before
	public void setUp() throws Exception {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			tavoloFacade = (ITavoloFacade) registry.lookup("TavoloFacade");
			ristoranteFacade = (IRistoranteFacade) registry.lookup("RistoranteFacade");
			userFacade = (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
		
		user = userFacade.login(Ruolo.TESTER, "test", "test");
		
		Ristorante ristorante = ristoranteFacade.selezionaRistorantePerRagioneSociale(user, "La taverna");
		
		tavolo = new Tavolo();
		tavolo.setAttivo(true);
		tavolo.setId(1000000L);
		tavolo.setOccupato(false);
		tavolo.setRiferimento("Tavolo test");
		tavolo.setRistorante(ristorante);
		tavolo = tavoloFacade.inserisciTavolo(user, tavolo);
	}

	@After
	public void tearDown() throws Exception {
		tavoloFacade.rimuoviTavolo(user, 1000000L);
		userFacade.logout(user);
	}

	@Test
	public void testSelezionaTavolo() throws RemoteException {
		User user = new User("cameriere 1", null, Ruolo.CAMERIERE.toString());		
		user = userFacade.login(Ruolo.CAMERIERE, "1234", "cameriere 1");
		Tavolo tavoloAttuale = tavoloFacade.selezionaTavolo(user, 1000000L);
		Assert.assertNull(tavoloAttuale);
		userFacade.logout(user);
	}

}
