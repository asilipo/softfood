package it.softfood.facade;

import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
import it.softfood.handler.UserFacade;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class PDAUserFacade {
	
	public User login(Ruolo ruolo, String password) {
		System.out.println("ciao1" );
		UserFacade role = UserFacade.getInstance();
		System.out.println("ciao2");
		System.out.println(role);
		User u = role.login(ruolo, password);
		return u;
	}
	
	public void logout(User u){
		UserFacade role = UserFacade.getInstance();
		role.logout(u);
	}

}
