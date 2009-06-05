package it.softfood.test.articolomenufacade.selezioneingredientipietanza;

import it.softfood.test.articolomenufacade.selezioneingredientepernome.TC1;
import it.softfood.test.articolomenufacade.selezioneingredientepernome.TC2;
import it.softfood.test.articolomenufacade.selezioneingredientepernome.TC3;
import it.softfood.test.articolomenufacade.selezioneingredientepernome.TC4;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSelezionaIngredientiPietanze {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.softfood.test.articolomenufacade.selezioneingredientipietanza");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC4.class);
		//$JUnit-END$
		return suite;
	}

}
