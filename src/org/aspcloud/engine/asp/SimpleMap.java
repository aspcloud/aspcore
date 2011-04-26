/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.util.Enumeration;

public interface SimpleMap
{
    public Object get(Object key) throws AspException;

    public void put(Object key, Object value) throws AspException;

    public Enumeration getKeys() throws AspException;
}

