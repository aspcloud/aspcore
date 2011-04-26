/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This node sets the "option explicit" flag to declare that a variable
 * must be declared before it can be used.
 * 
 * @author Terence Haddock
 * @version 0.9
 */
public class OptionExplicitNode extends DefaultNode
{
    /**
     * Constructor.
     */
    public OptionExplicitNode()
    {
    }

    /**
     * Dumps the string representation of this node.
     * @see DefaultNode#dump
     */
    public void dump()
    {
        System.out.print("OPTION EXPLICIT");
    }

    /**
     * Executes this node. Sets the !explicit flag in the context.
     * @param context current context
     * @return undefined value.
     * @throws AspException on error
     * @see DefaultNode#execute
     */
    public Object execute(AspContext context) throws AspException
    {
        context.setOptionExplicit();
        return null;
    }
}
