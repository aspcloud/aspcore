/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import org.aspcloud.engine.asp.CDONTS.NewMail;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import org.apache.log4j.Category;

/**
 * This class handles an exception by printing out the stack trace
 * to the response handler.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class MailExceptionHandler implements AspExceptionHandler
{
    /** Debugging class */
    static Category DBG = Category.getInstance(MailExceptionHandler.class);

    /** Who should this message be from? */
    String msgFrom;

    /** Who should this message be to? */
    String msgTo;

    /** What should be the subject? */
    String msgSubject;

    /**
     * This function configures any properties
     */
    public void configureExceptionHandler(Properties prop) throws AspException
    {
        DBG.debug("configureExceptionHandler");
        msgFrom = prop.getProperty("From", "arrowhead");
        msgTo = prop.getProperty("To");
        if (msgTo == null) throw new AspException("Message To not defined");
        msgSubject = prop.getProperty("Subject", "An error has occured in the ArrowHead application");
    }

    /**
     * This function is called when an exception occurs
     * @param ex Exception which occured
     */
    public boolean onExceptionOccured(AspContext ctx, AspException ex)
        throws AspException
    {
        try {
            Session session = ctx.getAspSession();
            Server server = ctx.getAspServer();
            Request request = ctx.getAspRequest();

            String IPAddress = (String)request.ServerVariables.get("REMOTE_ADDR");
            Object forwardedFor = request.ServerVariables.get("HTTP_X_FORWARDED_FOR");
            String sessionID = "No session";
            session.getSessionIfExists();
            if (session.httpSession != null)
            {
                sessionID = session.httpSession.getId();
            }
            NewMail mail;
            try {
                mail = (NewMail)server.CreateObject("CDONTS.NewMail");
            } catch (ClassNotFoundException cex) {
                throw new AspException("CDONTS.NewMail is not installed");
            } catch (Exception cex) {
                throw new AspException("Error creating CDONTS.NewMail");
            }
            mail.From = msgFrom;
            mail.To = msgTo;
            mail.Subject = msgSubject;

            StringWriter msgBody = new StringWriter();
            msgBody.write("An error has occured in the application.\n");
            msgBody.write("SessionID: ");msgBody.write(sessionID);msgBody.write("\n");
            if (forwardedFor instanceof String)
            {
                msgBody.write("Forward For: ");msgBody.write((String)forwardedFor);msgBody.write("\n");
            }
            msgBody.write("IP Address: ");msgBody.write(IPAddress);msgBody.write("\n");
            msgBody.write("The exception was:\n");
            msgBody.flush();
            PrintWriter pr = new PrintWriter(msgBody);
            ex.printStackTrace(pr);
            pr.flush();
            msgBody.flush();
            
            mail.Body = msgBody.toString();
            mail.Send();
        } catch (AspException aspEx) {
            DBG.error("Error during MailExceptionHandler", aspEx);
        }
        return true;
    }
}

