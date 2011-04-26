/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Category;

/**
 * This class handles an exception by printing out the stack trace
 * to the response handler.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class PrintExceptionHandler implements AspExceptionHandler
{
    /** Debugging class */
    static Category DBG = Category.getInstance(PrintExceptionHandler.class);

    /**
     * This function configures any properties
     */
    public void configureExceptionHandler(Properties prop)
    {
        /* Nothing to configure */
    }

    /**
     * This function is called when an exception occurs
     * @param ex Exception which occured
     */
    public boolean onExceptionOccured(AspContext ctx, AspException ex)
        throws AspException
    {
        try {
            Response rsp = ctx.getAspResponse();
            StringWriter string = new StringWriter();
            PrintWriter write = new PrintWriter(string);
            write.write("</table></table><pre>");
            ex.printStackTrace(write);
            write.write("</pre>");
            write.close();
            rsp.Write(string.toString());
        } catch (IOException ioex) {
            DBG.error("Error while displaying exception", ioex);
        }
        return true;
    }
}

