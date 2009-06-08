package it.softfood.test.ordinazionefacade.rimozioneordinazione;

import it.softfood.test.ordinazionefacade.rimozioneordinazione.TC1;
import it.softfood.test.ordinazionefacade.rimozioneordinazione.TC2;
import it.softfood.test.ordinazionefacade.rimozioneordinazione.TC3;
import it.softfood.test.ordinazionefacade.rimozioneordinazione.TC4;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteRimozioneOrdinazione {
	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.ordinazionefacade.rimozionelineaordinazione");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC4.class);
		//$JUnit-END$
		return suite;
	}

}
