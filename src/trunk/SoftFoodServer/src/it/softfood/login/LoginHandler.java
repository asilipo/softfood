package it.softfood.login;

import it.softfood.entity.User;
import it.softfood.util.XmlReader;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class LoginHandler {

	private static LoginHandler singleton;
	private R_CallbackHandler mcall = null;
	@SuppressWarnings("unchecked")
	private Hashtable subjectTable = null;
	Subject sub;
	private String xmlparameter = "authorization_file";

	@SuppressWarnings("unchecked")
	private LoginHandler() {
		XmlReader xml = new XmlReader();
		String file = xml.leggi(xmlparameter);
		System.setProperty("java.security.auth.login.config", file);
		subjectTable = new Hashtable();
		mcall = new R_CallbackHandler();
	}

	public synchronized static LoginHandler getInstance() {
		if (singleton == null) {
			singleton = new LoginHandler();
		}
		return singleton;
	}

	@SuppressWarnings("unchecked")
	public User login(String username, String password) {
		try {
			this.ceeckJustLogged(username);
			LoginContext loginContex;
			loginContex = new LoginContext("Jaas", mcall);
			mcall.setUserPwd(username, password);
			loginContex.login();
			Subject _authenticatedSubject = loginContex.getSubject();
			User principal = this.getPrincipal(username, _authenticatedSubject);
			subjectTable.put(principal.getUserName(), loginContex);
			return (principal);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public boolean logout(User user) {
		try {
			User u = check(user);
			LoginContext lc = (LoginContext) subjectTable.remove(u.getUserName());
			lc.logout();

			return true;
		} catch (Exception e) {
			System.err.println("LoginHandler#logout");
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private User check(User user) {
		String user_name = user.getUserName();
		Enumeration e = subjectTable.keys();
		String u;
		while (e.hasMoreElements()) {
			u = (String) e.nextElement();
			if (u.equals(user_name))
				return user;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private User getPrincipal(String user, Subject _authenticatedSubject) {
		Set s = _authenticatedSubject.getPrincipals();
		Iterator it = s.iterator();
		
		while (it.hasNext()) {
			User principal = (User) it.next();
			if (principal.getUserName().equals(user)) {
				return (principal);
			}
		}
		
		return (null);
	}

	@SuppressWarnings("unchecked")
	private void ceeckJustLogged(String user) throws LoginException {
		Enumeration e = subjectTable.keys();
		String u;
		while (e.hasMoreElements()) {
			u = (String) e.nextElement();
			if (u.equals(user))
				throw new LoginException("User just Present");
		}
		return;

	}

	@SuppressWarnings("unchecked")
	public Hashtable getSubjectTable() {
		return subjectTable;
	}

	@SuppressWarnings("unchecked")
	public void setSubjectTable(Hashtable subjectTable) {
		this.subjectTable = subjectTable;
	}
	
}
