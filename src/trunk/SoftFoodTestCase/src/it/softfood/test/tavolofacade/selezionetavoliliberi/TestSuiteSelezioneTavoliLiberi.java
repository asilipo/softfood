package it.softfood.test.tavolofacade.selezionetavoliliberi;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TestSuiteSelezioneTavoliLiberi {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.tavolofacade.selezionetavoliliberi");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC1.class);
		//$JUnit-END$
		return suite;
	}

}
