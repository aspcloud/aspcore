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
 * JavaReferenceMapNode class is a sub-class of JavaObjectNode specifically
 * for java objects which implement both the SimpleMap interface as well
 * as the SimpleReference interface.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class JavaReferenceMapNode extends JavaObjectNode implements SimpleMap, SimpleReference
{
    /** Debugging object */
    private static final transient Category DBG =
        Category.getInstance(JavaReferenceMapNode.class);

    /** Contained object as a SimpleMap interface object */
    SimpleMap mapObj;
    
    /** Contained object as a SimpleReference interface object */
    SimpleReference refObj;

    /**
     * Constructor.
     * @param obj SimpleMap/SimpleReference object
     */
    public JavaReferenceMapNode(Object obj)
    {
        super(obj);
        this.mapObj = (SimpleMap)obj;
        this.refObj = (SimpleReference)obj;
    }

    /**
     * Sets the value of this object.
     * @param value new value of this object.
     * @throws AspException if an error occurs
     * @see SimpleReference#setValue(Object)
     */
    public void setValue(Object value) throws AspException
    {
        refObj.setValue(value);
    }

    /**
     * Obtains the value of this object.
     * @return value of this object.
     * @throws AspException if an error occurs
     * @see SimpleReference#getValue()
     */
    public Object getValue() throws AspException
    {
        return refObj.getValue();
    }

    /**
     * Obtains the value of an object contained within this map.
     * @param key Key of object to obtain
     * @return value of the object referenced by the key
     * @throws AspException if an error occurs
     * @see SimpleMap#get(Object)
     */
    public Object get(Object key) throws AspException
    {
        return Types.coerceToNode(mapObj.get(key));
    }

    /**
     * Stores a value into this map.
     * @param key Key of object to store
     * @param value Value of object to store
     * @throws AspException if an error occurs
     * @see SimpleMap#put(Object,Object)
     */
    public void put(Object key, Object value) throws AspException
    {
        mapObj.put(key, value);
    }

    /**
     * Obtains the list of keys of this map.
     * @return list of keys stored in this map.
     * @throws AspException if an error occurs
     * @see SimpleMap#getKeys
     */
    public Enumeration getKeys() throws AspException
    {
        return mapObj.getKeys();
    }
}
