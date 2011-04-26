/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This exception class signals that a method was called which is
 * not implemented.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspNotImplementedException extends AspException
{
    /**
     * Constructor. Non-descriptive, it is suggested to use the
     * constructor with a single string parameter to describe the error.
     */
    public AspNotImplementedException()
    {
        super("Not Implemented");
    }

    /**
     * Constructor.
     *
     * @param desc Description, usually function which is not implemented.
     */
    public AspNotImplementedException(String desc)
    {
        super("Not Implemented: " + desc);
    }
}

