/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This is an abstract interface to use for nodes of which functions
 * can be called.
 * 
 * @author Terence Haddock
 * @version 0.9
 */
public interface FunctionNode extends Node
{
    /**
     * Executes a function with the specified parameters.
     *
     * @param vars Parameters for this function.
     * @param context AspContext under which to evaluate this expression.
     * @return Value of this function
     * @throws AspException is an error occurs.
     */
    public Object execute(VarListNode vars, AspContext context)
        throws AspException;
};

