package it.softfood.test.articolomenufacade.selezionebevandedisponibili;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSelezionaBevandeDisponibili {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.articolomenufacade.selezionebevandedisponibili");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC2.class);
		//$JUnit-END$
		return suite;
	}

}
