package it.softfood.test.articolomenufacade.rimozionepietanzamenu;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteRimuoviPietanzaMenu {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.articolomenufacade.rimozionepietanzamenu");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC1.class);
		//$JUnit-END$
		return suite;
	}

}
