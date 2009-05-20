package it.softfood.exception;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class UserException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserException(String messaggio) {
		super(messaggio);
	}

}
