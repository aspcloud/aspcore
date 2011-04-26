/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * The AspExitForException handles the EXIT FOR statement in VBScript.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspExitForException extends AspExitException
{
    /**
     * Constructor, no arguments.
     */
    public AspExitForException()
    {
        super("EXIT FOR called outside of FOR loop");
    }
};

