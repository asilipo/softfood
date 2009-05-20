package it.softfood.facade;

import it.softfood.entity.User;
import it.softfood.handler.UserFacade;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class PDAUserFacade {
	
	public User login(String ruolo, String password) {
		UserFacade role = UserFacade.getInstance();
		User u = role.login(ruolo, password);

		return u;
	}
	
	public void logout(User u){
		UserFacade role = UserFacade.getInstance();
		role.logout(u);
	}

}
