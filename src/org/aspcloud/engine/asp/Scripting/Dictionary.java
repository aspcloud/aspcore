/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp.Scripting;

import org.aspcloud.engine.asp.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Implements the Scripting.Dictionary object available
 * from Server.CreateObject("Scripting.Dictionary")
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class Dictionary implements SimpleMap
{
    /**
     * Contents of this dictionary, prepended with '_' to make it 
     * unavailable to ASP scripts
     */
    Hashtable _contents = new Hashtable();

    /**
     * Keys of this dictionary, stored in a vector to preserve
     * sort order. Prepended with '_' to make it unavailable to ASP
     * scripts.
     */
    Vector _keys = new Vector();

    /**
     * Constructor
     */
    public Dictionary() {};

    /**
     * Stores a value in this dictionary. Throws an error if the value
     * already exists.
     * @param name Name of value
     * @param value Value to store
     */
    public void Add(String name, Object value) throws AspException
    {
        if (_contents.containsKey(name)) {
            throw new RuntimeException("Already contains key: " + name);
        }
        put(name, value);
    }

    /**
     * Removes a value from this dictionary. Throws an error if the value
     * does not exist.
     * @param name Name of value
     */
    public void Remove(String name)
    {
        if (!_contents.containsKey(name)) {
            throw new RuntimeException("Key does not exist: " + name);
        }
        _keys.remove(name);
        _contents.remove(name);
    }

    /**
     * Tests for a key's existance.
     * @param name Name of value
     * @return <b>true</b> if value exists, <b>false</b> otherwise.
     */
    public boolean Exists(String name)
    {
        return _contents.containsKey(name);
    }

    /**
     * Returns number of elements in this dictionary.
     * @return number of elements in this dictionary.
     */
    public int Count()
    {
        return _contents.size();
    }

    /**
     * Obtains a element from the dictionary.
     * @param key Key to obtain.
     * @return value of key
     * @see SimpleMap#get(Object)
     * @throws ASPException if an error occurs
     */
    public Object get(Object key) throws AspException
    {
        String name = Types.coerceToString(key);
        return _contents.get(name);
    }

    /**
     * Stores an element in the dictionary.
     * @param key Key to store
     * @param value Value of key
     * @see SimpleMap#put(Object,Object)
     * @throws ASPException if an error occurs
     */
    public void put(Object key, Object value) throws AspException
    {
        String name = Types.coerceToString(key);
        if (value == null) {
            value = new NullNode();
        }
        if (!_keys.contains(name))
        {
            _keys.add(name);
        }
        _contents.put(name, value);
    }

    /**
     * Obtains the keys stored in this dictionary.
     * @return Keys of this dictionary.
     * @see SimpleMap#getKeys
     * @throws ASPException if an error occurs
     */
    public java.util.Enumeration getKeys() throws AspException
    {
        return _keys.elements();
    }

    /**
     * Obtains the keys of this dictionary. Converts the internal Vector
     * of keys into an array for conversion to node.
     * @return Array of keys in this dictionary.
     * @throws AspException if an error occurs.
     */
    public Object[] keys() throws AspException
    {
        Object arr[] = new Object[_contents.size()];
        int i = 0;
        Vector keyVec = new Vector();
        Enumeration enKeys = getKeys();
        while (enKeys.hasMoreElements())
        {
            arr[i] = enKeys.nextElement();
            i++;
        }
        return arr;
    }
}

