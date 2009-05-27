package it.softfood.test.ristorantefacade.InserimentoRistorante;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.softfood.entity.Indirizzo;
import it.softfood.entity.User;
import it.softfood.handler.IRistoranteFacade;
import it.softfood.handler.IUserFacade;
import it.softfood.handler.RistoranteFacade;
import it.softfood.entity.Ristorante;
import it.softfood.enumeration.Ruolo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TC23 {


	private IRistoranteFacade ristoranteFacade;
	private IUserFacade userFacade;
	private Ristorante actual;
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
		
		user = userFacade.login("test", "test"); 
	}

	@After
	public void tearDown() throws Exception {
		ristoranteFacade.rimuoviRistorante(user, actual.getRagioneSociale());
		userFacade.logout(user);
	}

	@Test
	public void testInserisciRistorante() throws RemoteException {
		User user1 = new User("amministratore","123456",Ruolo.AMMINISTRATORE);

		Indirizzo indirizzo = new Indirizzo(100L, "82100", "Benevento", "24 a","","via Roma",null,null,null);
		Ristorante expected = new Ristorante("Ristorante test",indirizzo,"01234567890");
		actual = ristoranteFacade.inserisciRistorante(user, expected);
		
		assertNull(actual);

	}
}
