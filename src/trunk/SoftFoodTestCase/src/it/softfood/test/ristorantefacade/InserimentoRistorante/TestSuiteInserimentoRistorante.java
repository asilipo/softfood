package it.softfood.test.ristorantefacade.InserimentoRistorante;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteInserimentoRistorante {

	public static Test suite() {
		
		TestSuite suite = new TestSuite("Test inserimento ristorante");

		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC1.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC24.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC25.class);

		System.out.println("Inserimento ristorante: TEST COMPLETO");

		return suite;
	}

}

