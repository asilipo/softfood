package it.softfood.test.articolomenufacade.inserimentobevandamagazzino;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TestSuiteInserimentoBevandaMagazzino {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.articolomenufacade.inserimentobevandamagazzino");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC6.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC7.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC4.class);
		//$JUnit-END$
		return suite;
	}

}
