package it.softfood.test.articolomenufacade.valutazionedisponibilitabevanda;

import it.softfood.entity.Bevanda;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.handler.IArticoloMenuFacade;
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
	private IArticoloMenuFacade articoloFacade;
	private User user;
	private Bevanda bevanda;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			articoloFacade = (IArticoloMenuFacade) registry.lookup("ArticoloFacade");
			userFacade = (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
		
		user=userFacade.login(Ruolo.TEST, "test");
		
		bevanda=new Bevanda();
		bevanda.setCapacita(1000F);
		bevanda.setNome("BEVANDA TEST");
		bevanda.setTipoArticolo("Bevanda");
		bevanda.setTipoPietanza(TipoPietanza.BEVANDA.ordinal());
		
		bevanda=articoloFacade.inserisciBevandaMenu(user, bevanda);
		
		articoloFacade.inserisciBevandaMagazzino(user, bevanda.getId(), 10000);
	}

	@After
	public void tearDown() throws Exception {
		articoloFacade.rimuoviBevandaMenu(user, bevanda.getId());
		userFacade.logout(user);
		
	}

	@Test
	public void testValutazioneDisponibilitaBevanda() {
		User user_test=null;
		try {
			user_test=userFacade.login(Ruolo.CUOCO, "12345");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			user_test=null;
		}
		
		
		
		
		int test_return = 0;
		try {
			test_return=articoloFacade.selezionaDisponibilitaBevanda(user_test, null);
		} catch (AccessControlException e) {
			System.out.println(e);
			test_return = 0;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			userFacade.logout(user_test);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(test_return>0);
	}

}
