/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This class contains a reference to an object which is being passed
 * by reference. Useful in ASP to Java calls where the object is passed
 * by reference instead of by value.
 *
 * @author Terence Haddock
 */
public class ByRefValue
{
    /** The sub-object this class contains. */
    Object subObject;

    /**
     * Constructor.
     */
    public ByRefValue()
    {
    };

    /**
     * Constructor, with initial object.
     * @param subObject sub-object this class contains.
     */
    public ByRefValue(Object subObject)
    {
        this.subObject = subObject;
    }

    /**
     * Obtains this object's value.
     */
    public Object getValue()
    {
        return subObject;
    }

    /**
     * Set this object's value.
     */
    public void setValue(Object subObject)
    {
        this.subObject = subObject;
    }
}

