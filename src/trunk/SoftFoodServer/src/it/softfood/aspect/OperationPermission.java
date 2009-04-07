package it.softfood.aspect;

import java.security.*;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public final class OperationPermission extends BasicPermission {
	
	private static final long serialVersionUID = 1L;

	public OperationPermission(String name) {
        super(name);
    }
    
    public OperationPermission(String name, String actions) {
        super(name, actions);
    }
    
}