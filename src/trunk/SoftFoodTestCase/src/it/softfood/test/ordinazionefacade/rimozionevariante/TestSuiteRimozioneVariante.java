package it.softfood.test.ordinazionefacade.rimozionevariante;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TestSuiteRimozioneVariante {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.ordinazionefacade.rimozionevariante");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC5.class);
		//$JUnit-END$
		return suite;
	}

}
