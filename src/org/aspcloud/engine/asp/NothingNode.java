/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This class contains a reference to a 'nothing' object, an object which
 * has no fields, no methods, no nothing.
 *
 * @author Terence Haddock
 */
public class NothingNode extends DefaultNode implements ObjectNode
{

    /**
     * Package-level constructor.
     */
    NothingNode() {};

    /**
     * Object method, objtains a field.
     * @param ident Identifier of the field to obtain.
     * @return Object which represents the field.
     * @throws AspException if an error occurs
     * @see ObjectNode#getField(IdentNode)
     */
    public Object getField(IdentNode ident) throws AspException
    {
        throw new AspException("Unknown field: " + ident);
    }

}
