package it.softfood.test.tavolofacade.selezionetavolo;

import it.softfood.entity.Ristorante;
import it.softfood.entity.Tavolo;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.handler.IRistoranteFacade;
import it.softfood.handler.ITavoloFacade;
import it.softfood.handler.IUserFacade;
import it.softfood.login.AuthorizationException;

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
		
		//user = userFacade.login(Ruolo.TESTER, "test");
		
		user = userFacade.login(Ruolo.TEST, "test");//da togliere
		
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
		userFacade.logout(user); //da togliere
	}

	@Test
	public void testSelezionaTavolo() throws RemoteException {
		//User user1 = new User("cameriere 1", "1234", Ruolo.CUOCO.toString());
		
		User user1 = userFacade.login(Ruolo.CUOCO, "12345");
		
		Tavolo tavoloAttuale=null;
		try{
//			
			tavoloAttuale= tavoloFacade.selezionaTavolo(user1, 1000000L);
		}catch(AccessControlException e){
			tavoloAttuale = null;
		}
//		if(user1 != null)
			userFacade.logout(user1);
		
		Assert.assertNull(tavoloAttuale);
	}

}