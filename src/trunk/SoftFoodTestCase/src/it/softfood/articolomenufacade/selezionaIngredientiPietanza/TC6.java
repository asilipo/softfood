package it.softfood.articolomenufacade.selezionaIngredientiPietanza;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.handler.IArticoloMenuFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TC6 {
	private IArticoloMenuFacade articoloFacade;
	private IUserFacade userFacade;
	private User user;
	private Ingrediente ingrediente;

	@Before
	public void setUp() throws Exception {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			articoloFacade = (IArticoloMenuFacade) registry.lookup("ArticoloFacade"); //CONTROLLARE
			//ristoranteFacade = (IRistoranteFacade) registry.lookup("RistoranteFacade");
			userFacade = (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
		
		user = userFacade.login(Ruolo.TEST, "test");//da togliere
		
		//Ristorante ristorante = ristoranteFacade.selezionaRistorantePerRagioneSociale(user, "La taverna");
	
	}

	@After
	public void tearDown() throws Exception {
		userFacade.logout(user); //da togliere
	}

	@Test
	public void testSelezionaIngredientePerNome() throws RemoteException {
		
		User user1 = userFacade.login(null, "1234");
		user1.setUserName("cameriere 1");
		long id = 1000000;
		
		ArrayList<Ingrediente> ingredienti = null;
		try{			
			ingredienti = articoloFacade.selezionaIngredientiPietanza(user1, id);
		}catch(Exception e){
			ingredienti = null;
		}
		if(user1 != null)
			userFacade.logout(user1);
		//dovrebbe essere non nullo
		Assert.assertNull(ingredienti);
	}

}
