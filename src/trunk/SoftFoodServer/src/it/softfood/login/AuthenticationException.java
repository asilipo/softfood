package it.softfood.login;

public  class AuthenticationException extends RuntimeException {                          
    
    public AuthenticationException(Exception cause) {   
    super(cause);  
    }                  
}
