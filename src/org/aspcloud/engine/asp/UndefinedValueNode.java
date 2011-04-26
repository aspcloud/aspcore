/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.io.Serializable;

/**
 * UndefinedValue represents the "undefined" value in Asp code.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class UndefinedValueNode extends DefaultNode implements Serializable
{
    /**
     * Constructor
     */
    public UndefinedValueNode()
    {
    }

    /**
     * Dumps the string representation of this node.
     * @see DefaultNode#dump
     */
    public void dump()
    {
        System.out.print("(undefined)");
    }
};

