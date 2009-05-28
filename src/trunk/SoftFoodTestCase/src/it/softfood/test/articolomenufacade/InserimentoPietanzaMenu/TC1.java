package it.softfood.test.articolomenufacade.InserimentoPietanzaMenu;

import it.softfood.entity.Pietanza;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.handler.IArticoloMenuFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TC1 {

	private IArticoloMenuFacade articoloMenuFacade;
	private IUserFacade userFacade;
	private User user;
	
	@Before
	public void setUp() throws Exception {	
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			articoloMenuFacade = (IArticoloMenuFacade) registry.lookup("ArticoloFacade");
			userFacade = (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
		
		user = userFacade.login(Ruolo.TESTER, "test");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInserisciPietanzaMenu() {
		Pietanza pietanza = new Pietanza();
		pietanza.setId(1000000L);
		pietanza.setNome("pietanza_test");
		pietanza.setTipo(TipoPietanza.ANTIPASTI);
		pietanza.setTipoPietanza(TipoPietanza.ANTIPASTI.ordinal());
		pietanza.setTipoArticolo("Pietanza");
		
		try {
			articoloMenuFacade.inserisciPietanzaMenu(user, pietanza);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
