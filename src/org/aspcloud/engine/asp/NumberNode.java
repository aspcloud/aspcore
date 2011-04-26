/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * NumberNode class represents a number parsed from the ASP source file.
 * Usually contains either the Integer class or the Double class.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class NumberNode extends DefaultNode
{
    /** Number this node contains */
    Object number;

    /**
     * Constructor.
     * @param number Number this node should contain.
     */
    public NumberNode(Object number)
    {
        this.number = number;
    }

    /**
     * Get the number this node contains.
     * @return number
     */
    public Object getNumber()
    {
        return number;
    }

    /**
     * Dumps this node's string representation.
     * @see DefaultNode#dump
     */
    public void dump()
    {
        System.out.print(number);
    }

    /**
     * Executes this node within the specified context. This class evaluates
     * to the number this class contains.
     * @param context AspContext under which to execute this node.
     * @return Number this node contains.
     * @see Node#execute(AspContext)
     */
    public Object execute(AspContext context)
    {
        return number;
    }

    /**
     * Create a number node from a decimal string.
     * @param str String to create number node from.
     */
    public static NumberNode fromDoubleToken(String str)
    {
        /* See if it should be an integer or float */
        if (str.indexOf(".") == -1)
        {
            /* Should be an integer */
            long longValue = Long.parseLong(str);
            if (longValue < Integer.MAX_VALUE)
            {
                return new NumberNode(new Integer((int)longValue));
            } else {
                return new NumberNode(new Long(longValue));
            }
        } else {
            /* Should be a floating point */
            return new NumberNode(new Double(str));
        }
    }

    /**
     * Create a number node from a hex string.
     * @param str Hex string to create number node from.
     */
    public static NumberNode fromHexToken(String str)
    {
        if (!str.startsWith("&H"))
        {
            throw new AspRuntimeException("Invalid hex string: " + str);
        }
        long longValue = Long.parseLong(str.substring(2), 16);
        if (longValue < Integer.MAX_VALUE)
            return new NumberNode(new Integer((int)longValue));
        else
            return new NumberNode(new Long(longValue));
    }
};

