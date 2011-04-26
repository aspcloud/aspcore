/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * The AspExitException class is a base class for the AspExit*Exception classes
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspExitException extends AspException
{
    /**
     * Constructor, no arguments.
     */
    protected AspExitException(String str)
    {
        super(str);
    }
};

