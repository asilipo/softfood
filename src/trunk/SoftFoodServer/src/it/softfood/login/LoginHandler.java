package it.softfood.login;

import it.softfood.entity.User;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.security.auth.callback.CallbackHandler;

import it.softfood.util.XmlReader;

public class LoginHandler {

	private static LoginHandler singleton;
	private R_CallbackHandler mcall = null;
	private Hashtable subjectTable = null;
	Subject sub;
	private String xmlparameter = "authorization_file";

	/**
	 * Allocates a LoginHandler object as a Singleton object
	 */
	private LoginHandler() {
		XmlReader xml = new XmlReader();
		String file = xml.leggi(xmlparameter);
		System.setProperty("java.security.auth.login.config", file);
		subjectTable = new Hashtable();
		mcall = new R_CallbackHandler();
	}

	/**
	 * Returns LoginHandler instance if just exists or a new instance
	 */
	public synchronized static LoginHandler getInstance() {
		if (singleton == null) {
			singleton = new LoginHandler();
		}
		return singleton;
	}

	/**
	 * Return an HMS_User object as result of the control for an HMS user
	 * credentials
	 * 
	 * @param user
	 * @param password
	 */
	public User login(String user, String password) {
		try {
			this.ceeckJustLogged(user);
			LoginContext loginContex;
			loginContex = new LoginContext("HMS_Jaas", mcall);
			mcall.setUserPwd(user, password);
			loginContex.login();
			Subject _authenticatedSubject = loginContex.getSubject();
			User principal = this.getPrincipal(user, _authenticatedSubject);
			subjectTable.put(principal, loginContex);
			return (principal);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Returns true if and only if the logout to the system from an HMS user is
	 * well accomplished.
	 * 
	 * @param user
	 */

	public boolean logout(User user) {
		try {
			LoginContext lc = (LoginContext) subjectTable.remove(user);
			lc.logout();
			return true;
		} catch (Exception e) {
			System.err.println("Error in logout operation");
			// e.printStackTrace();
			return false;
		}

	}

	/**
	 * Returns an HMS_User object registrated in to the HMS system or null if it
	 * does not exist
	 * 
	 * @param user
	 * @param _authenticatedSubject
	 */
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

	private void ceeckJustLogged(String user) throws LoginException {
		// TODO Auto-generated method stub
		Enumeration e = subjectTable.keys();
		User u;
		while (e.hasMoreElements()) {
			u = (User) e.nextElement();
			if (u.getUserName().equals(user))
				throw new LoginException("User just Present");
		}
		return;

	}

	public Hashtable getSubjectTable() {
		return subjectTable;
	}

	public void setSubjectTable(Hashtable subjectTable) {
		this.subjectTable = subjectTable;
	}
}
