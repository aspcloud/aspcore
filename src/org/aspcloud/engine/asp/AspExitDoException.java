/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * The AspExitDoException class handles the EXIT DO statement in VBScript.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspExitDoException extends AspExitException
{
    /**
     * Constructor, no arguments.
     */
    public AspExitDoException()
    {
        super("EXIT DO called outside of DO loop");
    }
};

