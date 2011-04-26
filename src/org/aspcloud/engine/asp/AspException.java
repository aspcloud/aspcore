/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * Base class of exceptions throws from internal ASP classes.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspException extends Exception
{
    /** Context of this exception */
    DebugContext ctx = null;

    /** Short description of this exception */
    String desc;

    /** Number assocated with this error */
    int errorNumber;

    /** 
     * Constructor with no initial debugging context.
     * @param desc Short description.
     */
    public AspException(String desc)
    {
        super(desc);
        this.desc = desc;
        this.errorNumber = 1;
    }

    /**
     * Constructor with no initial debugging context, and an error number set.
     * @param desc Short description
     * @param errorNumber Error number
     */
    public AspException(String desc, int errorNumber)
    {
        super(desc);
        this.desc = desc;
        this.errorNumber = errorNumber;
    }

    /**
     * Constructor with the short description of the string, and
     * the debugging context of the exception.
     * @param desc Short description.
     * @param ctx Debugging context.
     */
    public AspException(String desc, DebugContext ctx)
    {
        super(desc);
        this.desc = desc;
        this.ctx = ctx;
        this.errorNumber = 1;
    }

    /**
     * Constructor with the short description of the string, an error number,
     * and the debugging context of the exception.
     * @param desc Short description.
     * @param errorNumber the error number
     * @param ctx Debugging context.
     */
    public AspException(String desc, int errorNumber, DebugContext ctx)
    {
        super(desc);
        this.desc = desc;
        this.errorNumber = errorNumber;
        this.ctx = ctx;
        this.errorNumber = 1;
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
     * Gets this exception's context.
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

    /**
     * AspCode
     * @return AspCode of this error
     */
    public int AspCode()
    {
        // XXX Not really implemented
        return 1;
    }

    /**
     * AspDescription
     * @return Detailed description of this error
     */
    public String AspDescription()
    {
        return toString();
    }

    /**
     * Description
     * @return Short Description of this error
     */
    public String Description()
    {
        return desc;
    }

    /**
     * Category
     * @return Category of this error 
     */
    public int Category()
    {
        /* XXX Not really implemented */
        return 1;
    }

    /**
     * Column
     * @return Column where this error occured
     */
    public int Column()
    {
        /* XXX Not really implemented */
        return 1;
    }

    /**
     * File
     * @return File where this error occured
     */
    public String File()
    {
        if (ctx == null) return "unknown";
        return ctx.displayFilename;
    }

    /**
     * Line
     * @return Line number where this error occured
     */
    public int Line()
    {
        if (ctx == null) return 0;
        return ctx.lineno;
    }

    /**
     * Number
     * @return COM number of this error
     */
    public int Number()
    {
        return errorNumber;
    }

    /**
     * Source
     * @return Source where this error occured
     */
    public String Source()
    {
        /* XXX Not really implemented */
        return "Source view not implemented.";
    }
}
