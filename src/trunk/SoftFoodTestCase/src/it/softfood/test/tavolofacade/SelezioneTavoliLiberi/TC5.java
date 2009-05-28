package it.softfood.test.tavolofacade.SelezioneTavoliLiberi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import it.softfood.entity.Ristorante;
import it.softfood.entity.Tavolo;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.handler.IRistoranteFacade;
import it.softfood.handler.ITavoloFacade;
import it.softfood.handler.IUserFacade;
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

public class TC5 extends TestCase {

	private ITavoloFacade tavoloFacade;
	private IRistoranteFacade ristoranteFacade;
	private IUserFacade userFacade;
	private User user;
	private Tavolo tavolo1;
	private Tavolo tavolo2;
	
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
		
		user = userFacade.login(Ruolo.TESTER, "test");
		
		Ristorante ristorante = ristoranteFacade.selezionaRistorantePerRagioneSociale(user, "La taverna");
		
		tavolo1 = new Tavolo();
		tavolo1.setAttivo(true);
		tavolo1.setId(1000000L);
		tavolo1.setOccupato(false);
		tavolo1.setRiferimento("Tavolo1 test");
		tavolo1.setRistorante(ristorante);
		tavolo1 = tavoloFacade.inserisciTavolo(user, tavolo1);
		
		tavolo1 = new Tavolo();
		tavolo1.setAttivo(true);
		tavolo1.setId(1000001L);
		tavolo1.setOccupato(false);
		tavolo1.setRiferimento("Tavolo2 test");
		tavolo1.setRistorante(ristorante);
		tavolo1 = tavoloFacade.inserisciTavolo(user, tavolo2);
	}

	@After
	public void tearDown() throws Exception {
		tavoloFacade.rimuoviTavolo(user, 1000000L);
		tavoloFacade.rimuoviTavolo(user, 1000001L);
		userFacade.logout(user);
	}

	@Test
	public void testSelezionaTavoliLiberi() throws RemoteException {
		User user = new User("cameriere 1", " ", Ruolo.CAMERIERE.toString());	
		ArrayList<Tavolo> tavoliAttuali = (ArrayList<Tavolo>) tavoloFacade.selezionaTavoliLiberi(user);
		Assert.assertFalse(tavoliAttuali.contains(tavolo1) || tavoliAttuali.contains(tavolo2));
	}

}
