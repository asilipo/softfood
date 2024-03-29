package it.softfood.aspect;

import it.softfood.handler.IArticoloMenuFacade;
import it.softfood.handler.IOrdinazioneFacade;
import it.softfood.handler.IRistoranteFacade;
import it.softfood.handler.ITavoloFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public aspect Distribution {

	declare parents: it.softfood.handler.TavoloFacade implements ITavoloFacade;
	declare parents: it.softfood.handler.OrdinazioneFacade implements IOrdinazioneFacade;
	declare parents: it.softfood.handler.RistoranteFacade implements IRistoranteFacade;
	declare parents: it.softfood.handler.ArticoloMenuFacade implements IArticoloMenuFacade;
	declare parents: it.softfood.handler.UserFacade implements IUserFacade;

	pointcut initTavolo(ITavoloFacade t): execution(it.softfood.handler.TavoloFacade.new(..)) && this(t);

	after(ITavoloFacade t): initTavolo(t){
		try {
			System.setProperty("java.security.policy", "authorization.policy");
			System.setProperty("java.security.policy", "authorization.policy");
			
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			ITavoloFacade tt = (ITavoloFacade) java.rmi.server.UnicastRemoteObject.exportObject(t, 0);
			@SuppressWarnings("unused")
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			Naming.rebind("TavoloFacade", tt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	pointcut initOrdine(IOrdinazioneFacade t): execution(it.softfood.handler.OrdinazioneFacade.new(..)) && this(t);

	after(IOrdinazioneFacade t): initOrdine(t){
		try {
			System.setProperty("java.security.policy", "authorization.policy");
			System.setProperty("java.security.policy", "authorization.policy");
			if (System.getSecurityManager() == null) {
				System.out.println("Security Manager null....");
				System.setSecurityManager(new SecurityManager());
			}

			IOrdinazioneFacade tt = (IOrdinazioneFacade) java.rmi.server.UnicastRemoteObject.exportObject(t, 0);
			System.out.println("Object exported");
			
			@SuppressWarnings("unused")
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			Naming.rebind("OrdineFacade", tt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	pointcut initRistorante(IRistoranteFacade t): execution(it.softfood.handler.RistoranteFacade.new(..)) && this(t);

	after(IRistoranteFacade t): initRistorante(t){
		try {
			System.setProperty("java.security.policy", "authorization.policy");
			System.setProperty("java.security.policy", "authorization.policy");
			
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			IRistoranteFacade tt = (IRistoranteFacade) java.rmi.server.UnicastRemoteObject.exportObject(t, 0);
			System.out.println("Object exported");
			
			@SuppressWarnings("unused")
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			Naming.rebind("RistoranteFacade", tt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	pointcut initArticolo(IArticoloMenuFacade t): execution(it.softfood.handler.ArticoloMenuFacade.new(..)) && this(t);

	after(IArticoloMenuFacade t): initArticolo(t){
		try {
			System.setProperty("java.security.policy", "authorization.policy");
			System.setProperty("java.security.policy", "authorization.policy");
			
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			IArticoloMenuFacade tt = (IArticoloMenuFacade) java.rmi.server.UnicastRemoteObject.exportObject(t, 0);

			@SuppressWarnings("unused")
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			Naming.rebind("ArticoloFacade", tt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	pointcut initUser(IUserFacade t): execution(it.softfood.handler.UserFacade.new(..)) && this(t);

	after(IUserFacade t): initUser(t){
		try {
			System.setProperty("java.security.policy", "authorization.policy");
			System.setProperty("java.security.policy", "authorization.policy");
			
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			IUserFacade tt = (IUserFacade) java.rmi.server.UnicastRemoteObject.exportObject(t, 0);

			@SuppressWarnings("unused")
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			Naming.rebind("UserFacade", tt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}