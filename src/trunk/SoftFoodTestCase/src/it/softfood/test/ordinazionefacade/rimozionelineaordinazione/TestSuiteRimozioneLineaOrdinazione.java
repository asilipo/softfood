package it.softfood.test.ordinazionefacade.rimozionelineaordinazione;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteRimozioneLineaOrdinazione {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.ordinazionefacade.rimozionelineaordinazione");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC5.class);
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC4.class);
		//$JUnit-END$
		return suite;
	}

}
