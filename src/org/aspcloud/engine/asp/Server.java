/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import org.apache.log4j.Category;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * The Server class implements the ASP object "Server", which contains
 * utility functions.
 * 
 * @author Terence Haddock
 * @version 0.9
 */
public class Server
{
    /**
     * Debugging category
     */
    static Category DBG = Category.getInstance(Server.class);

    /** Script timeout */
    public int ScriptTimeout = 90;

    /** Hashtable of all the object names configured */
    Hashtable configuredNames;

    /** Servlet context */
    ServletContext servletContext;

    /** Local ASP Context */
    AspContext localContext;

    /**
     * Constructor.
     * @param config ServletConfig configuration
     */
    public Server(ServletConfig config, AspContext localContext)
    {
        if (DBG.isDebugEnabled()) DBG.debug("Creating Server Class");
        if (config == null)
        {
            DBG.debug("NULL Config Received");
            return;
        }
        configuredNames = new Hashtable();
        Enumeration enNames = config.getInitParameterNames();
        while (enNames.hasMoreElements())
        {
            String name = (String)enNames.nextElement();
            if (DBG.isDebugEnabled()) DBG.debug("Name: " + name);
            if (name.startsWith("object.")) {
                configuredNames.put(new IdentNode(name),
                    config.getInitParameter(name));
            }
        }
        if (DBG.isDebugEnabled()) DBG.debug("Finish init-ting Server class");
        this.servletContext = config.getServletContext();
        this.localContext = localContext;
    }
    
    /**
     * CreateObject ASP function.
     * @param objID Object ID, usually "java:(java class name)"
     * @return new object which corresponds to the object ID
     */
    public Object CreateObject(String objID)
            throws ClassNotFoundException,
                InstantiationException,
                IllegalAccessException
    {
        if (DBG.isDebugEnabled())
            DBG.debug("Creating object: " + objID);
        /* If the name begins with "java:", take them as-is */
        if (objID.startsWith("java:")) {
            String javaClass = objID.substring(5);
            return Class.forName(javaClass).newInstance();
        }
        /* Pre-pend 'object.' to the string */
        String classID = "object." + objID;
        /* Convert to an ident node */
        IdentNode iClassID = new IdentNode(classID);
        /* Check for existence in the configured names */
        Object obj;
        if (configuredNames.containsKey(iClassID)) {
            String javaClass = (String)configuredNames.get(iClassID);
            obj = Class.forName(javaClass).newInstance();
        } else {
            /* Initialize the class */
            obj = Class.forName(classID).newInstance();
        }
        /* Store the object into the context's OnPageEnd object to call */
        if (localContext != null)
        {
            localContext.callOnPageStart(obj);
            localContext.addOnPageEnd(obj);
        } else {
            if (DBG.isDebugEnabled())
                DBG.debug("No localcontext to set up OnPageEnd");
        }
        return obj;
    }

    /**
     * HTML encodes a string.
     * @param str String
     * @return string HTML-encoded
     */
    public static String htmlEncode(String str)
    {
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            switch(ch) {
                case '<':buf.append("&lt;");break;
                case '>':buf.append("&gt;");break;
                case '&':buf.append("&amp;");break;
                case '"':buf.append("&quot;");break;
                default:buf.append(ch);
            }
        }
        return buf.toString();
    }

    /**
     * URL Encodes a string.
     * @param str String
     * @return String, URL encoded
     */
    public static String URLEncode(String str)
    {
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            switch(ch) {
                case ' ':buf.append("+");break;
                case '?':buf.append("%3F");break;
                case '&':buf.append("%26");break;
                case '%':buf.append("%25");break;
                case '=':buf.append("%3D");break;
                default:buf.append(ch);
            }
        }
        return buf.toString();
    }

    /**
     * ASP-accessable function to obtain the local path based on a virtual
     * path.
     *
     * @param path Virtual path
     * @return Absolute path
     */
    public String MapPath(String path)
    {
        return servletContext.getRealPath(path);
    }
};
