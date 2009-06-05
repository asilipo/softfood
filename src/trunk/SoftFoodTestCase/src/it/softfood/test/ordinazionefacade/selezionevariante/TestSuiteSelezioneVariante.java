package it.softfood.test.ordinazionefacade.selezionevariante;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TestSuiteSelezioneVariante {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.ordinazionefacade.selezionevariante");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC5.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC4.class);
		//$JUnit-END$
		return suite;
	}

}
