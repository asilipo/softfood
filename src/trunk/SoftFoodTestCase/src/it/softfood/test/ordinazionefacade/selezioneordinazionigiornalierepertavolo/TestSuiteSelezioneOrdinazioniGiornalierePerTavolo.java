package it.softfood.test.ordinazionefacade.selezioneordinazionigiornalierepertavolo;

import it.softfood.test.ordinazionefacade.selezioneordinazionigiornalierepertavolo.*;
import it.softfood.test.ordinazionefacade.selezioneordinazionigiornalierepertavolo.TC5;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSelezioneOrdinazioniGiornalierePerTavolo {
	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.ordinazionefacade.selezioneordinazionigiornalierepertavolo");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC4.class);
		//suite.addTestSuite(TC5.class);
		

		
		//$JUnit-END$
		return suite;
	}
}
