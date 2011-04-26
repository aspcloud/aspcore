/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This exception class signals that some function was called with
 * the subscript out of range.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspSubscriptOutOfRangeException extends AspException
{
    /**
     * Constructor. Non-descriptive, it is suggested to use the
     * constructor with a single string parameter to describe the error.
     */
    public AspSubscriptOutOfRangeException()
    {
        super("Subscript out of range");
    }

    /**
     * Constructor.
     *
     * @param desc Description, usually function was called.
     */
    public AspSubscriptOutOfRangeException(String desc)
    {
        super("Subscript out of range: " + desc);
    }
}

