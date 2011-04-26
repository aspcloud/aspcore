/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This exception is thrown if a variable is redefined.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspRedefineIdentException extends AspException
{
    /**
     * Constructor. Non-descriptive, it is suggested to use the
     * constructor with a single string parameter to describe the error.
     */
    public AspRedefineIdentException()
    {
        super("Identifier redefined");
    }

    /**
     * Constructor.
     *
     * @param desc Description, usually number of arguments in function call.
     */
    public AspRedefineIdentException(String desc)
    {
        super("Identifier redefined: " + desc);
    }
}

