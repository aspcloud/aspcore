/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * Base class for runtime exceptions thrown from internal ASP classes.
 * 
 * @author Terence Haddock
 * @version 0.9
 */
public class AspRuntimeException extends RuntimeException
{
    /** Current debugging context */
    DebugContext ctx = null;

    /** Short description of this exception */
    String desc;

    /** 
     * Constructor with no initial debugging context.
     * @param desc Short description.
     */
    public AspRuntimeException(String desc)
    {
        super(desc);
        this.desc = desc;
    }

    /**
     * Constructor with the short description of the string, and
     * the debugging context of the exception.
     * @param desc Short description.
     * @param ctx Debugging context.
     */
    public AspRuntimeException(String desc, DebugContext ctx)
    {
        super(desc);
        this.desc = desc;
        this.ctx = ctx;
    }

    /**
     * Checks if this exception has its context.
     * @return <b>true</b> if this exception has its context, 
     *    <b>false</b> otherwise.
     */
    public boolean hasContext()
    {
        return ctx != null;
    }

    /**
     * Sets this exception's context.
     * @param ctx Debugging context
     */
    public void setContext(DebugContext ctx)
    {
        this.ctx = ctx;
    }

    /**
     * Gets the exception's context.
     * @return Debugging context
     */
    public DebugContext getContext()
    {
        return ctx;
    }

    /**
     * Obtains the short description of this exception.
     * @return short description of this exception, including
     *    debugging context.
     */
    public String toString()
    {
        if (ctx != null)
        {
            return "File " + ctx.displayFilename + " line " + ctx.lineno +
                ": " + desc;
        } else {
            return "File unknown line unknown: " + desc;
        }
    }
}
