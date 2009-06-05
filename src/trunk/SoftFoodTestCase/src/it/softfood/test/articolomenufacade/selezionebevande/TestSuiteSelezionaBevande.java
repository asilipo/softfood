package it.softfood.test.articolomenufacade.selezionebevande;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSelezionaBevande {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.articolomenufacade.selezionebevande");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC2.class);
		//$JUnit-END$
		return suite;
	}

}
