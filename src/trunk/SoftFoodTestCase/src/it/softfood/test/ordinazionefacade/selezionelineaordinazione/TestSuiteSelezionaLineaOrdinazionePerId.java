package it.softfood.test.ordinazionefacade.selezionelineaordinazione;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSelezionaLineaOrdinazionePerId {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.ordinazionefacade.selezionelineaordinazione");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC5.class);
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC1.class);
		//$JUnit-END$
		return suite;
	}

}
