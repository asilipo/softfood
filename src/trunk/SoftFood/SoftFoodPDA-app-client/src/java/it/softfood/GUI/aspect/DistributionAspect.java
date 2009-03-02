/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.softfood.GUI.aspect;

import java.util.Hashtable;
import javax.naming.Context;
import org.jboss.aop.joinpoint.MethodInvocation;

/**
 *
 * @author marcograsso
 */
public class DistributionAspect {

    public Object setInitialContext(MethodInvocation invocation) throws Throwable {
        System.out.println("PPPPPRRRRRROOOOOVVVVVVAAAAAAAAA ASPETTO");
        Hashtable hash = new Hashtable();
        hash.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        hash.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        hash.put(Context.PROVIDER_URL, "jnp://192.168.0.1:1099");
        
        Object[] arguments=invocation.getArguments();
        arguments[0]=hash;
        
        invocation.setArguments(arguments);
        
        return invocation.invokeNext();
    }
}
