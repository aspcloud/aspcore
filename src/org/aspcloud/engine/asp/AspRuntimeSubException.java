/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * Base class for AspExceptions wrapped inside runtime exceptions.
 * 
 * @author Terence Haddock
 * @version 0.9
 */
public class AspRuntimeSubException extends RuntimeException
{
    /** Actual AspException */
    AspException ex = null;

    /** 
     * Constructor with AspException.
     * @param ex Sub-exception
     */
    public AspRuntimeSubException(AspException ex)
    {
        super(ex.toString());
        this.ex = ex;
    }

    /**
     * Obtain the actual exception.
     * @return exception this class is containing.
     */
    public AspException getException()
    {
        return ex;
    }

    /**
     * Obtains the short description of this exception.
     * @return short description of this exception, including
     *    debugging context.
     */
    public String toString()
    {
        return ex.toString();
    }
}
