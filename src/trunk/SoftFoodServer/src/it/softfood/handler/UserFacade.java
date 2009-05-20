package it.softfood.handler;

import it.softfood.entity.User;
import it.softfood.login.LoginHandler;
import it.softfood.session.UserSession;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class UserFacade {
	
	private static UserFacade singleton; 
	private UserSession userSession = UserSession.getInstance();

	public UserFacade() {}
	
	public synchronized static UserFacade getInstance() {
		if (singleton == null) {
			singleton = new UserFacade();
		}
		
		return singleton;
	}

	public User selezionaUtentePerUserPassword(String username, String password) {
		if (username != null && password != null) {
			User user = userSession.selezionaUtente(username, password);
			return user;
		}
		
		return null;
	}
	
	public User inserisciUtente(User user){		
		if (user != null) {
			user = userSession.inserisciUser(user);
			return user;		
		}
		
		return null;
	}
	
	public User selezionaUserName(User user) {
		if (user != null) {
			user = userSession.selezionaUserPerUserName(user.getUserName());
			return user;
		}
		
		return null;
	}
	
	public User selezionaPassword(User user) {	
		if (user != null) {
			user = userSession.selezionaUserPerPassword(user.getPassword());
			return user;
		}
		
		return null;
	}
	
	public boolean modificaRuolo(User user, String ruolo) {
		if (user != null && ruolo != null) {
			boolean v = userSession.modificaRuoloUser(user, ruolo);
			return v;
		}
		
		return false;
	}
	
	public boolean eliminaUtente(User user) {
		if (user != null)
			return userSession.rimuoviUser(user.getUserName());
		
		return false;
	}
	
	public User selezionaUtente(User user, String username, String password) {
		if (user != null && username != null && password != null)
			return userSession.selezionaUtente(username, password);
		
		return null;
	}
	
	public User login(String ruolo, String password) {
		if (ruolo != null && password != null) {
			UserSession userSession = UserSession.getInstance();
			String username = userSession.selezionaUserPerPassword(password).getUserName();	
			LoginHandler l = LoginHandler.getInstance();
			User user = l.login(username, password);
			
			return user;
		}
		
		return null;
	}
	
	public void logout(User user) {
		if (user != null) 
			LoginHandler.getInstance().logout(user);
	}
	
}
