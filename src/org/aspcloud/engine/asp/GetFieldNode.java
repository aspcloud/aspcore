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
 * This node handles the evaluating of a field expression evaluation.
 * Such as Object.Value.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class GetFieldNode extends DefaultNode
{
    /** Debugging category */
    private static final transient Category DBG =
        Category.getInstance(GetFieldNode.class);

    /** Identifier of the field expression */
    IdentNode ident;

    /** Expression which to find the field of */
    Node expr;

    /**
     * Constructor.
     *
     * @param expr Expression of the field.
     * @param ident Identifier of the field to evaluate.
     */
    public GetFieldNode(Node expr, IdentNode ident)
    {
        this.ident = ident;
        this.expr = (Node)expr;
    }

    /**
     * Get the ident.
     * TODO: This should be getIdentiifer()
     * @return field this object is getting.
     */
    public IdentNode getIdent()
    {
        return ident;
    }

    /**
     * Expression this object is getting the field of.
     * @return expression this object is getting the field index of.
     */
    public Node getExpression()
    {
        return expr;
    }

    /**
     * Dump this expression.
     * @see Node#dump
     * @throws AspException if an error occurs.
     */
    public void dump() throws AspException
    {
        System.out.print("{F}");
        expr.dump();
        System.out.print(".");
        ident.dump();
    }

    /**
     * Executes this field expression.
     *
     * @param context AspContext under which to evaluate the expression.
     * @return return value of this expression
     * @throws AspException If an error occurs
     * @see Node#execute(AspContext)
     */
    public Object execute(AspContext context) throws AspException
    {
        if (DBG.isDebugEnabled()) 
            DBG.debug("execute");
        Object value = expr.execute(context);
        while (value instanceof SimpleReference &&
                !(value instanceof ObjectNode))
        {
            if (DBG.isDebugEnabled()) 
                DBG.debug("De-referencing " + value);
            value = ((SimpleReference)value).getValue();
        }
        if (value instanceof ObjectNode)
        {
            ObjectNode obj = (ObjectNode)value;
            if (DBG.isDebugEnabled()) 
                DBG.debug("Get field of " + ident + " from " + obj);
            return Types.coerceToNode(obj.getField(ident));
        } else {
            throw new AspException("Invalid class for field get: " + value.getClass().getName());
        }
    }
};

