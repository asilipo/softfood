package it.softfood.test.ordinazionefacade.inserimentoordinazione;

import it.softfood.entity.Indirizzo;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.Pietanza;
import it.softfood.entity.Ristorante;
import it.softfood.entity.Tavolo;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.handler.IArticoloMenuFacade;
import it.softfood.handler.IOrdinazioneFacade;
import it.softfood.handler.IRistoranteFacade;
import it.softfood.handler.ITavoloFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TC5 extends TestCase {

	private IOrdinazioneFacade ordinazioneFacade;
	private IArticoloMenuFacade articoloFacade;
	private IRistoranteFacade ristoranteFacade;
	private IUserFacade userFacade;
	private User user;
	private Pietanza pietanza;
	private Ordinazione ordinazione;
	private ITavoloFacade tavoloFacade;
	private Ristorante ristorante = new Ristorante();
	private Tavolo tavolo;

	@Before
	public void setUp() throws Exception {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			articoloFacade = (IArticoloMenuFacade) registry.lookup("ArticoloFacade");
			ordinazioneFacade =(IOrdinazioneFacade) registry.lookup("OrdineFacade");	
			tavoloFacade =(ITavoloFacade) registry.lookup("TavoloFacade");
			userFacade = (IUserFacade) registry.lookup("UserFacade");
			ristoranteFacade = (IRistoranteFacade)registry.lookup("RistoranteFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
		
		user = userFacade.login(Ruolo.TEST, "test");

		tavolo = new Tavolo();
		tavolo.setNumeroPosti(4);
		tavolo.setAttivo(true);
		tavolo.setOccupato(true);
		tavolo.setRiferimento("Tavolo Test");
		
		ristorante.setRagioneSociale("Ristorante Test");
		ristorante.setPartitaIva("01234567891");
		
		
		Indirizzo indirizzo = new Indirizzo();
		indirizzo.setVia("via Roma");
		indirizzo.setCivico("24 A");
		indirizzo.setCap("83100");
		indirizzo.setProvincia("AV");
		indirizzo.setCitta("Avellino");
		ristorante.setIndirizzo(indirizzo);
		
		ristorante = ristoranteFacade.inserisciRistorante(user, ristorante); 
			
		tavolo.setRistorante(ristorante);
		
		pietanza = new Pietanza();
		pietanza = new Pietanza();
		pietanza.setNome("BEVANDA TEST");
		pietanza.setTipoPietanza(TipoPietanza.PRIMO_PIATTO.ordinal());
		pietanza = articoloFacade.inserisciPietanzaMenu(user, pietanza);
	}

	@After
	public void tearDown() throws Exception {
		tavoloFacade.rimuoviTavolo(user, tavolo.getId());
		ristoranteFacade.rimuoviRistorante(user, ristorante.getRagioneSociale());		
		articoloFacade.rimuoviPietanzaMenu(user, pietanza.getId());
		userFacade.logout(user);		
	}

	@Test
	public void testInserisciOrdinazione() throws Exception {
		User user_test = null;
		try {
			user_test = userFacade.login(Ruolo.AMMINISTRATORE, "123456");
		} catch (RemoteException e) {
			fail ("RemoteException");
		}		
		
		try {
			ordinazione = new Ordinazione();
			
			ordinazione = ordinazioneFacade.inserisciOrdinazione(user_test, null);
		} catch (Exception e) {
			ordinazione = null;
		} 
		
		try {
			userFacade.logout(user_test);
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		assertNull(ordinazione);
	}
	
}
