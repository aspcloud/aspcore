/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This exception signals that an invalid cast has occurred.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspCastException extends AspException
{
    /**
     * Constructor. Non-descriptive, it is suggested to use the
     * constructor with a single string parameter to describe the error.
     */
    public AspCastException()
    {
        super("Cast Exception");
    }

    /**
     * Constructor.
     *
     * @param desc Description, usually type of data which could not be casted.
     */
    public AspCastException(String desc)
    {
        super("Cast Exception: " + desc);
    }
}

