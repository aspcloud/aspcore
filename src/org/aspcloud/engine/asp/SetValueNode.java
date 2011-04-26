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
 * SetValueNode implements setting of variables, the following construct:
 * <pre>
 * Set A = "Value"
 * </pre>
 * And:
 * <pre>
 * A = "Value"
 * </pre>
 *
 * @author Terence Haddock
 */
public class SetValueNode implements Node
{
    /** Debugging category */
    private static final transient Category    DBG = Category.getInstance(SetValueNode.class);

    /** The identifier we wish to set */
    Node    ident;

    /** The identifier's new expression when executed */
    Node    expr;

    /** Was the "SET" keyword used? */
    boolean setUsed;

    /**
     * Constructor.
     * @param ident Identifier we wish to set, can be an IdentNode,
     * or a compound expression which evaluates to a SimpleReference or
     * FunctionNode.
     * @param expr Target expression for the identifier.
     * @param setUsed was the SET keyword used?
     */
    public SetValueNode(Node ident, Node expr, boolean setUsed)
    {
        this.ident = ident;
        this.expr = expr;
        this.setUsed = setUsed;
    }

    /**
     * Gets the identifier which is being set.
     * @return identifier being set
     */
    public Node getIdent()
    {
        return ident;
    }

    /**
     * Gets the expression.
     * @return expression
     */
    public Node getExpression()
    {
        return expr;
    }

    /**
     * Dumps the debugging information about this node.
     * @throws AspException on error    
     */
    public void dump() throws AspException
    {
        System.out.print("{S}");
        if (setUsed) System.out.print("SET ");
        ident.dump();
        System.out.print("=");
        expr.dump();
    }

    /**
     * Prepare this node for execution. This does nothing for a SetValueNode.
     * @param context AspContext for this node.
     * @throws AspException on error
     */
    public void prepare(AspContext context) throws AspException
    {
        /* Nothing to do, the arguments to SetValueNodes should not
         * need prepared.
         */
    }

    /**
     * Executes this node, setting the value of the node.
     * @param context Context for the node.
     * @return Always returns null.
     * @throws AspException on error
     */
    public Object execute(AspContext context) throws AspException
    {
        /* Executes the expression, obtaning the dereferenced value */
        Object value = Types.dereference(expr.execute(context));
        if (DBG.isDebugEnabled()) {
            DBG.debug("Execute " + ident + " = " + value);
        }
        /* Check if the expression is valid for this expression, depending on
           if the SET keyword was used */
        if (setUsed && !(value instanceof ObjectNode))
        {
            DBG.debug("SET was used where not allowed");
            /* XXX throw new AspInvalidArgumentsException("SET"); */
        } else if (!setUsed && (value instanceof ObjectNode))
        {
            DBG.debug("SET was not used where required");
            /* XXX throw new AspInvalidArgumentsException("SET"); */
        }
        /* Test for IdentNode */
        if (ident instanceof IdentNode) {
            Object identVal = context.getValue((IdentNode)ident);
            if (identVal instanceof ConstValue)
                throw new AspReadOnlyException("Constant value");
            context.setValue((IdentNode)ident, value);
        } else {
            /* If it's not an IdentNode, we execute the node and see what
             * the result is. */
            Object identVal = ident.execute(context);
            if (identVal instanceof SimpleReference) {
                ((SimpleReference)identVal).setValue(value);
            } else if (identVal instanceof FunctionNode) {
                VarListNode vl = new VarListNode();
                vl.append(value);
                ((FunctionNode)identVal).execute(vl, context);
            } else {
                throw new AspException("Unknown object to set: " +
                            identVal.getClass().getName());
            }
        }
        return null;
    }
};

