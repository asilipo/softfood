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
	
	private UserSession user = UserSession.getInstance();

	public UserFacade() {
	}
	
	public synchronized static UserFacade getInstance() {
		if (singleton == null) {
			singleton = new UserFacade();
		}
		
		return singleton;
	}

	public User selezionaUtentePerUserPassword(String username, String password) {
		User u = user.selezionaUtente(username,password);
		return u;
	}
	
	public User inserisciUtente(User role, User u){		
		u=user.inserisciUser(u);
		return u;		
	}
	
	public User selezionaUserName(User role, User u) {		
		u = user.selezionaUserPerUserName(u.getUserName());
		return u;
	}
	
	public User selezionaPassword(User role, User u) {		
		u = user.selezionaUserPerPassword(u.getPassword());
		return u;
	}
	
	public boolean modificaRuolo(User role, User u, String ruolo) {
		boolean v = user.modificaRuoloUser(u, ruolo);
		return v;
	}
	
	public boolean eliminaUtente(User role, User r) {
		return user.rimuoviUser(r.getUserName());
	}
	
	public User selezionaUtente(User role, String username, String password) {
		return user.selezionaUtente(username,password);
	}
	
	public User login(String username, String password) {
		LoginHandler l = LoginHandler.getInstance();
		User u = l.login(username, password);
		return u;
	}
	
	public void logout(User u) {
		LoginHandler.getInstance().logout(u);
	}
	
}
