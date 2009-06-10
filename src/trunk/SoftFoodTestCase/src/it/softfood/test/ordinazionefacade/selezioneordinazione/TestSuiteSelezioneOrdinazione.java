package it.softfood.test.ordinazionefacade.selezioneordinazione;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSelezioneOrdinazione {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.ordinazionefacade.selezioneordinazione");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC3.class);
		//$JUnit-END$
		return suite;
	}

}
