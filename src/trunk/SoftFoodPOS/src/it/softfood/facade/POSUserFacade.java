package it.softfood.facade;

import it.softfood.entity.User;
import it.softfood.handler.UserFacade;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class POSUserFacade {
	
	public User login(String user, String password){
		UserFacade role = UserFacade.getInstance();
		User u = role.login(user, password);
		return u;
	}
	
	public void logout(User u){
		UserFacade role = UserFacade.getInstance();
		role.logout(u);
	}

}
