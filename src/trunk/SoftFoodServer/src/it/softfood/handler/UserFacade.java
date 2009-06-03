package it.softfood.handler;

import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;
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

	public UserFacade() {
	}

	public synchronized static UserFacade getInstance() {
		if (singleton == null) {
			singleton = new UserFacade();
		}
		return singleton;
	}

	public User selezionaUtente(String username, String password, Ruolo ruolo) {
		if (username != null && password != null) {
			User user = userSession.selezionaUtente1(username, password, ruolo.toString());
			return user;
		}

		return null;
	}

	public User selezionaUtentePerUserPassword(String username, String password) {
		if (username != null && password != null) {
			User user = userSession.selezionaUtente(username, password);
			return user;
		}

		return null;
	}

	public User inserisciUtente(User user) {
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

	public boolean modificaRuolo(User user, Ruolo ruolo) {
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

	public User login(Ruolo ruolo, String password) {
		try {
			if (ruolo != null && password != null && !password.equals(" ")) {
				User user = userSession.selezionaUserPerPassword(password);
				user=userSession.selezionaUtente1(user.getUserName(), password, ruolo.toString());
				String userName = user.getUserName();
				
				LoginHandler l = LoginHandler.getInstance();
				user = l.login(userName, password);

				return user;
			}
		} catch (Exception e) {
			return null;
		}

		return null;
	}
	
	public User loginUser(User user) {
		try {
			if (user != null && user.getName() != null && user.getPassword() != null 
					&& user.getRuolo() != null && !user.getPassword().equals(" ") &&
						!user.getName().equals(" ")) {				
				LoginHandler l = LoginHandler.getInstance();
				user = l.login(user.getName(), user.getPassword());

				return user;
			}
		} catch (Exception e) {
			return null;
		}

		return null;
	}

	public void logout(User user) {
		if (user != null) {
			LoginHandler l = LoginHandler.getInstance();
			l.logout(user);
		}
		return;
	}

}
