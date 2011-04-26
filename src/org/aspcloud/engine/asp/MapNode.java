/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * MapNode is an interface to a multi-dimensional array.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public interface MapNode extends Node
{
    /**
     * Obtains the value of this map node at the specified index.
     * To handle multi-dimensional arrays, the index is given as a list
     * of parameters.
     * @param varlist multi-dimension index of value to obtain from this map.
     * @param context Context under which this map is evaluated.
     * @return value of the map at the specified index(es)
     * @throws AspException if an error occurs
     */
    public Object getIndex(VarListNode varlist, AspContext context)
        throws AspException;

    /**
     * Obtains the upper bound of this map.
     * @param dimension Dimension of which to obtain upper bound of
     * @return upper bound of this map
     * @throws AspException if an error occurs
     */
    public int getUBOUND(int dimension) throws AspException;

    /**
     * Obtains the lower bound of this map.
     * @param dimension Dimension of which to obtain lower bound of
     * @return lower bound of this map
     * @throws AspException if an error occurs
     */
    public int getLBOUND(int dimension) throws AspException;
}

