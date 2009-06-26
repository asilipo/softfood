package it.softfood.aspect;

import it.softfood.handler.IArticoloMenuFacade;
import it.softfood.handler.IOrdinazioneFacade;
//import it.softfood.handler.IRistoranteFacade;
//import it.softfood.handler.ITavoloFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public aspect ClientDistribution {

	private IOrdinazioneFacade ordinazionefacade;
	private IArticoloMenuFacade articolofacade;
	private IUserFacade userfacade;

	public ClientDistribution() {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			ordinazionefacade = (IOrdinazioneFacade) registry.lookup("OrdineFacade");
			articolofacade = (IArticoloMenuFacade) registry.lookup("ArticoloFacade");
			userfacade= (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
	}

	pointcut distributeOrdinazioneFacadeCalls(): execution(* it.softfood.facade.POSOrdinazioneFacade.*(..)) && !execution(it.softfood.facade.POSOrdinazioneFacade.new(..));

	Object around(): distributeOrdinazioneFacadeCalls()  {
		Object obj = null;

		obj = ExecuteMetod.invoke(ordinazionefacade, thisJoinPoint.getSignature()
				.getName(), thisJoinPoint.getArgs());

		@SuppressWarnings("unused")
		Object[] params = thisJoinPoint.getArgs();
		
		return obj;
	}

	pointcut distributeArticoloFacadeCalls(): execution(* it.softfood.facade.POSArticoloMenuFacade.*(..)) && !execution(it.softfood.facade.POSArticoloMenuFacade.new(..));

	Object around(): distributeArticoloFacadeCalls()  {
		Object obj = null;

		obj = ExecuteMetod.invoke(articolofacade, thisJoinPoint.getSignature()
				.getName(), thisJoinPoint.getArgs());

		@SuppressWarnings("unused")
		Object[] params = thisJoinPoint.getArgs();
		
		return obj;
	}

	pointcut distributeUserFacadeCalls(): execution(* it.softfood.facade.POSUserFacade.*(..)) && !execution(it.softfood.facade.POSUserFacade.new(..));

	Object around(): distributeUserFacadeCalls()  {
		Object obj = null;

		obj = ExecuteMetod.invoke(userfacade, thisJoinPoint.getSignature()
				.getName(), thisJoinPoint.getArgs());

		@SuppressWarnings("unused")
		Object[] params = thisJoinPoint.getArgs();
		
		return obj;
	}

}

