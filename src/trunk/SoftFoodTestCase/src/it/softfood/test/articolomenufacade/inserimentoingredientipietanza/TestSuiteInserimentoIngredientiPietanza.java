package it.softfood.test.articolomenufacade.inserimentoingredientipietanza;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteInserimentoIngredientiPietanza {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.articolomenufacade.inserimentoingredientipietanza");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC6.class);
		suite.addTestSuite(TC5.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC7.class);
		//$JUnit-END$
		return suite;
	}

}
