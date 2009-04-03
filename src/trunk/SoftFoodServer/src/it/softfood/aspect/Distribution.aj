package it.softfood.aspect;


import it.softfood.facade.ISoftfoodFacade;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.Principal;


public aspect Distribution{

//	private String xmlparameter="config_authorization_file";

	declare parents: it.softfood.handler.TavoloFacade implements it.softfood.handler.ITavoloFacade;
	
	   

	pointcut init(it.softfood.handler.TavoloFacade t): execution( it.softfood.handler.TavoloFacade.new(..)) && this(t);
	
	after(it.softfood.handler.TavoloFacade t): init(t){
		try{
			System.out.println("Distribution Aspect");
//			XmlReader xml= new XmlReader();
//		    String file=xml.leggi(xmlparameter);
			System.setProperty("java.security.policy","authorization.policy");
			System.setProperty("java.security.policy","authorization.policy");
	 		if (System.getSecurityManager() == null) {
	 			System.out.println("Security Manager null....");
			    System.setSecurityManager(new SecurityManager());
			}
			System.out.println("Object Exporting....");
			ISoftfoodFacade ff=(ISoftfoodFacade)java.rmi.server.UnicastRemoteObject.exportObject(t,0);
			System.out.println("Object exported");
			Registry registry =LocateRegistry.getRegistry("localhost",1099);
			System.out.println("Get registry");
			Naming.rebind("softfood", t);
			System.out.println("Server created and ready.");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	
}