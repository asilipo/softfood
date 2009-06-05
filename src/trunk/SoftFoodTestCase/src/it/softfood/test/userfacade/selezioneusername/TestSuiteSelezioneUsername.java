package it.softfood.test.userfacade.selezioneusername;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TestSuiteSelezioneUsername {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.userfacade.selezioneusername");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC3.class);
		//$JUnit-END$
		return suite;
	}

}
