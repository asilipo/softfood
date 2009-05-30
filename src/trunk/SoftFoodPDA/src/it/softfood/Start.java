package it.softfood;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.handler.IUserFacade;
import it.softfood.handler.UserFacade;

public class Start {

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 */
	public static void main(String[] args) throws RemoteException, NotBoundException {
		System.setProperty("java.security.policy", "polis.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		// TODO Auto-generated method stub
		Registry registry = LocateRegistry.getRegistry("localhost");
		IUserFacade userfacade= (IUserFacade) registry.lookup("UserFacade");
		UserFacade role = UserFacade.getInstance();
		User u = role.login(role.selezionaUserPerPassword("1234").getUserName(),Ruolo.CAMERIERE, "1234");
		System.out.println(u);

	}

}
