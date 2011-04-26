/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import org.apache.log4j.Category;

import org.aspcloud.engine.asp.cache.ScriptCache;

/**
 * AspThread performs the actual processing of an ASP process.
 * @author Terence Haddock
 * @version 0.9
 */
public class AspThread implements Runnable
{
	/** Debugging category */
    private Category DBG = Category.getInstance(AspThread.class);

    /** Running context */
    AspContext context;

    /** Filename of ASP script */
    String filename;

    /** Exception thrown during execution */
    AspException ex = null;
	
	/** Cache of compiled scripts */    
	private ScriptCache cache;

    /**
     * Constructor.
     * @param context Global context
     * @param filename Filename of ASP code
     */
    public AspThread(AspContext context, String filename, ScriptCache cache)
    {
        this.context = context;
        this.filename = filename;
        this.cache = cache;
    }

    /**
     * Executes this thread, processing the ASP code.
     */
    public void run()
    {
        try {
            AspHandler handler = new AspHandler(context, filename, cache);
            if (DBG.isDebugEnabled()) DBG.debug("Parsing");
            Node node = handler.parse();

            if (DBG.isDebugEnabled()) DBG.debug("Preparing");
            node.prepare(context);

            if (DBG.isDebugEnabled()) DBG.debug("Executing");
            node.execute(context);
        
            if (DBG.isDebugEnabled()) DBG.debug("Done");
        } catch (AspException ex)
        {
            if (DBG.isDebugEnabled()) DBG.debug("AspException");
            if (!(ex instanceof AspExitScriptException))
                DBG.error("AspException", ex);
            this.ex = ex;
        } catch (RuntimeException ex)
        {
            if (DBG.isDebugEnabled()) DBG.debug("RuntimeException");
            DBG.error("Uncaught RuntimeException", ex);
        } catch (Exception ex)
        {
            if (DBG.isDebugEnabled()) DBG.debug("Exception");
            DBG.error("Uncaught exception", ex);
        }
    }

    /**
     * Static class which checks if a timeout has occured.
     * @param fileScope file-specific scope
     * @throws AspTimeoutException if a timeout has occured
     */
    public static void checkTimeout(AspContext context) throws AspException
    {
        IdentNode timeout = new IdentNode("!timeout");
        if (context.inScope(timeout)) {
            throw new AspTimeoutException();
        }
    }

    /**
     * Static method which marks that a timeout has occured
     * @param fileScope file-specific scope
     */
    public static void timeout(AspContext globalScope) throws AspException
    {
        IdentNode timeout = new IdentNode("!timeout");
        /* This will go into the global context */
        /* This forceScope statement is here to prevent an exception when
           option explicit is in effect */
        globalScope.forceScope(timeout);
        globalScope.setValue(timeout, "Yes");
    }

    /**
     * Obtains the exception thrown.
     * @return exception which was thrown, or null if none was thrown.
     */
    public Exception getException()
    {
        return ex;
    }
}
