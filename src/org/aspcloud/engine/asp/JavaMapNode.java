/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.util.Enumeration;
import org.apache.log4j.Category;

/**
 * JavaMapNode handles Java objects which implement the SimpleMap 
 * interface.
 * @author Terence Haddock
 * @version 0.9
 */
public class JavaMapNode extends JavaObjectNode implements SimpleMap
{
    /** Debugging category */
    private static final transient Category DBG = Category.getInstance(JavaMapNode.class);

    /** The object this JavaObjectNode points to, dereferenced as a 
     * SimpleMap object */
    SimpleMap mapObj;

    /**
     * Constructor.
     * @param obj Sub-object this node is referencing
     */
    public JavaMapNode(SimpleMap obj)
    {
        super(obj);
        this.mapObj = obj;
    }

    /**
     * Overrides the get() operation for an object.
     * @param key Key key of object to objtain
     * @return Value of the key reference
     * @see SimpleMap#get(Object)
     * @throws AspException when an exception occurs
     */
    public Object get(Object key) throws AspException
    {
        return Types.coerceToNode(mapObj.get(key));
    }

    /**
     * Overrides the put() operation for an object.
     * @param key Key of the object to store.
     * @param value Value of the object to store
     * @see SimpleMap#put(Object, Object)
     * @throws AspException if an exception occurs
     */
    public void put(Object key, Object value) throws AspException
    {
        mapObj.put(key, value);
    }

    /**
     * Overrides the getKeys() operation for an object.
     * @return enumeration of the keys contained within this object.
     * @throws AspException if an error occurs.
     */
    public Enumeration getKeys() throws AspException
    {
        return mapObj.getKeys();
    }
}
