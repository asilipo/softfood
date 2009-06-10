package it.softfood.test.ristorantefacade.selezioneristoranteperpartitaiva;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSelezionaRistorantePerPartitaIva {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.ristorantefacade.selezioneristoranteperpartitaiva");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC5.class);
		suite.addTestSuite(TC7.class);
		suite.addTestSuite(TC8.class);
		suite.addTestSuite(TC6.class);
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC2.class);
		//$JUnit-END$
		return suite;
	}

}
