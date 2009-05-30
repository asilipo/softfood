package it.softfood.aspect;

import it.softfood.handler.IArticoloMenuFacade;
import it.softfood.handler.IOrdinazioneFacade;
//import it.softfood.handler.IRistoranteFacade;
import it.softfood.handler.ITavoloFacade;
import it.softfood.handler.IUserFacade;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public aspect ClientDistribution {

	private ITavoloFacade tavolofacade;
//	private IRistoranteFacade ristorantefacade;
	private IOrdinazioneFacade ordinazionefacade;
	private IArticoloMenuFacade articolofacade;
	private IUserFacade userfacade;

	public ClientDistribution() {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost" , 1099);
			tavolofacade = (ITavoloFacade) registry.lookup("TavoloFacade");
//			ristorantefacade = (IRistoranteFacade) registry.lookup("RistoranteFacade");
			ordinazionefacade = (IOrdinazioneFacade) registry.lookup("OrdineFacade");
			articolofacade = (IArticoloMenuFacade) registry.lookup("ArticoloFacade");
			userfacade= (IUserFacade) registry.lookup("UserFacade");
		} catch (Exception e) {
			System.err.println("Exception to obtain the reference to the remote object: " + e);
		}
	}

//	pointcut distributeRistoranteFacadeCalls(): execution(* it.softfood.facade.PDARistoranteFacade.*(..)) && !execution(it.softfood.facade.PDARistoranteFacade.new(..));
//
//	Object around(): distributeRistoranteFacadeCalls()  {
//		Object obj = null;
//		obj = ExecuteMethod.invoke(ristorantefacade, thisJoinPoint.getSignature()
//				.getName(), thisJoinPoint.getArgs());
//		return obj;
//	}
	
	pointcut distributeTavoloFacadeCalls(): execution(* it.softfood.facade.PDATavoloFacade.*(..)) && !execution(it.softfood.facade.PDATavoloFacade.new(..));

	Object around(): distributeTavoloFacadeCalls()  {
		Object obj = null;
		obj = ExecuteMethod.invoke(tavolofacade, thisJoinPoint.getSignature()
				.getName(), thisJoinPoint.getArgs());
		return obj;
	}
	
	pointcut distributeOrdinazioneFacadeCalls(): execution(* it.softfood.facade.PDAOrdinazioneFacade.*(..)) && !execution(it.softfood.facade.PDAOrdinazioneFacade.new(..));

	Object around(): distributeOrdinazioneFacadeCalls()  {
		Object obj = null;
		obj = ExecuteMethod.invoke(ordinazionefacade, thisJoinPoint.getSignature()
				.getName(), thisJoinPoint.getArgs());

		return obj;
	}
	
	pointcut distributeArticoloFacadeCalls(): execution(* it.softfood.facade.PDAArticoloMenuFacade.*(..)) && !execution(it.softfood.facade.PDAArticoloMenuFacade.new(..));

	Object around(): distributeArticoloFacadeCalls()  {
		Object obj = null;
		obj = ExecuteMethod.invoke(articolofacade, thisJoinPoint.getSignature()
				.getName(), thisJoinPoint.getArgs());
		@SuppressWarnings("unused")
		Object[] params = thisJoinPoint.getArgs();
		return obj;
	}
	
	pointcut distributeUserFacadeCalls(): execution(* it.softfood.facade.PDAUserFacade.*(..)) && !execution(it.softfood.facade.PDAUserFacade.new(..));

	Object around(): distributeUserFacadeCalls()  {
		Object obj = null;
		obj = ExecuteMethod.invoke(userfacade, thisJoinPoint.getSignature()
				.getName(), thisJoinPoint.getArgs());
		@SuppressWarnings("unused")
		Object[] params = thisJoinPoint.getArgs();
		return obj;
	}

}

