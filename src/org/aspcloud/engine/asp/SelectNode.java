/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

public class SelectNode implements Node
{
    Node expr;
    VarListNode statements;

    public SelectNode(Node expr, VarListNode statements)
    {
        this.expr = expr;
        this.statements = statements;
    }

    public void dump() throws AspException
    {
        System.out.print("SELECT CASE ");
        expr.dump();
        System.out.println();
        for (int i = 0; i < statements.size(); i++)
        {
            CaseNode obj = (CaseNode)statements.get(i);
            obj.dump();
        }
        System.out.println("END SELECT");
    }

    public void prepare(AspContext context) throws AspException
    {
        for (int i = 0; i < statements.size(); i++)
        {
            CaseNode obj = (CaseNode)statements.get(i);
            obj.prepare(context);
        }
        /* expr needs not be prepared */
    }

    public Object execute(AspContext context) throws AspException
    {
        Object value = expr.execute(context);
        for (int i = 0; i < statements.size(); i++)
        {
            CaseNode cas = (CaseNode)statements.get(i);
            if (cas.matches(value, context)) {
                return cas.execute(context);
            }
        }
        return null;
    }
};

