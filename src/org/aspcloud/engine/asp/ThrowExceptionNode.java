/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * The ThrowExceptionNode handles nodes which automatically throw exceptions.
 * This includes Exit nodes.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class ThrowExceptionNode extends DefaultNode
{
    /** Exception to throw when this block is executed. */
    AspException exception;

    /**
     * Constructor, gives exception to throw.
     * @param exception Exception to throw when this block is executed.
     */
    public ThrowExceptionNode(AspException exception)
    {
        this.exception = exception;
    }

    /**
     * Dumps this VBScript element.
     * @see Node#dump()
     */
    public void dump() throws AspException
    {
        if (exception instanceof AspExitForException)
        {
            System.out.println("EXIT FOR");
        } else if (exception instanceof AspExitFunctionException)
        {
            System.out.println("EXIT FUNCTION");
        } else if (exception instanceof AspExitSubException)
        {
            System.out.println("EXIT SUB");
        } else if (exception instanceof AspExitDoException)
        {
            System.out.println("EXIT DO");
        } else super.dump();
    }

    /**
     * Executes this node, throwing the exception we want to throw.
     *
     * @param context AspContext under which to execute this node.
     * @return this function never returns
     * @see Node#execute(AspContext)
     */
    public Object execute(AspContext context) throws AspException
    {
        throw exception;
    }
};

