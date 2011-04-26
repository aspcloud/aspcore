/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * IdentNode contains an identifier
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class IdentNode extends DefaultNode
{
    /** The string identifier of this node */
    String ident;

    /**
     * Constructor.
     * @param ident String identifier of this object
     */
    public IdentNode(String ident)
    {
        if (ident.charAt(0) == '.')
        {
            int i = 1;
            while ((i < ident.length()) && (ident.charAt(i) == ' ' ||
                ident.charAt(i) == '\t' || ident.charAt(i) == '\n' ||
                ident.charAt(i) == '_' )) i++;
            this.ident = ident.substring(i);
        } else {
            this.ident = ident;
        }
    }

    /**
     * Dumps the code representation of this node.
     * @see Node#dump()
     */
    public void dump()
    {
        System.out.print(ident);
    }

    /**
     * Executes this node. For an Ident node, if this ident contains
     * a function the function is called with no arguments. Otherwise,
     * the value of the identifier is returned.
     * @param context Context under which to execute this identifier.
     * @see Node#execute(AspContext)
     * @throws AspException if an error occurs.
     */
    public Object execute(AspContext context) throws AspException
    {
        Object val = context.getValue(this);
        if (val instanceof FunctionNode)
        {
            return ((FunctionNode)val).execute(new VarListNode(), context);
        }
        return val;
    }

    /**
     * Tests equality of this identifier to another identifier.
     * @param obj Object to compare this identifier to.
     * @return <b>true</b> if obj is an IdentNode and obj contains an
     * identifier with the same name.
     */
    public boolean equals(Object obj)
    {
        if (obj instanceof IdentNode) {
            return ident.equalsIgnoreCase(((IdentNode)obj).ident);
        } else {
            return false;
        }
    }

    /**
     * Calculates the hashtable code for this IdentNode. Every IdentNode
     * which contains the same identifier name should return the same
     * hash code.
     * @return hash code
     */
    public int hashCode()
    {
        return ident.toLowerCase().hashCode();
    }

    /**
     * Outputs the string representation of this identifier, which is
     * simple the text name of the identifier this IdentNode points to.
     * @return string representation of this identifier.
     */
    public String toString()
    {
        return ident;
    }
};

