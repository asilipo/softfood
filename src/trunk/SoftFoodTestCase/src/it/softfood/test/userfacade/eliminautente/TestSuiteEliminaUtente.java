package it.softfood.test.userfacade.eliminautente;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TestSuiteEliminaUtente {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.userfacade.eliminautente");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC5.class);
		suite.addTestSuite(TC6.class);
		suite.addTestSuite(TC7.class);
		suite.addTestSuite(TC8.class);
		suite.addTestSuite(TC9.class);
		//$JUnit-END$
		return suite;
	}

}
