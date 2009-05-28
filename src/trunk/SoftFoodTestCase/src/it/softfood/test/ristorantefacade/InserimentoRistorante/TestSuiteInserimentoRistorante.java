package it.softfood.test.ristorantefacade.InserimentoRistorante;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteInserimentoRistorante {

	public static Test suite() {
		
		TestSuite suite = new TestSuite("Test inserimento ristorante");

		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC1.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC10.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC11.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC12.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC13.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC14.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC15.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC16.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC17.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC18.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC19.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC20.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC21.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC22.class);		
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC23.class);
		suite.addTestSuite(it.softfood.test.ristorantefacade.InserimentoRistorante.TC24.class);

		System.out.println("Inserimento ristorante: TEST COMPLETO");

		return suite;
	}

}

