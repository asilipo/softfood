package it.softfood.aspect;



import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;




public aspect ClientDistribution {
	
	private ISoftfoodFacade facade;
	
	
	
	public  ClientDistribution() {
			System.setProperty("java.security.policy","polis.policy");
			if (System.getSecurityManager() == null) {
		            System.setSecurityManager(new SecurityManager());
		     }
			try {
	            Registry registry = LocateRegistry.getRegistry("localhost");
	            facade = (ISoftfoodFacade) registry.lookup("softfood");
	        } 
			catch (Exception e) {
	            System.err.println("Exception to obtain the reference to the remote object");
	            e.printStackTrace();
	        }
			
		
	}
	
	pointcut distributefacadeCalls(): execution(* it.softfood.aspect.ExecuteFacade.*(..)) && !execution(it.softfood.aspect.ExecuteFacade.new(..));
	
	
	Object around(): distributefacadeCalls()  {
		Object obj =null;
		  obj = ExecuteMetod.invoke(facade, thisJoinPoint.getSignature().getName(), thisJoinPoint.getArgs());
		 Object[] params=thisJoinPoint.getArgs();
		 return obj;
	}
	
}
