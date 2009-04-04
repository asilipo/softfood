package it.softfood.aspect;

import it.softfood.handler.IOrdinazioneFacade;
import it.softfood.handler.IRistoranteFacade;
import it.softfood.handler.ITavoloFacade;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public aspect ClientDistribution {

	private ITavoloFacade tavolofacade;
	private IRistoranteFacade ristorantefacade;
	private IOrdinazioneFacade ordinazionefacade;

	public ClientDistribution() {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			tavolofacade = (ITavoloFacade) registry.lookup("TavoloFacade");
			ristorantefacade = (IRistoranteFacade) registry.lookup("RistoranteFacade");
			ordinazionefacade = (IOrdinazioneFacade) registry.lookup("OrdineFacade");
		} catch (Exception e) {
			System.err
					.println("Exception to obtain the reference to the remote object");
			e.printStackTrace();
		}

	}

	pointcut distributeRistoranteFacadeCalls(): execution(* it.softfood.facade.PDARistoranteFacade.*(..)) && !execution(it.softfood.facade.PDARistoranteFacade.new(..));

	Object around(): distributeRistoranteFacadeCalls()  {
		Object obj = null;
		obj = ExecuteMetod.invoke(ristorantefacade, thisJoinPoint.getSignature()
				.getName(), thisJoinPoint.getArgs());
		Object[] params = thisJoinPoint.getArgs();
		return obj;
	}
	
	pointcut distributeTavoloFacadeCalls(): execution(* it.softfood.facade.PDATavoloFacade.*(..)) && !execution(it.softfood.facade.PDATavoloFacade.new(..));

	Object around(): distributeTavoloFacadeCalls()  {
		Object obj = null;
		obj = ExecuteMetod.invoke(tavolofacade, thisJoinPoint.getSignature()
				.getName(), thisJoinPoint.getArgs());
		Object[] params = thisJoinPoint.getArgs();
		return obj;
	}
	
	pointcut distributeOrdinazioneFacadeCalls(): execution(* it.softfood.facade.PDAOrdinazioneFacade.*(..)) && !execution(it.softfood.facade.PDAOrdinazioneFacade.new(..));

	Object around(): distributeOrdinazioneFacadeCalls()  {
		Object obj = null;
		obj = ExecuteMetod.invoke(ordinazionefacade, thisJoinPoint.getSignature()
				.getName(), thisJoinPoint.getArgs());
		Object[] params = thisJoinPoint.getArgs();
		return obj;
	}

}

