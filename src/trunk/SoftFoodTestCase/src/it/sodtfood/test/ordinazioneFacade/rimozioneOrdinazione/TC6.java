package it.softfood.ordinazioneFacade.rimozioneOrdinazione;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.Tavolo;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.handler.IOrdinazioneFacade;
import it.softfood.handler.ITavoloFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.AccessControlException;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TC6 {
	private IOrdinazioneFacade ordinazioneFacade;
	private IUserFacade userFacade;
	private User user;
	private ITavoloFacade tavoloFacade;
	private long id;
	private Ordinazione ordinazione;
	private List<Tavolo> tavolo;
	private LineaOrdinazione lineaOrdinazione;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			ordinazioneFacade = (IOrdinazioneFacade) registry.lookup("OrdineFacade"); //CONTROLLARE
			//ristoranteFacade = (IRistoranteFacade) registry.lookup("RistoranteFacade");
			tavoloFacade = (ITavoloFacade) registry.lookup("TavoloFacade");
			userFacade = (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
		
		user = userFacade.login(Ruolo.TEST, "test");
		
		LineaOrdinazione linea = ordinazioneFacade.selezionaLineaOrdinazionePerId(user, 10L);
			
		ordinazione = new Ordinazione();
		ordinazione.setData(new Date(109,1,21));
		ordinazione.setId( (long)1000000 );
		ordinazione.setCoperti(4);
		tavolo = tavoloFacade.selezionaTavoliLiberi(user);
		
		id = tavolo.get(0).getId();
		ordinazione.setTavolo(tavolo.get(0));
		ordinazione.setId(100000L);
		ordinazione.setTerminato(false);
	
		
		
		ordinazioneFacade.inserisciOrdinazione(user, ordinazione);
		
		
		
		}

	@After
	public void tearDown() throws Exception {
		ordinazioneFacade.rimuoviOrdinazione(user, ordinazione.getId(), false);
		userFacade.logout(user); //da togliere
	}

	@Test
	public void testRimozioneOrdinazione() throws RemoteException {
		
		User user1 = userFacade.login(Ruolo.CAMERIERE, "1234");
	//	user1.setUserName("cuoco");
		
		boolean ordinazioneAttuale = false;
		try{	
			ordinazioneAttuale = ordinazioneFacade.rimuoviOrdinazione(user1, ordinazione.getId(), null);
			
		}catch(AccessControlException e){
			ordinazioneAttuale = false;
		}
	
		if(user1 != null)
			userFacade.logout(user1);
		
		Assert.assertFalse(ordinazioneAttuale);
	}
}
