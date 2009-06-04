package it.softfood.test.tavolofacade.selezionetavolo;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSelezioneTavolo {

	public static Test suite() {
		
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.tavolofacade.SelezioneTavolo");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC3.class);
		//$JUnit-END$
		return suite;
	}

}

