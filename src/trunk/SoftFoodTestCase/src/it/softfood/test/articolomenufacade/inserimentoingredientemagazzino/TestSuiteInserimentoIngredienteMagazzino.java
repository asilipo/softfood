package it.softfood.test.articolomenufacade.inserimentoingredientemagazzino;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteInserimentoIngredienteMagazzino {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.articolomenufacade.inserimentoingredientemagazzino");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC5.class);
		suite.addTestSuite(TC6.class);
		//$JUnit-END$
		return suite;
	}

}
