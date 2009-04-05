package it.softfood.login;

/*
 * @(#)SampleLoginModule.java	1.18 00/01/11
 *
 * Copyright 2000-2002 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * -Redistributions of source code must retain the above copyright  
 * notice, this  list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduct the above copyright 
 * notice, this list of conditions and the following disclaimer in 
 * the documentation and/or other materials provided with the 
 * distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of 
 * contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any 
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND 
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY 
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY 
 * DAMAGES OR LIABILITIES  SUFFERED BY LICENSEE AS A RESULT OF  OR 
 * RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THE SOFTWARE OR 
 * ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE 
 * FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, 
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER 
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF 
 * THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that Software is not designed, licensed or 
 * intended for use in the design, construction, operation or 
 * maintenance of any nuclear facility. 
 */



import it.softfood.handler.UserFacade;

import java.security.Principal;
import java.util.*;
import java.io.IOException;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import javax.security.auth.spi.*;

import it.softfood.entity.User;


/**
 * <p> This sample LoginModule authenticates users with a password.
 * 
 * <p> This LoginModule only recognizes one user:	testUser
 * <p> testUser's password is:	testPassword
 *
 * <p> If testUser successfully authenticates itself,
 * a <code>SamplePrincipal</code> with the testUser's user name
 * is added to the Subject.
 *
 * <p> This LoginModule recognizes the debug option.
 * If set to true in the login Configuration,
 * debug messages will be output to the output stream, System.out.
 *
 * @version 1.18, 01/11/00
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

   /**
    *  This metod initialize the LogingModule
    */
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options) {
 
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		//debug = "true".equalsIgnoreCase((String)options.get("debug"));
    }

    /**
     * (non-Javadoc)
     * @see javax.security.auth.spi.LoginModule#login()
     */
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
    	      String spw=tempPassword.toString();
    	      password = new char[tempPassword.length];
    	      System.arraycopy(tempPassword, 0, password, 0, tempPassword.length);
    	      ((PasswordCallback)callbacks[1]).clearPassword();
    	      } 
    	    catch (IOException ioe) {
    	    	throw new LoginException(ioe.toString());
    	    } 
    	    catch (UnsupportedCallbackException uce) {
    	    	throw new LoginException(uce.toString());
    	    }
    	    String passwordString=new String("");
    		for(int i=0;i<password.length ; i++){
    			passwordString=passwordString.concat(new String(""+password[i]));
    		}
    		User us=this.controlCredential(username,passwordString);
    	    if (us!=null) {
    	    	hmsUser=us;
    	    	succeeded = true;
    	    	System.out.println("UTENTE ACCETTATO!");
    	    	return true;
    	    } else {
    	      succeeded = false;
    	      username = null;
    	      password = null;
    	      throw new LoginException("Username e/o password errati.");
    	    }
    	  }
 

    /**
     * (non-Javadoc)
     * @see javax.security.auth.spi.LoginModule#commit()
     */
    public boolean commit() throws LoginException {
		if (succeeded == false) {
		    return false;
		} 
		else {
		    if (!subject.getPrincipals().contains(hmsUser))
		    						subject.getPrincipals().add(hmsUser);
		    if (debug) {
		    }
		    username = null;
		    for (int i = 0; i < password.length; i++)
			password[i] = ' ';
		    password = null;
		    commitSucceeded = true;
		    return true;
	   }
    }

    /**
     * 
     */
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

    /**
     * (non-Javadoc)
     * @see javax.security.auth.spi.LoginModule#logout()
     */
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
    
  
    /**
     * This metod return an HMS_User if username ad password identify an
     * HMS Employee
     * 
     * @param username
     * @param password
     */
    private User controlCredential(String username,String password){
		UserFacade emp=UserFacade.getInstance();
		User employee=emp.findByUsernamePassword(username, password);
		if(employee==null){
			return(null);
		}
//		Hotelfunctionalarea hfa=employee.getHotelfunctionalarea();
//		String ruolo=hfa.getName();
//		HMS_User u= new HMS_User(ruolo,employee.getUsername(),employee.getTaxcode(),ruolo);
		return employee;
	}
}
