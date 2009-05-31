package it.softfood.test.ristorantefacade.InserimentoRistorante;

import it.softfood.entity.Indirizzo;
import it.softfood.entity.Pietanza;
import it.softfood.entity.Ristorante;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.handler.IRistoranteFacade;
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

public class TC1 extends TestCase {

	private IRistoranteFacade ristoranteFacade;
	private IUserFacade userFacade;
	private Ristorante ristorante;
	private User user;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			ristoranteFacade = (IRistoranteFacade) registry.lookup("RistoranteFacade");
			userFacade = (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
		
		user = userFacade.login(Ruolo.TEST, "test", "test");
	}

	@After
	public void tearDown() throws Exception {
		ristoranteFacade.rimuoviRistorante(user, ristorante.getRagioneSociale());
		userFacade.logout(user);
	}

	@Test
	public void testInserisciRistorante() throws RemoteException {

		User user = userFacade.login(Ruolo.AMMINISTRATORE, "123456", "amministratore");
		Pietanza pietanzaAttuale = articoloMenuFacade.inserisciPietanzaMenu(user, pietanza);
		Assert.assertTrue(pietanza.equals(pietanzaAttuale));
	}

}
