package it.softfood.test.articolomenufacade.selezionepietanzepertipo;

import it.softfood.test.articolomenufacade.selezionepietanze.TC1;
import it.softfood.test.articolomenufacade.selezionepietanze.TC2;
import it.softfood.test.articolomenufacade.selezionepietanze.TC3;
import it.softfood.test.articolomenufacade.selezionepietanzedisponibilipertipopietanza.TC4;
import it.softfood.test.articolomenufacade.selezionepietanzedisponibilipertipopietanza.TC5;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuitSelezionaPietanzePerTipo {
	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.articolomenufacade.selezionepietanze");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC5.class);

		//$JUnit-END$
		return suite;
	}
}
