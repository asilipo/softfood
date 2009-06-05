package it.softfood.test.articolomenufacade.valutazionedisponibilitabevanda;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteValutazioneDisponibilitaBevanda {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.articolomenufacade.valutazionedisponibilitabevanda");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC4.class);
		//$JUnit-END$
		return suite;
	}

}
