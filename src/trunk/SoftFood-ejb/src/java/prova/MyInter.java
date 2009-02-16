/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prova;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author nio
 */
public class MyInter {

    @AroundInvoke
    public Object prova(InvocationContext ic) throws Exception {
        System.out.println("Prova Interrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        return ic.proceed();
    }
}
