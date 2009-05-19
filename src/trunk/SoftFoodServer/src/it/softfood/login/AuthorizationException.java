package it.softfood.login;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class AuthorizationException extends RuntimeException {
        
	private static final long serialVersionUID = 1L;

	public AuthorizationException(Exception cause) {  
        super(cause);
    }

}
