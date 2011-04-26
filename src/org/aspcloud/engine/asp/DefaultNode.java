/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This class contains the default implementation of the Node
 * interface. The default implementation is to throw the
 * AspNotImplemented exception for every method.
 * 
 * @author Terence Haddock
 * @version 0.9
 */
public class DefaultNode implements Node
{
    /**
     * Dumps the node representation in a textual format. Default
     * implementation is to throw an AspNotImplemented exception
     *
     * @throws AspNotImplementedException if not overriden
     * @see Node#dump()
     */
    public void dump() throws AspException
    {
        throw new AspNotImplementedException("dump");
    }

    /**
     * Prepares this node for execution. Default implementation is to
     * do nothing.
     *
     * @param context Global context
     * @throws AspException if an error occurs
     * @see Node#prepare(AspContext)
     */
    public void prepare(AspContext context) throws AspException
    {
        /* Default is to do nothing. */
    }

    /**
     * Executes this node, returning the result.
     * Default implementation is to return itself.
     *
     * @param context Current context
     * @return Result of this node's execution.
     * @throws AspNotException if any error occurs
     * @see Node#execute(AspContext)
     */
    public Object execute(AspContext context) throws AspException
    {
        return this;
    }
}
