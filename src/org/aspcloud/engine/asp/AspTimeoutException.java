/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This exception is thrown when a script times out.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspTimeoutException extends AspException
{
    /**
     * Constructor.
     */
    public AspTimeoutException()
    {
        super("Script timed out");
    }

    /**
     * Constructor.
     *
     * @param desc Description, not usually used.
     */
    public AspTimeoutException(String desc)
    {
        super("Script timed out: " + desc);
    }
}

