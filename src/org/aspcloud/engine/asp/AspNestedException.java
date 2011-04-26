/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.io.PrintStream;
import java.io.PrintWriter;
import org.apache.log4j.Category;

/**
 * AspNestedException handles generic sub-exceptions.
 * 
 * @author Terence Haddock
 * @version 0.9
 */
public class AspNestedException extends AspException
{
    /** Debugging category */
    Category DBG = Category.getInstance(AspNestedException.class);

    /** Nested exception */
    Throwable nestedEx;

    /**
     * Constructor with an empty exception.
     */
    public AspNestedException()
    {
        super(Constants.emptyException.toString());
        this.nestedEx = Constants.emptyException;
    }
    
    /**
     * Constructor with no initial debugging context
     * @param ex Nested exception
     */
    public AspNestedException(Throwable ex)
    {
        super(ex.toString());
        if (DBG.isDebugEnabled()) DBG.debug("AspNestedException: " + ex.toString());
        this.nestedEx = ex;
    }

    /**
     * Constructor with an error number and no initial debugging context
     * @param ex Nested exception
     */
    public AspNestedException(Throwable ex, int errorNumber)
    {
        super(ex.toString(), errorNumber);
        if (DBG.isDebugEnabled()) DBG.debug("AspNestedException: " + ex.toString());
        this.nestedEx = ex;
    }

    /**
     * Constructor with initial debugging context
     * @param ex Nested exception
     * @param ctx Debugging context
     */
    public AspNestedException(Throwable ex, DebugContext ctx)
    {
        super(ex.toString(), ctx);
        this.nestedEx = ex;
        if (nestedEx instanceof AspException)
        {
            AspException aspEx = (AspException)nestedEx;
            if (!aspEx.hasContext())
                aspEx.setContext(ctx);
        }
    }

    /**
     * Constructor with an error number and initial debugging context
     * @param ex Nested exception
     * @param ctx Debugging context
     */
    public AspNestedException(Throwable ex, int errorNumber, DebugContext ctx)
    {
        super(ex.toString(), errorNumber, ctx);
        this.nestedEx = ex;
        if (nestedEx instanceof AspException)
        {
            AspException aspEx = (AspException)nestedEx;
            if (!aspEx.hasContext())
                aspEx.setContext(ctx);
        }
    }

    /**
     * Set this object's context.
     */
    public void setContext(DebugContext ctx)
    {
        super.setContext(ctx);
        if (DBG.isDebugEnabled()) DBG.debug("setContext: " + ctx);
        if (nestedEx instanceof AspException)
        {
            AspException aspEx = (AspException)nestedEx;
            if (!aspEx.hasContext())
                aspEx.setContext(ctx);
        }
    }

    /**
     * ToString
     * @return String representation of exception
     */
    public String toString()
    {
        if (DBG.isDebugEnabled()) DBG.debug("toString: " + nestedEx.toString());
        return nestedEx.toString();
    }

    /**
     * printStackTrace prints the stack trace to the specified location
     * @param s PrintStream to use for output
     */
    public void printStackTrace(PrintStream s)
    {
        if (DBG.isDebugEnabled()) DBG.debug("PrintStackTrace(PrintStream)");
        nestedEx.printStackTrace(s);
        s.println(super.toString());
    }

    /**
     * printStackTrace prints the stack trace to the specified location
     * @param s PrintWriter to use for output
     */
    public void printStackTrace(PrintWriter s)
    {
        if (DBG.isDebugEnabled()) DBG.debug("PrintStackTrace(PrintWriter)");
        nestedEx.printStackTrace(s);
        s.println(super.toString());
    }

    /**
     * printStackTrace prints the stack trace to System.err
     */
    public void printStackTrace()
    {
        if (DBG.isDebugEnabled()) DBG.debug("PrintStackTrace(System.err)");
        nestedEx.printStackTrace();
        System.err.println(super.toString());
    }
    
    /**
     * AspCode
     * @return AspCode of this error
     */
    public int AspCode()
    {
        if (nestedEx instanceof AspException)
            return ((AspException)nestedEx).AspCode();
        return super.AspCode();
    }

    /**
     * AspDescription
     * @return Detailed description of this error
     */
    public String AspDescription()
    {
        if (nestedEx instanceof AspException)
            return ((AspException)nestedEx).AspDescription();
        return super.AspDescription();
    }

    /**
     * Description
     * @return Short Description of this error
     */
    public String Description()
    {
        if (nestedEx instanceof AspException)
            return ((AspException)nestedEx).Description();
        return super.Description();
    }

    /**
     * Category
     * @return Category of this error 
     */
    public int Category()
    {
        if (nestedEx instanceof AspException)
            return ((AspException)nestedEx).Category();
        return super.Category();
    }

    /**
     * Column
     * @return Column where this error occured
     */
    public int Column()
    {
        if (nestedEx instanceof AspException)
            return ((AspException)nestedEx).Column();
        return super.Column();
    }

    /**
     * File
     * @return File where this error occured
     */
    public String File()
    {
        if (nestedEx instanceof AspException)
            return ((AspException)nestedEx).File();
        return super.File();
    }

    /**
     * Line
     * @return Line number where this error occured
     */
    public int Line()
    {
        if (nestedEx instanceof AspException)
            return ((AspException)nestedEx).Line();
        return super.Line();
    }

    /**
     * Number
     * @return COM number of this error
     */
    public int Number()
    {
        if (nestedEx instanceof AspException)
            return ((AspException)nestedEx).Number();
        return super.Number();
    }

    /**
     * Source
     * @return Source where this error occured
     */
    public String Source()
    {
        if (nestedEx instanceof AspException)
            return ((AspException)nestedEx).Source();
        return super.Source();
    }

    /**
     * Clear this exception.
     */
    public void Clear()
    {
        nestedEx = Constants.emptyException;
    }
}
