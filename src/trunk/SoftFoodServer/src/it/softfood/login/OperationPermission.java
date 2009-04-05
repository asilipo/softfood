package it.softfood.login;

import java.security.*;

public final class OperationPermission extends BasicPermission {
       
    public OperationPermission(String name) {
        super(name);
    }
   
    public OperationPermission(String name, String actions) {
        super(name, actions);
    }
}

