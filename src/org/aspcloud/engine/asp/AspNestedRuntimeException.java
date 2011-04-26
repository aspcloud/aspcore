/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.io.PrintWriter;

/**
 * This exception class signals that a nested, generic exception has occured.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspNestedRuntimeException extends RuntimeException
{
    /**
     * The nested exception
     */
    Throwable ex;

    /**
     * Constructor.
     *
     * @param ex Nested exception
     */
    public AspNestedRuntimeException(Throwable ex)
    {
        super("Nested Exception: " + ex.toString());
        this.ex = ex;
    }

    /**
     * Prints the stack trace of this exception.
     *
     * @param s Writer to print the stack trace onto
     */
    public void printStackTrace(PrintWriter s)
    {
        ex.printStackTrace(s);
    }
}

