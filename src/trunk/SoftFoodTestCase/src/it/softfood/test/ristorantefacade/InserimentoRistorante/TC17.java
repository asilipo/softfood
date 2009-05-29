package it.softfood.test.ristorantefacade.InserimentoRistorante;

import it.softfood.entity.Indirizzo;
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

public class TC17 extends TestCase {

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
		
		user = userFacade.login(Ruolo.TESTER, "test", "test");
	}

	@After
	public void tearDown() throws Exception {
		userFacade.logout(user);
	}

	@Test
	public void testInserisciRistorante() throws RemoteException {
		 Indirizzo indirizzo = new Indirizzo();
		 indirizzo.setCap("83100");
		 indirizzo.setCitta("Avellino");
		 indirizzo.setCivico("");
		 indirizzo.setProvincia("AV");
		 indirizzo.setVia("via Roma");
		 indirizzo.setId(1000000L);
		 
		 ristorante = new Ristorante();
		 ristorante.setIndirizzo(indirizzo);
		 ristorante.setPartitaIva("01234567891");
		 ristorante.setRagioneSociale("Ristorante test");
		 
		 User user = new User("amministratore", "123456", Ruolo.AMMINISTRATORE.toString());	
		 Ristorante ristoranteAttuale = ristoranteFacade.inserisciRistorante(user, ristorante);
		 
		 Assert.assertNull(ristoranteAttuale);
	}

}
