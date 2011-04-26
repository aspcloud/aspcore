/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.util.Vector;

import org.apache.log4j.Category;

/**
 * This class contains a list of variables.
 * 
 * @author Terence Haddock
 */
public class VarListNode extends DefaultNode
{
    /** Debugging context */
    private static final transient Category DBG = Category.getInstance(VarListNode.class);

    /** List of variables */
    Vector    vars;

    /**
     * Constructor, empty list
     */
    public VarListNode()
    {
        vars = new Vector();
    }

    /**
     * Adds an element to the list
     * @param obj Element to add
     */
    public void append(Object obj)
    {
        vars.add(obj);
    }

    /**
     * Adds all of the elements in another varlistnode to this varlistnode.
     * @param v Sub-var list node to add
     */
    public void appendAll(VarListNode v)
    {
        for (int i = 0; i < v.size(); i++)
        {
            append(v.get(i));
        }
    }

    /**
     * Inserts the element to the beginning of the list.
     * @param obj Element to insert
     */
    public void prepend(Object obj)
    {
        vars.insertElementAt(obj, 0);
    }

    /**
     * Returns the size of this list 
     * @return size of this list
     */
    public int size()
    {
        return vars.size();
    }

    /**
     * Obtains an element at the specified index
     * @param index Index of element to obtain
     */
    public Object get(int index)
    {
        return vars.get(index);
    }

    /**
     * Dumps the representation of this varlist
     * @see DefaultNode#dump
     */
    public void dump() throws AspException
    {
        int i;
        for (i = 0; i < vars.size(); i++)
        {
            if (i != 0) System.out.print(" , ");
            Object obj = vars.get(i);
            if (obj instanceof Node)
            {
                ((Node)obj).dump();
            } else if (obj == null) {
                System.out.print("NULL");
            } else {
                System.out.print(obj.toString());
            }
        }
    }

    /**
     * Executes all of the elements in the variable list and returns
     * a vector of the return values of each node.
     * @param context Current context
     * @see DefaultNode#execute(AspContext)
     */
    public Object execute(AspContext context) throws AspException
    {
        Vector vec = new Vector();
        int i;
        for (i = 0; i < vars.size(); i++)
        {
            Object nObj = vars.get(i);
            if (nObj instanceof Node)
            {
                Node node = (Node)nObj;
                Object obj = node.execute(context);
                obj = Types.dereference(obj);
                vec.add(obj);
            } else {
                vec.add(nObj);
            }
        }
        return vec;
    }
};

