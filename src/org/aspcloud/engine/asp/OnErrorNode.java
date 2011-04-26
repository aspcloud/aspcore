/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import org.apache.log4j.Category;

/**
 * The OnErrorNode handles the ON ERROR GOTO x and ON ERROR RESUME NEXT
 * code which enables error handling in subroutines.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class OnErrorNode extends DefaultNode
{
    /**
     * Debugging class
     */ 
    private static final transient Category DBG = Category.getInstance(OnErrorNode.class);

    /**
     * Line number to jump to
     */
    NumberNode lineno;

    /**
     * Resume next?
     */
    boolean resumenext;

    /**
     * Constructor for resume next.
     */
    public OnErrorNode()
    {
        resumenext = true;
    }

    /**
     * Constructor to jump to line number.
     * @param lineno Line number to jump to
     */
    public OnErrorNode(NumberNode lineno)
    {
        this.lineno = lineno;
        resumenext = false;
    }

    /**
     * Executes this node, setting the error handler appropriately.
     * 
     * @param context AspContext under which to execute this node.
     * @return null
     * @see Node#execute(AspContext)
     */
    public Object execute(AspContext context) throws AspException
    {
        final IdentNode ident = new IdentNode("_err");
        if (DBG.isDebugEnabled()) DBG.debug("Executing On Error");
        context.forceScope(ident);
        if (resumenext) {
            if (DBG.isDebugEnabled()) DBG.debug("Setting value to null");
            context.setValue(ident, null);
        } else {
            Object val = lineno.execute(context);
            Integer iVal = Types.coerceToInteger(val);
            if (DBG.isDebugEnabled()) DBG.debug("Setting value to " + iVal);
            context.setValue(ident, iVal);
        }
        return null;
    }
}
