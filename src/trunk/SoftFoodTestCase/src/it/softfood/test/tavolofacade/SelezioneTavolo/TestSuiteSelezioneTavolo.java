package it.softfood.test.tavolofacade.SelezioneTavolo;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSelezioneTavolo {

	public static Test suite() {
		
		TestSuite suite = new TestSuite("Test selezione tavolo");

		suite.addTestSuite(it.softfood.test.tavolofacade.SelezioneTavolo.TC1.class);
		suite.addTestSuite(it.softfood.test.tavolofacade.SelezioneTavolo.TC2.class);
		suite.addTestSuite(it.softfood.test.tavolofacade.SelezioneTavolo.TC3.class);
		suite.addTestSuite(it.softfood.test.tavolofacade.SelezioneTavolo.TC4.class);
		suite.addTestSuite(it.softfood.test.tavolofacade.SelezioneTavolo.TC5.class);
		suite.addTestSuite(it.softfood.test.tavolofacade.SelezioneTavolo.TC6.class);
		suite.addTestSuite(it.softfood.test.tavolofacade.SelezioneTavolo.TC7.class);
		suite.addTestSuite(it.softfood.test.tavolofacade.SelezioneTavolo.TC8.class);
		suite.addTestSuite(it.softfood.test.tavolofacade.SelezioneTavolo.TC9.class);

		System.out.println("Selezione tavolo: TEST COMPLETO");

		return suite;
	}

}

