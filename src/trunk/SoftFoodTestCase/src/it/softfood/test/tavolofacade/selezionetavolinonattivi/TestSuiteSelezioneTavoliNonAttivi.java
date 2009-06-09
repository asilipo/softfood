package it.softfood.test.tavolofacade.selezionetavolinonattivi;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TestSuiteSelezioneTavoliNonAttivi {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.tavolofacade.selezionetavolinonattivi");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC2.class);
		//$JUnit-END$
		return suite;
	}

}
