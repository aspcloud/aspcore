/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * The AspExitScriptException class handles the Response.End statement in VBScript
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspExitScriptException extends AspExitException
{
    /**
     * Constructor, no arguments.
     */
    public AspExitScriptException()
    {
        super("EXIT SCRIPT called outside of script");
    }
};

