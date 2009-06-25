package it.softfood.test.ordinazionefacade.selezionelineeordinazioneperordinazionetipopietanza;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.IngredientePietanza;
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
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Date;

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

	private IOrdinazioneFacade ordinazioneFacade;
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
	private Ingrediente ingrediente;
	private IngredientePietanza ingrediente_pietanza;

	@SuppressWarnings("deprecation")
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
			fail ("Exception");
		}
		
		user = userFacade.login(Ruolo.TEST, "test");
		
		ristorante = ristoranteFacade.selezionaRistorantePerRagioneSociale(user, "La taverna");
		
		tavolo = new Tavolo();
		tavolo.setNumeroPosti(4);
		tavolo.setAttivo(true);
		tavolo.setOccupato(true);
		tavolo.setRiferimento("Tavolo Test");
		tavolo.setRistorante(ristorante);
		
		tavolo = tavoloFacade.inserisciTavolo(user, tavolo);
		
		pietanza = new Pietanza();
		pietanza.setNome("Antipasto all'italiana");
		pietanza.setTipoPietanza(TipoPietanza.ANTIPASTI.ordinal());
		pietanza = articoloFacade.inserisciPietanzaMenu(user, pietanza);
		
		ingrediente = new Ingrediente();
		ingrediente.setNome("INGREDIENTE TEST");
		ingrediente.setScadenza(new Date(109,5,30));
		ingrediente = articoloFacade.inserisciIngrediente(user, ingrediente);
		
		articoloFacade.inserisciIngredienteMagazzino(user, ingrediente.getId(), 100);
		
		ingrediente_pietanza = new IngredientePietanza();
		ingrediente_pietanza.setArticolo(pietanza);
		ingrediente_pietanza.setIngrediente(ingrediente);
		ingrediente_pietanza.setQuantita(1);
		ingrediente_pietanza=articoloFacade.inserisciIngredientePietanza(user, ingrediente_pietanza);
				
		ordinazione = new Ordinazione();
		ordinazione.setCoperti(4);
		ordinazione.setTerminato(true);
		ordinazione.setTavolo(tavolo);	
		ordinazione.setData(new Date(109,5,30));
		
		ordinazione = ordinazioneFacade.inserisciOrdinazione(user, ordinazione);
		
		lineaordinazione = new LineaOrdinazione();
		lineaordinazione.setId(1000000L);
		lineaordinazione.setArticolo(pietanza);
		lineaordinazione.setEvaso(false);
		lineaordinazione.setQuantita(1);
		lineaordinazione.setOrdinazione(ordinazione);
		
		lineaordinazione = ordinazioneFacade.inserisciLineaOrdinazione(user, lineaordinazione);
	
	}

	@After
	public void tearDown() throws Exception {
		ordinazioneFacade.rimuoviOrdinazione(user, ordinazione.getId(), false);
		tavoloFacade.rimuoviTavolo(user, tavolo.getId());		
		articoloFacade.rimuoviPietanzaMenu(user, pietanza.getId());
		articoloFacade.rimuoviIngrediente(user, ingrediente.getId());
		userFacade.logout(user);		
	}

	@Test
	public void testSelezionaLineaOrdinazionePerOrdinazioneTipoPietanza() {
		User user_test = null;
		try {
			user_test = userFacade.login(Ruolo.CASSIERE, "1234567");
		} catch (RemoteException e) {
			fail ("RemoteException");
		}		
		
		ArrayList<LineaOrdinazione> linee=null;
		
		try {
			linee=ordinazioneFacade.selezionaLineeOrdinazionePerOrdinazioneTipoPietanza(user_test, ordinazione, TipoPietanza.ANTIPASTI);
		} catch (AccessControlException e) {
			linee=null;
		} catch (Exception e) {
			fail(e.toString());
		} 
		
		try {
			userFacade.logout(user_test);
		} catch (RemoteException e) {
			fail ("RemoteException");
		}
		
		assertNull(linee);
	}

}
