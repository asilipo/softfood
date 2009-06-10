package it.softfood.test.ristorantefacade.selezioneristoranteperragionesociale;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSelezionaRistorantePerRagioneSociale {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.ristorantefacade.selezioneristoranteperragionesociale");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC5.class);
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC6.class);
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC3.class);
		//$JUnit-END$
		return suite;
	}

}
