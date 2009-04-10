package it.softfood.aspect;

import it.softfood.util.XmlReader;

import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.aspectj.lang.Signature;
   
/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public aspect Logging {      
	     
	private Logger _logger = Logger.getLogger("trace");	
	private final String xmlparameter="fileLog";
	 
	public Logging() {
		XmlReader xml= new XmlReader();
		String file=xml.leggi(xmlparameter);
		SimpleLayout layout = new SimpleLayout();
	    FileAppender appender = null;
	    try {
	         appender = new FileAppender(layout,file,false);
	    } catch(Exception e) {
	    	  e.printStackTrace();
	    }

	    _logger.addAppender(appender);
	    _logger.setLevel(Level.ALL); 
	    
	}

	
	
	pointcut traceLogin(): 	execution(* itsoftfood.session.*.*(..));

	before(): traceLogin() {
		if (_logger.isEnabledFor(Level.INFO)) {
		    Signature sig = thisJoinPointStaticPart.getSignature();
		    _logger.log(Level.INFO,"Execution ["+ sig.getDeclaringType().getName() 
		    		                           + "."+ sig.getName() + "]. By:"+
		    		                           ". Date+"+new Date().toString());
		}
	  }
	
	pointcut traceMethods(): execution(* it.softfood.session.*.*(..)) && !execution(it.softfood.session.*.new(..));
	
	before(): traceMethods() {
		if (_logger.isEnabledFor(Level.INFO)) {
		    Signature sig = thisJoinPointStaticPart.getSignature();
		    _logger.log(Level.INFO,"Execution ["+ sig.getDeclaringType().getName() 
		    		                           + "."+ sig.getName() + "]. By:"+
		    		                           ". Date+"+new Date().toString());
		}
	}
}