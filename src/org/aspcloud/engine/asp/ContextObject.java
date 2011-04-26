/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This class is a container which stores a single object and the
 * DebugContext in which this object was created.
 *
 * @author Terence Haddock
 * @version 0.9
 * @see DebugContext
 */
public class ContextObject
{
    /**
     * The object this container holds.
     */
    public Object object;

    /**
     * The line number this object was created.
     */
    public int lineno;

    /**
     * Constructor.
     *
     * @param object The object to hold.
     * @param context The line number of this object.
     */
    public ContextObject(Object object, int lineno)
    {
        this.object = object;
        this.lineno = lineno;
    }

    /**
     * Output this context container as a string. For debugging.
     */
    public String toString()
    {
        return "[" + object + "] at line " + lineno;
    }
}

