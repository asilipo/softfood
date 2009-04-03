package it.softfood.aspect;

import org.apache.log4j.*;
import org.aspectj.lang.*;
import java.util.Date;
import it.softfood.util.XmlReader;
import it.softfood.facade.*;
   
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

	//pointcut traceMethods(User user): execution(* it.softfood.session.*.*(..)) && !execution(it.softfood.session.*.new(..));
	
	pointcut traceMethods() : 	
		execution(* it.softfood.session.*.*(..)) && !execution(it.softfood.session.*.new(..));
	
	pointcut traceLogin() : 	execution(* itsoftfood.session.*.*(..));

	before() : traceLogin() {
		if (_logger.isEnabledFor(Level.INFO)) {
		    Signature sig = thisJoinPointStaticPart.getSignature();
		    _logger.log(Level.INFO,"Execution ["+ sig.getDeclaringType().getName() 
		    		                           + "."+ sig.getName() + "]. By:"+
		    		                           ". Date+"+new Date().toString());
		}
	  }
	
	before() : traceMethods() {
		if (_logger.isEnabledFor(Level.INFO)) {
		    Signature sig = thisJoinPointStaticPart.getSignature();
		    _logger.log(Level.INFO,"Execution ["+ sig.getDeclaringType().getName() 
		    		                           + "."+ sig.getName() + "]. By:"+
		    		                           ". Date+"+new Date().toString());
		}
	  }
	}