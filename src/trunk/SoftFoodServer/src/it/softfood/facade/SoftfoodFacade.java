package it.softfood.facade;

import it.softfood.aspect.ISoftfoodFacade;
import it.softfood.entity.Tavolo;
import it.softfood.session.TavoloSession;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
 
    
public class SoftfoodFacade implements Serializable{  
	 
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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