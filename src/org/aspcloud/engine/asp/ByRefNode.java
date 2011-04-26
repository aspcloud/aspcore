/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * The ByRefNode holds an ident which is a variable passed by reference to
 * a function or procedure.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class ByRefNode extends DefaultNode
{
    /** The identifier this byrefnode corresponds to. */
    IdentNode ident;

    /**
     * Constructor.
     * @param ident Identifier this ByRefNode corresponds to
     */
    public ByRefNode(IdentNode ident)
    {
        this.ident = ident;
    }

    /**
     * Dumps the visual contents of this ByRefNode
     */
    public void dump()
    {
        ident.dump();
    }

    /**
     * Get the identifier this ByRefNode points to.
     * @return IdentNode this ByRefNode points to
     */
    public IdentNode getIdent()
    {
        return ident;
    }
}

