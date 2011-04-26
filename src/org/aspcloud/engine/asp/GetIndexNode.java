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
 * This node handles the "map" expression in vbscript, such as
 * Session("variable").
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class GetIndexNode extends DefaultNode
{
    /** Debugging category */
    private static final transient Category DBG = Category.getInstance(GetIndexNode.class);

    /** Expression to obtain index of */
    Node ident;

    /** Parameters to field expression */
    VarListNode expr;

    /** Is this expression for functions only? */
    boolean func;

    /**
     * Get the parameters.
     * @return parameters to obtain index of.
     */
    public VarListNode getExpression()
    {
        return expr;
    }

    /**
     * Get the identifier.
     * @return identifier to use
     */
    public Node getIdent()
    {
        return ident;
    }

    /**
     * Constructor. Can be used for arrays, functions, and subroutines.
     * @param ident Expression for field get.
     * @param expr Parameter for field get
     */
    public GetIndexNode(Node ident, VarListNode expr)
    {
        this.ident = ident;
        this.expr = expr;
        this.func = false;
    }

    /**
     * Constructor. Can be used to mark that subroutines not allowed.
     * @param ident Expression for field get.
     * @param expr Parameter for field get
     * @param func Is this a function?
     */
    public GetIndexNode(Node ident, VarListNode expr, boolean func)
    {
        this.ident = ident;
        this.expr = expr;
        this.func = func;
    }

    /**
     * Dumps the visual representation of this expression.
     * @throws AspException if an error occurs.
     * @see Node#dump()
     */
    public void dump() throws AspException
    {
        System.out.print("{I}");
        ident.dump();
        System.out.print("(");
        expr.dump();
        System.out.print(")");
    }

    /**
     * Executes this node.    
     * @param context The context under which to evaluate this expression.
     * @return value of this expression.
     * @throws AspException if an error occurs.
     * @see Node#execute(AspContext)
     */
    public Object execute(AspContext context) throws AspException
    {
        /* Intermidate object */
        Node value;
        if (DBG.isDebugEnabled())
            DBG.debug("Get index of " + ident);
        if (!(ident instanceof IdentNode))
        {
            /* Check for map or function nodes */
            Object objVal = ident.execute(context);
            /* De-reference the node */
            while ((objVal instanceof SimpleReference) &&
                    !(objVal instanceof MapNode) &&
                    !(objVal instanceof FunctionNode)) {
                if (DBG.isDebugEnabled())
                    DBG.debug("Dereference: " + objVal);
                objVal = ((SimpleReference)objVal).getValue();
            }
            value = (Node)objVal;
        } else {
            /* Indirect reference */
            Object objVal = context.getValue((IdentNode)ident);
            while ((objVal instanceof SimpleReference) &&
                    !(objVal instanceof MapNode) &&
                    !(objVal instanceof FunctionNode)) {
                if (DBG.isDebugEnabled())
                    DBG.debug("Dereference: " + objVal);
                objVal = ((SimpleReference)objVal).getValue();
            }
            if (DBG.isDebugEnabled())
                if (objVal != null)
                    DBG.debug("Execute on: " + objVal + "(" + objVal.getClass() + ")");
            value = (Node)objVal;
        }
        /* Check for null pointer */
        if (value == null || value instanceof NullNode) {
            throw new AspException("NULL pointer exception");
        }
        /* Check for undefined functions or values */
        if (value instanceof UndefinedValueNode) {
            throw new AspException("Undefined function or value: "
                + ident.toString().toUpperCase());
        }
        if (func && expr.size() > 1) {
            if (value instanceof SubDefinitionNode) {
                SubDefinitionNode sub = (SubDefinitionNode)value;
                if (!sub.isFunction) {
                    throw new AspException("Call required for subroutine call");
                }
            }
        }
        if (value instanceof MapNode) {
            /* Handle a map get */
            if (DBG.isDebugEnabled())
                DBG.debug("Map get on " + value.getClass());
            Object ret = ((MapNode)value).getIndex(expr, context);
            if (DBG.isDebugEnabled())
                DBG.debug("Return: " + ret);
            return ret;
        }
        if (value instanceof FunctionNode) {
            /* Handle a function call */
            if (DBG.isDebugEnabled())
                DBG.debug("Execute on " + value.getClass());
            Object ret = ((FunctionNode)value).execute(expr, context);
            if (DBG.isDebugEnabled())
                DBG.debug("Return: " + ret);
            return ret;
        }
        /* Type conversion failure */
        throw new AspException("Unknown type for (): " + value.getClass().getName());
    }

    /**
     * Set the function flag.
     * @param isFunc is a function?
     */
    public void setFunction(boolean isFunction)
    {
        this.func = isFunction;
    }
};

