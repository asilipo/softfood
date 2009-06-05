package it.softfood.test.articolomenufacade.selezionepietanze;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSelezionaPietanze {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.articolomenufacade.selezionepietanze");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC3.class);
		//$JUnit-END$
		return suite;
	}

}
