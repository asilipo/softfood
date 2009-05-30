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
		UserFacade role = UserFacade.getInstance();
		System.out.println(role);
		String username = role.selezionaUserPerPassword(password).getUserName();
		User u = role.login(username,ruolo, password);
		return u;
	}
	
	public void logout(User u){
		UserFacade role = UserFacade.getInstance();
		role.logout(u);
	}

}
