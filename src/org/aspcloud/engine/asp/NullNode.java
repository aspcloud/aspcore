/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * NullNode represents the NULL value in Asp code.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class NullNode extends DefaultNode
{
    /**
     * Constructor
     */
    public NullNode()
    {
    }

    /**
     * Dumps the string representation of this node.
     * @see DefaultNode#dump
     */
    public void dump()
    {
        System.out.print("NULL");
    }

    /**
     * This function always returns true if it is being compared to a null
     * value, or if the other value is a NullNode object.
     * @param obj object to compare to
     * @return <b>true</b> if the objects are equals, <b>false</b> otherwise.
     */
    final public boolean equals(Object obj)
    {
        if (obj == null || obj instanceof NullNode)
            return true;
        return false;
    };
     
    /**
     * NullNodes all have the hashcode of zero.
     * @return always zero
     */
    public int hashCode()
    {
        return 0;
    };
};

