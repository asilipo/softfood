package it.softfood.aspect;



import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;

import java.util.Date;
import java.util.Hashtable;
import java.security.Permission;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import it.softfood.entity.User;
import it.softfood.login.AuthorizationException;
import it.softfood.login.LoginHandler;


import org.aspectj.lang.JoinPoint;

import it.softfood.util.XmlReader;


public aspect Authorization{

	private String xmlparameter="config_authorization_file";
	
	public Authorization()throws Exception{
		XmlReader xml= new XmlReader();
	    String file = xml.leggi(xmlparameter);
		System.setProperty("java.security.policy", file);
	}
	
	pointcut authOperations(User user): execution(* it.softfood.handler.*.*(User,..)) &&
    execution(* it.softfood.handler.*.login(String,String)) &&
    !execution(* it.softfood.handler.*.getInstance()) &&
     args(user,..);
    
	before(User user) : authOperations(user) {
		
		LoginHandler login=LoginHandler.getInstance();
		Hashtable hT=login.getSubjectTable();
        if(hT.containsKey(user)){
        	return;    
		}
		else{
			throw new AuthorizationException(new Exception("User Not Logged "+user.getUserName()));
		}	
	}
	
    public Permission getPermission(JoinPoint.StaticPart joinPointStaticPart){
        return new OperationPermission(joinPointStaticPart.getSignature().getName());
    }
     
	  Object around(final User user): authOperations(user) && !cflowbelow(authOperations(User)) {     
		  try {
			  LoginHandler login=LoginHandler.getInstance();
			  Hashtable hT=login.getSubjectTable();
			  LoginContext lc= (LoginContext)hT.get(user);
			  Subject _authenticatedSubject=lc.getSubject();
			  User u=(User)_authenticatedSubject.getPrincipals().iterator().next();
			  return Subject.doAsPrivileged(_authenticatedSubject,
					                       new PrivilegedExceptionAction() {public Object run() throws Exception {return proceed(user); } },
				 	                       null); 
			 } 
		  catch (PrivilegedActionException ex) {                  
		     throw new AuthorizationException(ex.getException());   
		  }
	  }
	  
	  before(User user) : authOperations(user) {    
		  AccessController.checkPermission(getPermission(thisJoinPointStaticPart));
		  return;    
		}
	  
 }