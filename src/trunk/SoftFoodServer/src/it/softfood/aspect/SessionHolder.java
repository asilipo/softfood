package it.softfood.aspect;

import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public interface SessionHolder {
	
    public abstract Session getSession();
    
    public abstract void setSession(Session s);

}

