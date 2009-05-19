package it.softfood.login;

import it.softfood.entity.User;
import it.softfood.handler.IUserFacade;
import it.softfood.handler.UserFacade;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Softfood_LoginModule implements LoginModule {

	private Subject subject;
	private CallbackHandler callbackHandler;
	private boolean debug = false;
	private boolean succeeded = false;
	private boolean commitSucceeded = false;
	private String username;
	private char[] password;
	private User hmsUser;

	@SuppressWarnings("unchecked")
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
	}

	public boolean login() throws LoginException {
		if (callbackHandler == null) {
			throw new LoginException("No CallbackHandler defined");
		}
		
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("Username");
		callbacks[1] = new PasswordCallback("Password", false);

		try {
			callbackHandler.handle(callbacks);
			username = ((NameCallback)callbacks[0]).getName();
			char[] tempPassword = ((PasswordCallback)callbacks[1]).getPassword();
			@SuppressWarnings("unused")
			String spw = tempPassword.toString();
			password = new char[tempPassword.length];
			System.arraycopy(tempPassword, 0, password, 0, tempPassword.length);
			((PasswordCallback)callbacks[1]).clearPassword();
		} catch (IOException ioe) {
			throw new LoginException(ioe.toString());
		} catch (UnsupportedCallbackException uce) {
			throw new LoginException(uce.toString());
		}
		
		String passwordString = new String("");
		for(int i=0;i<password.length ; i++){
			passwordString=passwordString.concat(new String(""+password[i]));
		}
		User us = this.controlCredential(username,passwordString);
		if (us != null) {
			hmsUser = us;
			succeeded = true;
			return true;
		} else {
			succeeded = false;
			username = null;
			password = null;
			throw new LoginException("Username e/o password errati.");
		}
	}

	public boolean commit() throws LoginException {
		if (succeeded == false) {
			return false;
		} else {
			if (!subject.getPrincipals().contains(hmsUser))
				subject.getPrincipals().add(hmsUser);
			if (debug) {}
			username = null;
			for (int i = 0; i < password.length; i++)
				password[i] = ' ';
			password = null;
			commitSucceeded = true;
			return true;
		}
	}

	public boolean abort() throws LoginException {
		if (succeeded == false) {
			return false;
		} else if (succeeded == true && commitSucceeded == false) {
			succeeded = false;
			username = null;
			if (password != null) {
				for (int i = 0; i < password.length; i++)
					password[i] = ' ';
				password = null;
			}
			hmsUser = null;
		} else {
			logout();
		}
		return true;
	}

	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(hmsUser);
		succeeded = false;
		succeeded = commitSucceeded;
		username = null;
		if (password != null) {
			for (int i = 0; i < password.length; i++)
				password[i] = ' ';
			password = null;
		}
		hmsUser = null;
		return true;
	}

	private User controlCredential(String username,String password){
		IUserFacade emp=UserFacade.getInstance();
		User employee = null;
		try {
			employee = emp.selezionaUtentePerUserPassword(username, password);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if(employee == null){
			return(null);
		}
		
		return employee;
	}
	
}
