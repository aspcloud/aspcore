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
 * Implements the case part of the select..case statement.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class CaseNode extends DefaultNode
{
    /** List of tests for this case */
    VarListNode tests;

    /** Code to execute if one of the tests evaluate to true */
    BlockNode exec;

    /**
     * Constructor to create a new case node.
     * @param tests Tests to evaluate, <b>null</b> for the ELSE statement.
     * @param exec Block to execute.
     */
    public CaseNode(VarListNode tests, BlockNode exec)
    {
        this.tests = tests;
        this.exec = exec;
    }

    /**
     * Dumps this case node.
     * @see Node#dump()
     * @throws AspException if an error occurs
     */
    public void dump() throws AspException
    {
        System.out.print("CASE ");
        if (tests == null)
        {
            System.out.print("ELSE");
        } else {
            tests.dump();
        }
        System.out.println();
        exec.dump();
    }

    /**
     * Checks if the test matches the case node.
     * @return <b>true</b> if the case node matches, <b>false</b> otherwise.
     * @throws AspException if an error occurs
     */
    public boolean matches(Object value, AspContext context) throws AspException
    {
        if (tests == null) {
            return true;
        }
        String strValue = Types.coerceToString(value);
        Vector vecValues = (Vector)tests.execute(context);
        for (int i = 0; i < vecValues.size(); i++)
        {
            String testValue = Types.coerceToString(vecValues.get(i));
            if (testValue.equals(strValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prepares this node. Calls prepare on children nodes.
     * 
     * @param context Global context
     * @see Node#prepare(AspContext)
     * @throws AspException if an error occurs.
     */
    public void prepare(AspContext context) throws AspException
    {
        exec.prepare(context);
    }

    /**
     * Executes this case node.
     * @param context AspContext under which to execute this case node.
     * @return the result of this block node.
     * @see Node#execute(AspContext)
     * @throws AspException if an error occurs
     */
    public Object execute(AspContext context) throws AspException
    {
        return exec.execute(context);
    }
};

