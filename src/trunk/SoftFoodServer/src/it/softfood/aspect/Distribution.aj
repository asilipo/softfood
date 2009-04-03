package it.softfood.aspect;


import it.softfood.handler.ITavoloFacade;

import java.rmi.Naming;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



public aspect Distribution{

//	private String xmlparameter="config_authorization_file";

	declare parents: it.softfood.handler.TavoloFacade implements ITavoloFacade;
	
	   

	pointcut init(it.softfood.handler.TavoloFacade t): execution(it.softfood.handler.TavoloFacade.new(..)) && this(t);
	
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
			ITavoloFacade tt=(ITavoloFacade)java.rmi.server.UnicastRemoteObject.exportObject(t,0);
			System.out.println("Object exported");
			Registry registry =LocateRegistry.getRegistry("localhost",1099);
			System.out.println("Get registry");
			Naming.rebind("TavoloFacade", tt);
			System.out.println("Server created and ready.");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	
}