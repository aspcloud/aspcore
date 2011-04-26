/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.util.Properties;
import org.apache.log4j.Category;

/**
 * This class handles an exception by setting the status on the response object.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class SetStatusExceptionHandler implements AspExceptionHandler
{
    /** Debugging class */
    static Category DBG = Category.getInstance(SetStatusExceptionHandler.class);

    /** Exception code */
    int status = 500;

    /**
     * This function configures any properties
     */
    public void configureExceptionHandler(Properties prop)
    {
        String statusStr = prop.getProperty("Status");
        if (statusStr != null) status = Integer.parseInt(statusStr);
    }

    /**
     * This function is called when an exception occurs
     * @param ex Exception which occured
     */
    public boolean onExceptionOccured(AspContext ctx, AspException ex)
        throws AspException
    {
        if (DBG.isDebugEnabled()) DBG.debug("Setting the response status to "
            + status);
        Response rsp = ctx.getAspResponse();
        rsp.Status(status);
        return true;
    }
}

