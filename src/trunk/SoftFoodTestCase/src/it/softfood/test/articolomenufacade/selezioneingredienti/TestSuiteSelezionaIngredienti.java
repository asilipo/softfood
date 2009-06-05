package it.softfood.test.articolomenufacade.selezioneingredienti;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSelezionaIngredienti {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.articolomenufacade.selezioneingredienti");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC3.class);
		//$JUnit-END$
		return suite;
	}

}
