/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.util.Properties;

/**
 * This class defines an interface to exception handlers.
 * @author Terence Haddock
 * @version 0.9
 */
public interface AspExceptionHandler
{
    /**
     * This function configures the exception handler 
     */
    public void configureExceptionHandler(Properties prop)
        throws AspException;
    /**
     * This function is called when an exception occurs.
     * @param ctx Current ASP context
     * @param ex The exception which occured.
     * @return <b>true</b> to continue processing exceptions, <b>false</b> to
     *  stop at this one.
     */
    public boolean onExceptionOccured(AspContext ctx, AspException ex)
        throws AspException;
}

