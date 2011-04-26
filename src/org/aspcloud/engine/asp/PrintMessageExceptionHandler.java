/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import org.apache.log4j.Category;
import java.io.IOException;
import java.util.Properties;

/**
 * This class handles an exception by printing out the stack trace
 * to the response handler.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class PrintMessageExceptionHandler implements AspExceptionHandler
{
    /** Debugging class */
    static Category DBG = Category.getInstance(PrintExceptionHandler.class);

    /** The message to display */
    String message;

    /**
     * This function configures any properties
     */
    public void configureExceptionHandler(Properties prop)
    {
        this.message = prop.getProperty("Message");
        if (this.message == null)
        {
            throw new RuntimeException("Message not defined");
        }
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
            rsp.Write(message);
        } catch (IOException ioex) {
            DBG.error("Error while displaying exception", ioex);
        }
        return true;
    }
}

