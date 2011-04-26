/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Category;

/**
 * This exception only handles a "FileNotFound" exception by sending
 * a file not found error report.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class FileNotFoundExceptionHandler implements AspExceptionHandler
{
    /** Debugging class */
    static Category DBG = Category.getInstance(FileNotFoundExceptionHandler.class);

    /**
     * This function configures any properties
     */
    public void configureExceptionHandler(Properties prop)
    {
    }

    /**
     * This function is called when an exception occurs
     * @param ctx ASP context
     * @param ex Exception which occured
     * @return <b>true</b> on non-file not found exceptions, <b>false</b> on
     *  File not found exceptions.
     */
    public boolean onExceptionOccured(AspContext ctx, AspException ex)
        throws AspException
    {
        if (ex instanceof AspNestedException)
        {
            AspNestedException nex = (AspNestedException)ex;
            /* This is a little bit of a hack, but oh well. */
            if ((nex.Line() == 0) &&
                (nex.nestedEx instanceof FileNotFoundException))
            {
                /* Grab the filename */
                final IdentNode filenameIdent = new IdentNode("!filename");
                String filename = (String)ctx.getValue(filenameIdent);
                /* Grab the response object */
                Response rsp = ctx.getAspResponse();
                HttpServletResponse servRest = rsp.getHttpServletResponse();
                try {
                    servRest.sendError(404, filename);
                } catch (IOException ioex) {
                    throw new AspNestedException(ioex);
                }
                return false;
            }
        }
        return true;
    }
}

