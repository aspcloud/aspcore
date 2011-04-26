/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

public interface SimpleReference
{
    public void setValue(Object value) throws AspException;

    public Object getValue() throws AspException;
};

