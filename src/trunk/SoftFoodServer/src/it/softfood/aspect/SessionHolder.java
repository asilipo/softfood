package it.softfood.aspect;

import org.hibernate.Session;

/**
 * An interface which has to be implemented
 * by every class which is needed a Hibernate Session for the
 * data access operation.
 * 
 */ 

public interface SessionHolder
{
    public abstract Session getSession();
    
    public abstract void setSession(Session s);

}

