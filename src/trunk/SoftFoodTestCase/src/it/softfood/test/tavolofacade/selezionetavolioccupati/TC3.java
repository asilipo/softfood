package it.softfood.test.tavolofacade.selezionetavolioccupati;

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
import java.security.AccessControlException;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TC3 extends TestCase {

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
		
		user = userFacade.login(Ruolo.AMMINISTRATORE, "123456");
		
		Ristorante ristorante = ristoranteFacade.selezionaRistorantePerRagioneSociale(user, "La taverna");
		
		tavoloInserito = new Tavolo();
		tavoloInserito.setRiferimento("Tavolo Test");
		tavoloInserito.setAttivo(true);
		tavoloInserito.setId(1000000L);
		tavoloInserito.setNumeroPosti(4);
		tavoloInserito.setOccupato(true);
		tavoloInserito.setRistorante(ristorante);
		
		tavoloInserito = tavoloFacade.inserisciTavolo(user, tavoloInserito);
	}

	@After
	public void tearDown() throws Exception {
		tavoloFacade.rimuoviTavolo(user, 1000000L);
		userFacade.logout(user);
	}

	@Test
	public void testSelezionaTavoliOccupati() {
		ArrayList<Tavolo> tavoliAttuali = null;
		User user1 = null;

		try {
			user1 = userFacade.login(Ruolo.CASSIERE, "1234567");
			tavoliAttuali = (ArrayList<Tavolo>) tavoloFacade.selezionaTavoliOccupati(user1);
			userFacade.logout(user1);
		} catch (RemoteException e) {
			fail("RemoteException");
		} catch (AccessControlException ace) {
			tavoliAttuali = null;
			try {
				userFacade.logout(user1);
			} catch (RemoteException e) {
				fail("RemoteException");
			}
		} catch (NullPointerException npe) {
			tavoliAttuali = null;
			try {
				userFacade.logout(user1);
			} catch (RemoteException e) {
				fail("RemoteException");
			}
		}
		
		assertNull(tavoliAttuali);
	}

}
