package it.softfood.aspect;




import it.softfood.handler.ITavoloFacade;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;




public aspect ClientDistribution {
	
	private ITavoloFacade tavolofacade;
	
	
	
	public  ClientDistribution() {
			System.setProperty("java.security.policy","polis.policy");
			if (System.getSecurityManager() == null) {
		            System.setSecurityManager(new SecurityManager());
		     }
			try {
	            Registry registry = LocateRegistry.getRegistry("localhost");
	            tavolofacade = (ITavoloFacade) registry.lookup("TavoloFacade");
	        } 
			catch (Exception e) {
	            System.err.println("Exception to obtain the reference to the remote object");
	            e.printStackTrace();
	        }
			
		
	}
	
	pointcut distributefacadeCalls(): execution(* it.softfood.facade.*.*(..)) && !execution(it.softfood.facade.*.new(..));
	
	
	Object around(): distributefacadeCalls()  {
		Object obj =null;
		  obj = ExecuteMetod.invoke(tavolofacade, thisJoinPoint.getSignature().getName(), thisJoinPoint.getArgs());
		 Object[] params=thisJoinPoint.getArgs();
		 return obj;
	}
	
}
