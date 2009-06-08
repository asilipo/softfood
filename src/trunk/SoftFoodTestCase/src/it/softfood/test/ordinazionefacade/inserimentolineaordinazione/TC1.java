package it.softfood.test.ordinazionefacade.inserimentolineaordinazione;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.AccessControlException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import it.softfood.entity.Articolo;
import it.softfood.entity.Indirizzo;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.Pietanza;
import it.softfood.entity.Ristorante;
import it.softfood.entity.Tavolo;
import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.exception.AggiornamentoIngredientiMagazzinoException;
import it.softfood.exception.DisponibilitaBevandaException;
import it.softfood.exception.DisponibilitaPietanzaException;
import it.softfood.exception.UserException;
import it.softfood.handler.IArticoloMenuFacade;
import it.softfood.handler.IOrdinazioneFacade;
import it.softfood.handler.IRistoranteFacade;
import it.softfood.handler.ITavoloFacade;
import it.softfood.handler.IUserFacade;
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
	private LineaOrdinazione lineaordinazione;
	

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
		
		
		
		
		pietanza = new Pietanza();
		pietanza.setNome("BEVANDA TEST");
		pietanza.setTipoPietanza(TipoPietanza.PRIMO_PIATTO.ordinal());
		pietanza = articoloFacade.inserisciPietanzaMenu(user, pietanza);
		
		System.out.println("PIET "+pietanza);
		
		
	
	}

	@After
	public void tearDown() throws Exception {
		articoloFacade.rimuoviPietanzaMenu(user, pietanza.getId());
		tavoloFacade.rimuoviTavolo(user, tavolo.getId());		
		ordinazioneFacade.rimuoviOrdinazione(user, ordinazione.getId(), false);
		userFacade.logout(user);		
	}

	@Test
	public void testInserimentoLineaOrdinazione() throws Exception {
		User user_test = null;
		try {
			user_test = userFacade.login(Ruolo.AMMINISTRATORE, "123456");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			user_test = null;
		}		
		
		try {
			
			tavolo = tavoloFacade.inserisciTavolo(user, tavolo);
			System.out.println("TAV "+tavolo);
			
			ordinazione = new Ordinazione();
			ordinazione.setCoperti(4);
			ordinazione.setTerminato(true);
			ordinazione.setTavolo(tavolo);	
			ordinazione.setData(new Date(109,5,30));
			
			ordinazione = ordinazioneFacade.inserisciOrdinazione(user, ordinazione);
			System.out.println("ORDI "+ordinazione);
			
			
			lineaordinazione = new LineaOrdinazione();
			lineaordinazione.setArticolo(pietanza);
			lineaordinazione.setEvaso(false);
			lineaordinazione.setQuantita(12);
			lineaordinazione.setOrdinazione(ordinazione);
			
			lineaordinazione = ordinazioneFacade.inserisciLineaOrdinazione(user_test, lineaOrdinazione);
			
		} catch (Exception e) {
			System.out.println(e);
			lineaordinazione = null;
		} 
		
		try {
			userFacade.logout(user_test);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		assertNotNull(lineaordinazione);
	}

}
