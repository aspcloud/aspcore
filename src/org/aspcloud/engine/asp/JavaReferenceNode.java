/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import org.apache.log4j.Category;

/**
 * JavaReferenceNode is a sub-class of the JavaObjectNode class
 * which handles Java objects which implement the SimpleReference interface.
 * @author Terence Haddock
 * @version 0.9
 */
public class JavaReferenceNode extends JavaObjectNode implements SimpleReference
{
    /** Debugging category */
    private static final transient Category DBG = Category.getInstance(JavaReferenceNode.class);

    /** SimpleReference cast of the object contained in this JavaObjectNode */
    SimpleReference obj;

    /** Constructor.
     * @param obj SimpleReference object
     */
    public JavaReferenceNode(SimpleReference obj)
    {
        super(obj);
        this.obj = obj;
    }

    /**
     * Sets the value of this object.
     * @param value New value of this object
     * @throws AspException if an error occurs
     * @see SimpleReference#setValue
     */
    public void setValue(Object value) throws AspException
    {
        obj.setValue(value);
    }

    /**
     * Obtains the value of this object.
     * @return value of this object
     * @throws AspException if an error occurs
     * @see SimpleReference#getValue
     */
    public Object getValue() throws AspException
    {
        return obj.getValue();
    }
}
