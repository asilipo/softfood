package it.softfood.handler;

import it.softfood.entity.User;
import it.softfood.session.UserSession;


public class UserFacade {
	
	private static UserFacade singleton; 
	
	private UserSession user=UserSession.getInstance();

	public UserFacade() {
	}
	
	public synchronized static UserFacade getInstance() {
		if (singleton == null) {
			singleton = new UserFacade();
		}
		
		return singleton;
	}

	public User findByUsernamePassword(String username, String password) {
		User u = user.selezionaUtente(username,password);
		return u;
	}

}
