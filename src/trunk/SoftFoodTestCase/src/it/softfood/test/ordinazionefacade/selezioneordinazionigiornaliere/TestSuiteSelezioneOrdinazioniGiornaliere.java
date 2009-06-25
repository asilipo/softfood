package it.softfood.test.ordinazionefacade.selezioneordinazionigiornaliere;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TestSuiteSelezioneOrdinazioniGiornaliere {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.ordinazionefacade.selezioneordinazionigiornaliere");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC3.class);
		//$JUnit-END$
		return suite;
	}

}
