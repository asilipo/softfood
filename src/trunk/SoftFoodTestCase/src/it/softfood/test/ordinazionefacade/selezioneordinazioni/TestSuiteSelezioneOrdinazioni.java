package it.softfood.test.ordinazionefacade.selezioneordinazioni;

import it.softfood.test.ordinazionefacade.selezioneordinazioni.*;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSelezioneOrdinazioni {
	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.ordinazionefacade.selezioneordinazioni");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC4.class);

		
		//$JUnit-END$
		return suite;
	}
}
