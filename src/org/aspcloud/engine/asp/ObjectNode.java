/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * ObjectNode is an interface which represents an "object", something
 * which contains fields.
 * @author Terence Haddock
 * @version 0.9
 */
public interface ObjectNode extends Node
{
    /**
     * Obtains the named field of an object.
     * @param ident Identifier of the field to obtain.
     * @return Object which represents the field.
     * @throws AspException if an error occurs
     */
    public Object getField(IdentNode ident) throws AspException;
};

