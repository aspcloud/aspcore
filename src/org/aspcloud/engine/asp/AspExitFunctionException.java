/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * The AspExitFunctionException handles the EXIT FUNCTION statement in VBScript
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspExitFunctionException extends AspExitException
{
    /**
     * Constructor, no arguments.
     */
    public AspExitFunctionException()
    {
        super("EXIT FUNCTION called outside of function");
    }

};

