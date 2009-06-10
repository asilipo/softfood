package it.softfood.test.ordinazionefacade.rimozionelineaordinazione;

import static org.junit.Assert.*;
import it.softfood.entity.Indirizzo;
import it.softfood.entity.LineaOrdinazione;
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
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TC6 {
	private IOrdinazioneFacade ordinazioneFacade;
	private LineaOrdinazione lineaOrdinazione;
	private IArticoloMenuFacade articoloFacade;
	private IRistoranteFacade ristoranteFacade;
	private IUserFacade userFacade;
	private User user;
	private Pietanza pietanza;
	private Ordinazione ordinazione;
	private Tavolo tavolo;
	private ITavoloFacade tavoloFacade;
	private Ristorante ristorante;
	

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
		tavolo.setOccupato(false);
		tavolo.setRiferimento("Tavolo Test");
		
		ristorante = new Ristorante();
		ristorante.setRagioneSociale("Ristorante Test");
		ristorante.setPartitaIva("01234567891");
		
		Indirizzo indirizzo = new Indirizzo();
		indirizzo.setVia("via Roma");
		indirizzo.setCivico("24 A");
		indirizzo.setCap("83100");
		indirizzo.setProvincia("Av");
		indirizzo.setCitta("Avellino");
		ristorante.setIndirizzo(indirizzo);
		
		ristorante = ristoranteFacade.inserisciRistorante(user, ristorante);
		tavolo.setRistorante(ristorante);
		
		tavolo = tavoloFacade.inserisciTavolo(user, tavolo);
		
		pietanza = new Pietanza();
		pietanza=new Pietanza();
		pietanza.setNome("BEVANDA TEST");
		pietanza.setTipoPietanza(TipoPietanza.PRIMO_PIATTO.ordinal());
		pietanza=articoloFacade.inserisciPietanzaMenu(user, pietanza);
		
		
		ordinazione = new Ordinazione();
		ordinazione.setCoperti(4);
		ordinazione.setTerminato(true);
		ordinazione.setTavolo(tavolo);	
		ordinazione.setData(new Date(109,5,30));
		
		ordinazione = ordinazioneFacade.inserisciOrdinazione(user, ordinazione);
		System.out.println("OIRD"+ordinazione);
	}

	@After
	public void tearDown() throws Exception {
		ordinazioneFacade.rimuoviOrdinazione(user, ordinazione.getId(), false);
		tavoloFacade.rimuoviTavolo(user, tavolo.getId());		
		articoloFacade.rimuoviPietanzaMenu(user, pietanza.getId());
		ristoranteFacade.rimuoviRistorante(user, ristorante.getRagioneSociale());		
		userFacade.logout(user);		
	}

	@Test
	public void testInserisciOrdinazione() throws Exception {
		User user_test = null;
		try {
			user_test = userFacade.login(Ruolo.CUOCO, "12345");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			user_test = null;
		}		
		boolean verifica = false;
		try {
			verifica = ordinazioneFacade.rimuoviOrdinazione(user_test, ordinazione.getId(),  null);
			
		} catch (Exception e) {
			
			verifica = false;
		} 
		System.out.println("VER"+verifica);
		
		try {
			userFacade.logout(user_test);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		assertFalse(verifica);
	}
}