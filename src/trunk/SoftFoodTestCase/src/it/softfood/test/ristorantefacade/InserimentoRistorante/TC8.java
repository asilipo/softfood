package it.softfood.test.ristorantefacade.InserimentoRistorante;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import it.softfood.entity.Indirizzo;
import it.softfood.entity.User;
import it.softfood.handler.IRistoranteFacade;
import it.softfood.handler.RistoranteFacade;
import it.softfood.entity.Ristorante;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TC8 {

	private IRistoranteFacade ristoranteFacade;
	private Ristorante ristorante;
	private User user;
	
	@Before
	public void setUp() throws Exception {
		 ristoranteFacade = RistoranteFacade.getInstance();
		 
		 Indirizzo indirizzo = new Indirizzo();
		 indirizzo.setCap("83100");
		 indirizzo.setCitta("Avellino");
		 indirizzo.setCivico("10");
		 indirizzo.setId(1000000L);
		 indirizzo.setProvincia("AV");
		 indirizzo.setVia("via Roma");
		 
		 ristorante = new Ristorante();
		 ristorante.setIndirizzo(indirizzo);
		 ristorante.setPartitaIva("01234567891");
		 ristorante.setRagioneSociale("Test");
		 
		 user = new User("test", "test", "test");		 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInserisciRistorante() throws RemoteException {
		ristoranteFacade.inserisciRistorante(user, ristorante);
	}

	@Test
	public void testModificaRistorante() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaRistorantePerRagioneSociale() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelezionaRistorantePerPartitaIva() {
		fail("Not yet implemented");
	}

	@Test
	public void testRimuoviRistorante() {
		fail("Not yet implemented");
	}

}
