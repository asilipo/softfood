package it.softfood.test.ristorantefacade.inserimentoristorante;

import it.softfood.entity.Indirizzo;
import it.softfood.entity.Ristorante;
import it.softfood.entity.Tavolo;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.handler.IRistoranteFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.AccessControlException;

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

public class TC7 extends TestCase {

	private IUserFacade userFacade;
	private IRistoranteFacade ristoranteFacade;
	private User user;
	private Tavolo tavoloInserito;
	private Ristorante ristorante;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			userFacade = (IUserFacade) registry.lookup("UserFacade");
			ristoranteFacade = (IRistoranteFacade) registry.lookup("RistoranteFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
			fail("Exception");
		}
		
		user = userFacade.login(Ruolo.TEST, "test");
		
	}

	@After
	public void tearDown() throws Exception {
		
		userFacade.logout(user);
	}

	@Test
	public void testRimuoviRistorante() {
		User user1 = null;
		try {
			user1 = userFacade.login(Ruolo.AMMINISTRATORE, "123456") ;

			Indirizzo indirizzo = new Indirizzo();
			indirizzo.setCap("a");
			indirizzo.setCitta("Avellino");
			indirizzo.setCivico("23 A");
			indirizzo.setId(1000000L);
			indirizzo.setProvincia("AV");
			indirizzo.setVia("via Roma");
			
			ristorante = new Ristorante();
			ristorante.setIndirizzo(indirizzo);
			ristorante.setPartitaIva("01234567890");
			ristorante.setRagioneSociale("Ristorante Test");
			ristorante = ristoranteFacade.inserisciRistorante(user1, ristorante);
			
			
			userFacade.logout(user1);
		} catch (RemoteException e) {
			fail ("RemoteException");
		}catch (AccessControlException ace) {
			ristorante=null;
			try {
				userFacade.logout(user1);
			} catch (RemoteException e) {
				fail ("RemoteException");
			}
		}
		
		assertNull(ristorante);
	}
}
