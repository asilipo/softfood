/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.softfood.aspect;

import it.softfood.entity.Ordinazione;
import org.apache.log4j.Logger;
import org.jboss.aop.joinpoint.MethodInvocation;

/**
 *
 * @author marcograsso
 */
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());
    
    public Object logOrder(MethodInvocation invocation) throws Throwable{
        Object obj=invocation.invokeNext();
        Object[] arguments=invocation.getArguments();
        Ordinazione ordine=(Ordinazione) arguments[0];
        StringBuffer buff = new StringBuffer();
        buff.append("LOGGER DELL'ORDINE ").append(ordine.getId());
        buff.append(" ").append(ordine.getTavolo().getRiferimento()).append(" # POSTI OCCUPATI:").append(ordine.getTavolo().getNumeroPosti());
        logger.trace(buff);
        
        logger.info(buff);
        return obj;
    }

}
