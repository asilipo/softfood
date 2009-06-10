package it.softfood.test.ristorantefacade.selezioneristoranteperpartitaiva;

import static org.junit.Assert.*;
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
		
		Indirizzo indirizzo = new Indirizzo();
		indirizzo.setCap("83100");
		indirizzo.setCitta("Avellino");
		indirizzo.setCivico("23 A");
		indirizzo.setId(1000000L);
		indirizzo.setProvincia("AV");
		indirizzo.setVia("via Roma");
		
		ristorante = new Ristorante();
		ristorante.setIndirizzo(indirizzo);
		ristorante.setPartitaIva("01234567890");
		ristorante.setRagioneSociale("Ristorante Test");
		
		ristorante = ristoranteFacade.inserisciRistorante(user, ristorante);
	
	}

	@After
	public void tearDown() throws Exception {
		ristoranteFacade.rimuoviRistorante(user, "Ristorante Test");
		userFacade.logout(user);
	}

	@Test
	public void testSelezionaRistorantePerRagioneSociale() {
		User user1 = null;
		try {
			user1 = userFacade.login(Ruolo.CASSIERE, "1234567") ;
			ristorante=ristoranteFacade.selezionaRistorantePerRagioneSociale(user1, "Ristorante Test");
			userFacade.logout(user1);
		} catch (RemoteException e) {
			fail ("RemoteException");
		}catch (AccessControlException ace) {
			ristorante = null;
			try {
				userFacade.logout(user1);
			} catch (RemoteException e) {
				fail ("RemoteException");
			}
		}
		
		assertNull(ristorante);
	}

}
