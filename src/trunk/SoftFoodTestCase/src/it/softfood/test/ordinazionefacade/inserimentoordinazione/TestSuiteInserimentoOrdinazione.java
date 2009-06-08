package it.softfood.test.ordinazionefacade.inserimentoordinazione;

import it.softfood.test.ordinazionefacade.inserimentoordinazione.TC1;
import it.softfood.test.ordinazionefacade.inserimentoordinazione.TC2;
import it.softfood.test.ordinazionefacade.inserimentoordinazione.TC3;
import it.softfood.test.ordinazionefacade.inserimentoordinazione.TC4;
import it.softfood.test.ordinazionefacade.inserimentoordinazione.TC5;
import it.softfood.test.ordinazionefacade.inserimentoordinazione.TC6;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteInserimentoOrdinazione {
	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.ordinazionefacade.inserimentoordinazione");

		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC5.class);
		suite.addTestSuite(TC6.class);

		return suite;
	}
}
