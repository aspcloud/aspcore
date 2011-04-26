/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.io.IOException;

/**
 * OutputNode handles the ASP notation &lt;%= value %&gt; to directly output 
 * an expression's value.
 * @author Terence Haddock
 * @version 0.9
 */
public class OutputNode extends DefaultNode
{
    /** Expression to output */
    Node expr;

    /** Debugging context, location of output node in asp code. */
    DebugContext dbgCtx;

    /**
     * Constructor.
     * @param expr Expression to output.
     * @param dbgCtx Context of expression in ASP file.
     */
    public OutputNode(Object expr, DebugContext dbgCtx)
    {
        this.expr = (Node)expr;
    }

    /**
     * Dumps the representation of this node.
     * @throws AspException in case of error
     * @see Node#dump
     */
    public void dump() throws AspException
    {
        System.out.print("<%=");
        expr.dump();
        System.out.print("%>");
    }

    /**
     * Executes this output node.
     * @param context AspContext under which to execute this node.
     * @return return value, for this node always null
     * @throws AspException in case of error
     * @see Node#execute
     */
    public Object execute(AspContext context) throws AspException
    {
        final IdentNode respIdent = new IdentNode("response");
        try {
            Object obj = expr.execute(context);
            String str = Types.coerceToString(obj);
            JavaObjectNode respObj = (JavaObjectNode)context.getValue(respIdent);
            Response resp = (Response)respObj.getSubObject();
            resp.Write(str);
        } catch (IOException ex) {
            throw new AspNestedException(ex, dbgCtx);
        } catch (AspException ex) {
            if (!ex.hasContext()) {
                ex.setContext(dbgCtx);
            }
            throw ex;
        }
        return null;
    }
};

