package it.softfood.login;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public  class AuthenticationException extends RuntimeException {                          
    
	private static final long serialVersionUID = 1L;

	public AuthenticationException(Exception cause) {   
		super(cause);  
    }      
	
}
