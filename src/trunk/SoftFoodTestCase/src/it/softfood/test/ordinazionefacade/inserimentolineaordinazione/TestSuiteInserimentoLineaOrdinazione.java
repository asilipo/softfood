package it.softfood.test.ordinazionefacade.inserimentolineaordinazione;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TestSuiteInserimentoLineaOrdinazione {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.ordinazionefacade.inserimentolineaordinazione");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC12.class);
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC11.class);
		suite.addTestSuite(TC14.class);
		suite.addTestSuite(TC13.class);
		suite.addTestSuite(TC8.class);
		suite.addTestSuite(TC6.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC5.class);
		suite.addTestSuite(TC7.class);
		suite.addTestSuite(TC10.class);
		suite.addTestSuite(TC9.class);
		suite.addTestSuite(TC1.class);
		//$JUnit-END$
		return suite;
	}

}
