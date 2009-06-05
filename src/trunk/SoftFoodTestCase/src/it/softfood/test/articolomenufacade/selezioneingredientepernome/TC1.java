package it.softfood.test.articolomenufacade.selezioneingredientepernome;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.handler.IArticoloMenuFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.AccessControlException;
import java.util.Date;

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
		} catch (AccessControlException e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
		
		user = userFacade.login(Ruolo.TEST, "test");//da togliere
		
		//Ristorante ristorante = ristoranteFacade.selezionaRistorantePerRagioneSociale(user, "La taverna");
		ingrediente = new Ingrediente();
		ingrediente.setDescrizione("Ingrediente di test");
		ingrediente.setId(10000L);
		ingrediente.setNome("Ingrediente di Test");
		ingrediente.setTipoIngrediente("IngredienteLungaConservazione");
		ingrediente.setScadenza(new Date(109,1,21));
		ingrediente.setUnitaMisura("g");
		ingrediente.setVariante(true);

		ingrediente = articoloFacade.inserisciIngrediente(user, ingrediente);
	}

	@After
	public void tearDown() throws Exception {
		articoloFacade.rimuoviIngrediente(user, 1000000L);
		userFacade.logout(user); //da togliere
	}

	@Test
	public void testSelezionaIngredientePerNome() throws RemoteException {
		
		User user1 = userFacade.login(Ruolo.CUOCO, "12345");
		//user1.setUserName("cameriere 1");
		
		Ingrediente ingredienteAttuale=null;
		try{			
			ingredienteAttuale= articoloFacade.selezionaIngredientePerNome(user1, ingrediente.getNome());
		}catch(Exception e){
			ingredienteAttuale = null;
		}
		if(user1 != null)
			userFacade.logout(user1);
		//dovrebbe essere non nullo
		Assert.assertNotNull(ingredienteAttuale);
	}

}