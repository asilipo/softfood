package it.softfood.login;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;


public class R_CallbackHandler implements CallbackHandler{

        private String user,pass;

        /**
         * @see javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.callback.Callback[])
         */
        public void handle(Callback[] callbacks) throws IOException,
                        UnsupportedCallbackException {
                for (int i = 0; i < callbacks.length; i++) {
                         if (callbacks[i] instanceof NameCallback) {
                             NameCallback nc = (NameCallback)callbacks[i];
                             nc.setName(user);
                              } 
                         else if (callbacks[i] instanceof PasswordCallback) {
                                 
                             PasswordCallback pc = (PasswordCallback)callbacks[i];
                             char []paswd=new char[pass.length()];
                                        for(int ii=0;ii<pass.length();ii++)
                                                paswd[ii]=pass.charAt(ii);
                                        
                             pc.setPassword(paswd);
                              } 
                         else {
                                throw new UnsupportedCallbackException(callbacks[i], "Callback di tipo sconosciuto.");
                              }
                }
                this.user=null;
        this.pass=null;
        }
        
        /**
         * This is the costructor
         */
    public R_CallbackHandler(){
        this.user=null;
        this.pass=null;
    }
    
    /**
     * This metod permits to set username and password that are passed to the LoginModule
     * 
     * @param user
     * @param pwd
     */
    public void setUserPwd(String user, String pwd){
        this.user=user;
        this.pass=pwd;
    }
        
}
