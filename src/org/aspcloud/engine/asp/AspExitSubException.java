/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * The ExitSubNode class handles the EXIT SUB statement in VBScript
 */
public class AspExitSubException extends AspExitException
{
    /**
     * Constructor, no arguments    
     */
    public AspExitSubException()
    {
        super("EXIT SUB called outside of subroutine");
    }
};

