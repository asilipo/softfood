package it.softfood.test.tavolofacade.InserimentoTavolo;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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

public class TC1 extends TestCase {

	private ITavoloFacade tavoloFacade;
	private IRistoranteFacade ristoranteFacade;
	private IUserFacade userFacade;
	private User user;
	private Tavolo tavolo;
	private Ristorante ristorante;
	
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
		ristorante = ristoranteFacade.selezionaRistorantePerRagioneSociale(user, "La taverna");
	}

	@After
	public void tearDown() throws Exception {
		tavoloFacade.rimuoviTavolo(user, tavolo.getId());
		userFacade.logout(user);
	}

	@Test
	public void testInserisciTavolo() throws RemoteException {
		User user = userFacade.login(Ruolo.AMMINISTRATORE, "123456", "amministratore");
		tavolo = new Tavolo();
		tavolo.setNumeroPosti(4);
		tavolo.setAttivo(true);
		tavolo.setId(1000000L);
		tavolo.setOccupato(false);
		tavolo.setRiferimento("Tavolo Test");
		tavolo.setRistorante(ristorante);
		Tavolo tavoloAttuale = tavoloFacade.inserisciTavolo(user, tavolo);
		
		Assert.assertTrue(tavolo.equals(tavoloAttuale));
		userFacade.logout(user);
	}

}
