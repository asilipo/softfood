package it.softfood.aspect;

import it.softfood.handler.ITavoloFacade;
import it.softfood.handler.IOrdinazioneFacade;
import it.softfood.handler.IRistoranteFacade;
import it.softfood.handler.IArticoloMenuFacade;

import java.rmi.Naming;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public aspect Distribution {

	// private String xmlparameter="config_authorization_file";

	declare parents: it.softfood.handler.TavoloFacade implements ITavoloFacade;
	declare parents: it.softfood.handler.OrdinazioneFacade implements IOrdinazioneFacade;
	declare parents: it.softfood.handler.RistoranteFacade implements IRistoranteFacade;
	declare parents: it.softfood.handler.ArticoloMenuFacade implements IArticoloMenuFacade;

	pointcut initTavolo(ITavoloFacade t): execution(it.softfood.handler.TavoloFacade.new(..)) && this(t);

	after(ITavoloFacade t): initTavolo(t){
		try {
			System.out.println("Distribution Aspect");
			// XmlReader xml= new XmlReader();
			// String file=xml.leggi(xmlparameter);
			System.setProperty("java.security.policy", "authorization.policy");
			System.setProperty("java.security.policy", "authorization.policy");
			if (System.getSecurityManager() == null) {
				System.out.println("Security Manager null....");
				System.setSecurityManager(new SecurityManager());
			}
			System.out.println("Object Exporting....");
			ITavoloFacade tt = (ITavoloFacade) java.rmi.server.UnicastRemoteObject
					.exportObject(t, 0);
			System.out.println("Object exported");
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			System.out.println("Get registry");
			Naming.rebind("TavoloFacade", tt);
			System.out.println("Server created and ready.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	pointcut initOrdine(IOrdinazioneFacade t): execution(it.softfood.handler.OrdinazioneFacade.new(..)) && this(t);

	after(IOrdinazioneFacade t): initOrdine(t){
		try {
			System.out.println("Distribution Aspect");
			// XmlReader xml= new XmlReader();
			// String file=xml.leggi(xmlparameter);
			System.setProperty("java.security.policy", "authorization.policy");
			System.setProperty("java.security.policy", "authorization.policy");
			if (System.getSecurityManager() == null) {
				System.out.println("Security Manager null....");
				System.setSecurityManager(new SecurityManager());
			}
			System.out.println("Object Exporting....");
			IOrdinazioneFacade tt = (IOrdinazioneFacade) java.rmi.server.UnicastRemoteObject
					.exportObject(t, 0);
			System.out.println("Object exported");
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			System.out.println("Get registry");
			Naming.rebind("OrdineFacade", tt);
			System.out.println("Server created and ready.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	pointcut initRistorante(IRistoranteFacade t): execution(it.softfood.handler.RistoranteFacade.new(..)) && this(t);

	after(IRistoranteFacade t): initRistorante(t){
		try {
			System.out.println("Distribution Aspect");
			// XmlReader xml= new XmlReader();
			// String file=xml.leggi(xmlparameter);
			System.setProperty("java.security.policy", "authorization.policy");
			System.setProperty("java.security.policy", "authorization.policy");
			if (System.getSecurityManager() == null) {
				System.out.println("Security Manager null....");
				System.setSecurityManager(new SecurityManager());
			}
			System.out.println("Object Exporting....");
			IRistoranteFacade tt = (IRistoranteFacade) java.rmi.server.UnicastRemoteObject
					.exportObject(t, 0);
			System.out.println("Object exported");
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			System.out.println("Get registry");
			Naming.rebind("RistoranteFacade", tt);
			System.out.println("Server created and ready.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	pointcut initArticolo(IArticoloMenuFacade t): execution(it.softfood.handler.ArticoloMenuFacade.new(..)) && this(t);

	after(IArticoloMenuFacade t): initArticolo(t){
		try {
			System.out.println("Distribution Aspect");
			// XmlReader xml= new XmlReader();
			// String file=xml.leggi(xmlparameter);
			System.setProperty("java.security.policy", "authorization.policy");
			System.setProperty("java.security.policy", "authorization.policy");
			if (System.getSecurityManager() == null) {
				System.out.println("Security Manager null....");
				System.setSecurityManager(new SecurityManager());
			}
			System.out.println("Object Exporting....");
			IArticoloMenuFacade tt = (IArticoloMenuFacade) java.rmi.server.UnicastRemoteObject
					.exportObject(t, 0);
			System.out.println("Object exported");
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			System.out.println("Get registry");
			Naming.rebind("ArticoloFacade", tt);
			System.out.println("Server created and ready.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}