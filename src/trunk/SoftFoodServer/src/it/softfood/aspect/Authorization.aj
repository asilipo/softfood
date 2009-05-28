package it.softfood.aspect;

import it.softfood.entity.User;
import it.softfood.login.AuthorizationException;
import it.softfood.login.LoginHandler;
import it.softfood.util.XmlReader;

import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Hashtable;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;

import org.aspectj.lang.JoinPoint;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public aspect Authorization {

	private String xmlparameter = "InitialConfiguration.xml";

	public Authorization() throws Exception {
		System.out.println("Authorization ");
		XmlReader xml = new XmlReader();
		String file = xml.leggi(xmlparameter);
		System.setProperty("java.security.policy", file);
	}

	pointcut authOperations(User user): execution(* it.softfood.handler.*.*(User,..)) &&
	execution(* it.softfood.handler.*.login(String,String))&&
	!execution(* it.softfood.handler.*.logout(User)) &&
	!execution(* it.softfood.handler.*.getInstance()) &&
	args(user,..);

	@SuppressWarnings("unchecked")
	before(User user) : authOperations(user) {
		System.out.println("before " + user);
		System.out.println("before " +user.getUserName());
		LoginHandler login = LoginHandler.getInstance();
		Hashtable hT = login.getSubjectTable();
		if(hT.containsKey(user)) {
			return;    
		} else {
			System.out.println("AuthorizationException");
			throw new AuthorizationException(new Exception("User Not Logged " + user.getUserName()));
		}	
	}

	public Permission getPermission(JoinPoint.StaticPart joinPointStaticPart) {
		System.out.println("getPermission " );
		return new OperationPermission(joinPointStaticPart.getSignature().getName());
	}

	@SuppressWarnings("unchecked")
	Object around(final User user): authOperations(user) && !cflowbelow(authOperations(User)) {     
		try {
			LoginHandler login = LoginHandler.getInstance();
			Hashtable hT = login.getSubjectTable();
			LoginContext lc = (LoginContext) hT.get(user);
			Subject _authenticatedSubject=lc.getSubject();
			_authenticatedSubject.getPrincipals().iterator().next();
			return Subject.doAsPrivileged(_authenticatedSubject,
					new PrivilegedExceptionAction() {public Object run() throws Exception {return proceed(user); } },
					null); 
		} 
		catch (PrivilegedActionException ex) {
			System.out.println("AuthorizationException");
			throw new AuthorizationException(ex.getException());
		}
	}

	before(User user) : authOperations(user) {    
		AccessController.checkPermission(getPermission(thisJoinPointStaticPart));
		return;    
	}

}