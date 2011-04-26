/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * This class represents a string in the parsed ASP code.
 *
 * @author Terence Haddock
 */
public class StringNode extends DefaultNode
{
    /** String this string node contains */
    String string;

    /**
     * Constructor.
     * @param string String value
     */
    public StringNode(Object string)
    {
        this.string = (String)string;
    }

    /**
     * Dumps this string value.
     * @see DefaultNode#dump
     */
    public void dump()
    {
        System.out.print(string);
    }

    /**
     * Get the string value.
     * @return string value
     */
    public String getString()
    {
        return string;
    }

    /**
     * Executes this string, returns the string value stripped of the
     * surrounding quotes and any escape sequences.
     * @param context ASP Context
     * @return Strng value
     * @see DefaultNode#execute(AspContext)
     */
    public Object execute(AspContext context)
    {
        return string;
        /*String substr = string.substring(1, string.length() - 1);
        int index = substr.indexOf("\"\"");
        while (index >= 0) {
            substr = substr.substring(0, index) + substr.substring(index+1);
            index = substr.indexOf("\"\"", index+1);
        }
        return substr;*/
    }

    /**
     * Construct a string node from a string token.
     * @param str String token
     */
    public static StringNode fromStringToken(String str)
    {
        String substr = str.substring(1, str.length() - 1);
        int index = substr.indexOf("\"\"");
        while (index >= 0) {
            substr = substr.substring(0, index) + substr.substring(index+1);
            index = substr.indexOf("\"\"", index+1);
        }
        return new StringNode(substr);
    }
};

