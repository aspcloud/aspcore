/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This exception is thrown if write is attempted on a read-only variable.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspReadOnlyException extends AspException
{
    /**
     * Constructor. Non-descriptive, it is suggested to use the
     * constructor with a single string parameter to describe the error.
     */
    public AspReadOnlyException()
    {
        super("Modification of read-only variable");
    }

    /**
     * Constructor.
     *
     * @param desc Description, usually variable on which write was attempted.
     */
    public AspReadOnlyException(String desc)
    {
        super("Modification of read-only variable: " + desc);
    }
}

