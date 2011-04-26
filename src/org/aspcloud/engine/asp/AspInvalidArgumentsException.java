/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This exception is thrown if a function is called with an invalid
 * number of arguments.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspInvalidArgumentsException extends AspException
{
    /**
     * Constructor. Non-descriptive, it is suggested to use the
     * constructor with a single string parameter to describe the error.
     */
    public AspInvalidArgumentsException()
    {
        super("Invalid arguments");
    }

    /**
     * Constructor.
     *
     * @param desc Description, usually number of arguments in function call.
     */
    public AspInvalidArgumentsException(String desc)
    {
        super("Invalid arguments: " + desc);
    }
}

