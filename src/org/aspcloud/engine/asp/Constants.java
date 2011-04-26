/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * The Constants class contains static contains used in the
 * org.aspcloud.engine.asp package.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class Constants
{
    /**
     * Static, constant NullNode.
     */
    public final static NullNode nullNode = new NullNode();

    /**
     * Static, constant UndefinedValueNode.
     */
    public final static UndefinedValueNode undefinedValueNode =
        new UndefinedValueNode();

    /**
     * Static, constant NothingNode.
     */
    public final static NothingNode nothingNode =
        new NothingNode();

    /**
     * Static, constant empty exception.
     */
    public final static AspEmptyException emptyException =
        new AspEmptyException();
}

