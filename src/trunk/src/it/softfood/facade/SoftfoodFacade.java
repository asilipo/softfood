package it.softfood.facade;

import it.softfood.entity.Tavolo;
import it.softfood.session.TavoloSession;

import java.util.List;
 
    
public class SoftfoodFacade {  
	 
	 
	private static SoftfoodFacade singleton; 
	
	public List<Tavolo> selezionaTavoliLiberi(){
		
		return new TavoloSession().selezionaTavoliLiberi();
		
	}
	
	
	/*
	 * Singleton Metod
	 */
	public synchronized static SoftfoodFacade getInstance() {
		if (singleton == null) {
			singleton = new SoftfoodFacade();
		}
		return singleton;
	}
	
	private SoftfoodFacade() {
//		TavoloSession tavolo=TavoloSession.getInstance();
	}
	
}
