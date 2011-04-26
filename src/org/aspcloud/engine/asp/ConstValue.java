/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * ConstValue handles constant values, preventing changing the value.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class ConstValue implements SimpleReference
{
    /** Value this constant contains */
    Object value;

    /**
     * Constructor.
     * @param value Value this constant contains.
     */
    public ConstValue(Object value)
    {
        this.value = value;
    }

    /**
     * Sets the value of this variable, throws an error for these 
     * constant values.
     * @param value New value for this node.
     * @throws AspReadOnlyException when called.
     * @see SimpleReference#setValue
     */
    public void setValue(Object value) throws AspException
    {
        throw new AspReadOnlyException("Constant expression");
    }

    /**
     * Obtains the value of this variable
     * @return Value of this expression.
     * @see SimpleReference#getValue
     */
    public Object getValue() throws AspException
    {
        return value;    
    }
};

