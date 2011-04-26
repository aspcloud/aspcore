/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * Implements the definition of an ident.
 * 
 * @author Terence Haddock
 * @version 0.9
 */
public class DefineIdentNode extends DefaultNode
{
    /** Name of ident to define */
    IdentNode ident;

    /**
     * Constructor.
     *
     * @param ident Name of the identifier to define
     */
    public DefineIdentNode(IdentNode ident)
    {
        this.ident = ident;
    }

    /**
     * Get the ident this node is defining.
     * @param ident ident this node is defining
     */
    public IdentNode getIdent()
    {
        return ident;
    }

    /**
     * Dumps this expression.
     * 
     * @see Node#dump()
     * @throws AspException if an error occurs.
     */
    public void dump() throws AspException
    {
        System.out.print("DIM ");
        ident.dump();
        System.out.println();
    }

    /**
     * Prepares this expression, this involves setting the scope of
     * the dimension statement.
     *
     * @param scope AspContext under which to prepare this expression.
     * @see Node#prepare(AspContext)
     * @throws AspException if an error occurs
     */
    public void prepare(AspContext scope) throws AspException
    {
        /* Check if this variable is already in scope */
        if (scope.inDirectScope(ident)) {
            throw new AspRedefineIdentException(ident.toString());
        }
        scope.forceScope(ident);
    }
    

    /**
     * Executes this expression.
     * 
     * @param scope AspContext under which to evaluate this expression.
     * @return always null
     * @see Node#execute(AspContext)
     * @throws AspException if an error occurs.
     */
    public Object execute(AspContext scope) throws AspException
    {
        return null;
    }
};

