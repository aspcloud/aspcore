/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.io.IOException;

/**
 * HTMLNode handles a normal HTML %&gt; ... &lt;% "output" node.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class HTMLNode implements Node
{
    /** Text string of the HTML */
    String html;

    /**
     * Constructor.
     *
     * @param inHtml HTML string of this node.
     */
    public HTMLNode(String inHtml)
    {
        html = inHtml;
    }

    /**
     * Dumps this node.
     * @see Node#dump()
     */
    public void dump()
    {
        System.out.print("%>" + html + "<%");
    }

    /**
     * Prepares this node for execution.
     * @param context AspContext this node will be executed under.
     * @see Node#prepare(AspContext)
     */
    public void prepare(AspContext context)
    {
        /* Nothing to do */
    }

    /**
     * Executes this node within the specified context. This node simply
     * calls the Response.Write routine within the current context
     * to output this node.
     * @param context AspContext to execute this node under.
     * @see Node#execute(AspContext)
     * @throws AspException if an error occurs
     */
    public Object execute(AspContext context) throws AspException
    {    
        final IdentNode respIdent = new IdentNode("response");
        JavaObjectNode respObj = (JavaObjectNode)context.getValue(respIdent);
        Response resp = (Response)respObj.getSubObject();
        try {
            resp.Write(html);
        } catch (IOException ex) {
            throw new AspNestedException(ex);
        }

        return null;
    }
};

