/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * Special exception class which returns blank for all values.
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class AspEmptyException extends AspException
{

    /**
     * Constructor
     */
    public AspEmptyException()
    {
        super("Empty");
    }

    /**
     * AspCode
     * @return AspCode of this error
     */
    public int AspCode()
    {
        return 0;
    }

    /**
     * AspDescription
     * @return Detailed description of this error
     */
    public String AspDescription()
    {
        return "";
    }

    /**
     * Description
     * @return Short Description of this error
     */
    public String Description()
    {
        return "";
    }

    /**
     * Category
     * @return Category of this error 
     */
    public int Category()
    {
        return 0;
    }

    /**
     * Column
     * @return Column where this error occured
     */
    public int Column()
    {
        return 0;
    }

    /**
     * File
     * @return File where this error occured
     */
    public String File()
    {
        return "";
    }

    /**
     * Line
     * @return Line number where this error occured
     */
    public int Line()
    {
        return 0;
    }

    /**
     * Number
     * @return COM number of this error
     */
    public int Number()
    {
        return 0;
    }

    /**
     * Source
     * @return Source where this error occured
     */
    public String Source()
    {
        return "";
    }
}
