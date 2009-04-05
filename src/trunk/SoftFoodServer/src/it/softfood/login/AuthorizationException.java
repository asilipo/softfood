package it.softfood.login;

public class AuthorizationException extends RuntimeException {
        public AuthorizationException(Exception cause) {  
        super(cause);
    }

}
