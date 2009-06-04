package it.softfood.test.tavolofacade.selezionetavoliliberi;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSelezioneTavoliLiberi {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.tavolofacade.SelezioneTavoliLiberi");
		//$JUnit-BEGIN$	
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC3.class);
		//$JUnit-END$
		return suite;
	}

}
