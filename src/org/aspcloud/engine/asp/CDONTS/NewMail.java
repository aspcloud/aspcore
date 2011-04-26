/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp.CDONTS;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import org.aspcloud.engine.asp.AspException;
import org.apache.log4j.Category;

/**
 * This class implements the CDONTS.NewMail ASP Object.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class NewMail
{
    /** Debugging context */
    static Category DBG = Category.getInstance(NewMail.class);

    /** Which server to use for sending the mail */
    public String Host = null;

    /** Who this mail should be from */
    public String From = null;

    /** Who this mail should be to */
    public String To = null;

    /** The subject of the mail */
    public String Subject = null;

    /** The body of the mail */
    public String Body = null;

    /**
     * This ASP-Accessible function sends the mail.
     */
    public void Send() throws AspException
    {
        try {
            Properties props = new Properties();
            if (Host != null) {
                props.put("mail.smtp.host", Host);
            }
            Session session = Session.getDefaultInstance(props, null);
            Message msg = new MimeMessage(session);
            try {
                msg.setFrom(new InternetAddress(From));
            } catch (AddressException ex) {
                throw new AspException("Invalid From address: " + From);
            }
            try {
                msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(To, false));
            } catch (AddressException ex) {
                throw new AspException("Invalid To address: " + To);
            }
            msg.setSubject(Subject);
            msg.setText(Body);
            msg.setSentDate(new java.util.Date());
            Transport.send(msg);
            DBG.debug("Sent mail to " + To);
        } catch (MessagingException ex) {
            throw new AspException("Exception while sending message: " + ex);
        }
    }
}
