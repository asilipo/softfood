package it.softfood.test.articolomenufacade.selezioneingredientipietanza;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.IngredientePietanza;
import it.softfood.entity.Pietanza;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.handler.IArticoloMenuFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TC2 {
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
		
		ingrediente = new Ingrediente();
		ingrediente.setDescrizione("Ingrediente di test");
		ingrediente.setId(1000000L);
		ingrediente.setNome("Ingrediente di Test");
		ingrediente.setTipoIngrediente("IngredienteLungaConservazione");
		ingrediente.setScadenza(new Date(109,8,26));
		ingrediente.setUnitaMisura("g");
		ingrediente.setVariante(true);

		ingrediente = articoloFacade.inserisciIngrediente(user, ingrediente);
	
	}

	@After
	public void tearDown() throws Exception {
		articoloFacade.rimuoviIngrediente(user, ingrediente.getId());
		userFacade.logout(user); //da togliere
	}

	@Test
	public void testSelezionaIngredientePietanza() throws RemoteException {
		
		User user1 = userFacade.login(Ruolo.CAMERIERE, "1234");
		
	
		
		ArrayList<Ingrediente> ingredienti = null;
		try{			
			ingredienti = articoloFacade.selezionaIngredientiPietanza(user1, ingrediente.getId());
		}catch(Exception e){
			ingredienti = null;
		}
		
		userFacade.logout(user1);
		//dovrebbe essere non nullo
		Assert.assertNotNull(ingredienti);
	}
}
