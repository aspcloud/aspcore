/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This node handles function calls within a vbscript node.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class FunctionCallNode implements Node
{
    /** Expression of this function */
    Node        ident;
    /** Parameters to this function */
    VarListNode    expr;

    /**
     * Constructor.
     *
     * @param ident Identifier of this function
     * @param expr Parameters to this function
     */
    public FunctionCallNode(Object ident, Object expr)
    {
        this.ident = (Node)ident;
        this.expr = (VarListNode)expr;
    }

    /**
     * Dumps the reprensentation of this node.
     * @throws AspException if an error occurs.
     * @see Node#dump
     */
    public void dump() throws AspException
    {
        ident.dump();
        System.out.print("(");
        expr.dump();
        System.out.print(")");
    }

    /**
     * Obtains the expression of this function call node.
     * @return expression
     */
    public VarListNode getExpression()
    {
        return expr;
    }

    /**
     * Prepares this function for evaluation.
     *
     * @param context AspContext under which to evaluate this expression.
     * @throws AspException if an error occurs.
     */
    public void prepare(AspContext context) throws AspException
    {
        /* Nothing to do, the arguments to function calls
          * should not need prepared.
         */
    }

    /**
     * Executes this expression.
     *
     * @param context AspContext under which to evaluate this expression.
     * @return the value of this function call.
     * @throws AspException if an error occurs.
     */
    public Object execute(AspContext context) throws AspException
    {
        SubDefinitionNode sub = (SubDefinitionNode)ident.execute(context);
        return sub.execute(expr, context);
    }
};

