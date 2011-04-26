/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import org.apache.log4j.Category;

import org.aspcloud.engine.asp.cache.CachedScript;
import org.aspcloud.engine.asp.cache.ScriptCache;
import org.aspcloud.engine.asp.parse.NestedTokenManager;
import org.aspcloud.engine.asp.parse.ParseException;
import org.aspcloud.engine.asp.parse.TokenMgrError;
import org.aspcloud.engine.asp.parse.VBScript;

/**
 * This class handles the parsing of ASP code.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspHandler
{
    static private final transient Category DBG = Category.getInstance(AspHandler.class);

    /**
     * Cache of pre-parsed files.
     */
    private ScriptCache cachedScripts = null;

    /**
     * Current ASP Context.
     */
    private AspContext context;

    /**
     * Display path to ASP file
     */
    private String filename;

    /**
     * Constructor.
     * @param file Full path to ASP file to parse.
     * @param filename Display path to ASP file to parse
     */
    public AspHandler(AspContext context, String filename, ScriptCache cache) throws AspNestedException
    {
        this.context = context;
        this.filename = filename;
        this.cachedScripts = cache;
    }

    /**
     * Parses the current file and returns the base node of the file.
     *
     * @return Node representation of parsed file.
     */
    public Node parse() throws AspException
    {
        CachedScript cachedScript = cachedScripts.get(filename, context);
        synchronized(cachedScript)
        {
            if (cachedScript.node != null)
            {
                long time = System.currentTimeMillis();
                if ((time - cachedScript.checkedTime) > 10000)
                {
                    cachedScript.checkedTime = time;
                    if (!cachedScript.fileFactory.isModified())
                        return cachedScript.node;
                    else
                        if (DBG.isDebugEnabled()) DBG.debug("File was modified, re-parsing");
                } else {
                    return cachedScript.node;
                }
            } else {
                if (DBG.isDebugEnabled()) DBG.debug("No node in cache");
            }
            try {
                if (DBG.isDebugEnabled())
                    DBG.debug("Opening token manager for " + filename);

                if (DBG.isDebugEnabled()) DBG.debug("Clearing cache");
                cachedScript.fileFactory.clearLoadedFilesCache();

                if (DBG.isDebugEnabled()) DBG.debug("Resolving file");
                String absoluteFilename = cachedScript.fileFactory.
                    resolveFile(null, filename, true);
                if (DBG.isDebugEnabled()) DBG.debug("Creating TokenManager");
                NestedTokenManager tokManager = new NestedTokenManager(
                    absoluteFilename, cachedScript.fileFactory);
                if (DBG.isDebugEnabled()) DBG.debug("Creating Parser");


                VBScript script = new VBScript(tokManager); //VBScript(tokManager);
                if (DBG.isDebugEnabled()) DBG.debug("Parsing file");
                Node value = script.WholeFile();

                if (DBG.isDebugEnabled()) DBG.debug("Updating values");
                cachedScript.node = value;
                cachedScript.checkedTime = System.currentTimeMillis();

                if (DBG.isDebugEnabled()) DBG.debug("Done.");
                return value;
            } catch (AspException ex) {
                throw ex;
            } catch (AspRuntimeSubException ex) {
                throw ex.getException();
            } catch (AspRuntimeException ex) {
                throw new AspNestedException(ex);
            } catch (TokenMgrError ex) {
                DebugContext ctx = new DebugContext(ex.getFilename(),
                    ex.getLineNo());
                throw new AspNestedException(ex, ctx);
            } catch (ParseException ex) {
                DebugContext ctx = new DebugContext();
                ex.currentToken.fillDebugContext(ctx);
                throw new AspNestedException(ex, ctx);
            }
        }
    }
}
