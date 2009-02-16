/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prova;

import org.jboss.aop.joinpoint.Invocation;

/**
 *
 * @author nio
 */

//@Aspect(scope=Scope.PER_VM)

public class MyInter  {

    
    /*public Object prova(InvocationContext ic) throws Exception {
        System.out.println("Prova Interrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        return ic.proceed();
    }*/

    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    //@Bind(pointcut="execution(* it.softfood.session.ordinazione.OrdinazioneSessionBean->inserisciOrdinazione())")
    
     public Object invoke(Invocation arg0) throws Throwable {
        System.out.println("Prova Interrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        return arg0.invokeNext();
    }
}
