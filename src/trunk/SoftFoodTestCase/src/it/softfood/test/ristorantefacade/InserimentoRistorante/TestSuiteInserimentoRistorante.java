package it.softfood.test.ristorantefacade.InserimentoRistorante;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteInserimentoRistorante {

	public static Test suite() {
		
		TestSuite suite = new TestSuite(
			"Test for it.softfood.test.tavolofacade.InserimentoRistorante");
		//$JUnit-BEGIN$
		suite.addTestSuite(TC1.class);
		suite.addTestSuite(TC2.class);
		suite.addTestSuite(TC3.class);
		suite.addTestSuite(TC4.class);
		suite.addTestSuite(TC5.class);
		suite.addTestSuite(TC6.class);
		suite.addTestSuite(TC7.class);
		suite.addTestSuite(TC8.class);
		suite.addTestSuite(TC9.class);
		suite.addTestSuite(TC10.class);
		suite.addTestSuite(TC11.class);
		suite.addTestSuite(TC12.class);
		suite.addTestSuite(TC13.class);
		suite.addTestSuite(TC14.class);
		suite.addTestSuite(TC15.class);
		suite.addTestSuite(TC16.class);
		suite.addTestSuite(TC17.class);
		suite.addTestSuite(TC18.class);
		suite.addTestSuite(TC19.class);
		suite.addTestSuite(TC20.class);
		suite.addTestSuite(TC21.class);
		suite.addTestSuite(TC22.class);		
		suite.addTestSuite(TC23.class);
		suite.addTestSuite(TC24.class);
		suite.addTestSuite(TC25.class);
		suite.addTestSuite(TC26.class);
		suite.addTestSuite(TC27.class);
		suite.addTestSuite(TC28.class);
		suite.addTestSuite(TC29.class);
		//$JUnit-END$
		return suite;
	}

}

