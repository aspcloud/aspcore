/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This exception is throws if the arguments to a function are outside
 * the legal range of values.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspOverflowException extends AspException
{
    /**
     * Constructor. Non-descriptive, it is suggested to use the
     * constructor with a single string parameter to describe the error.
     */
    public AspOverflowException()
    {
        super("Overflow");
    }

    /**
     * Constructor.
     *
     * @param desc Description, usually integer value which caused the overflow.
     */
    public AspOverflowException(String desc)
    {
        super("Overflow: " + desc);
    }
}

