/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.util.Vector;

/**
 * AbstractFunctionNode contains basic code used by most Function
 * nodes including converting a VarListNode node to a Vector of
 * de-referenced values.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public abstract class AbstractFunctionNode extends DefaultNode
    implements FunctionNode
{
    /**
     * Overloaded class to handle executing functions, will convert
     * the variable list passed in to a Vector of elements.
     * @param varList parameter list
     * @param context Current context.
     * @return result of the function call
     * @throws AspException if any error occurs.
     * @see FunctionNode#execute(VarListNode,AspContext)
     * @see AbstractFunctionNode#execute(Vector,AspContext)
     */
    public Object execute(VarListNode varList, AspContext context)
        throws AspException
    {
        Vector values = (Vector)varList.execute(context);
        for (int i = 0; i < values.size(); i++)
        {
            Object obj = values.get(i);
            while (obj instanceof SimpleReference) {
                obj = ((SimpleReference)obj).getValue();
            }
            values.set(i, obj);
        }
        return execute(values, context);
    }

    /**
     * Abstract class to handle executing a function.
     * @param values De-referenced values
     * @param context Current context.
     * @return result of the function call
     * @throws AspException if any error occurs.
     * @see FunctionNode#execute(VarListNode,AspContext)
     * @see AbstractFunctionNode#execute(Vector,AspContext)
     */
    abstract public Object execute(Vector values, AspContext context)
        throws AspException;
}
