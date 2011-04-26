/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.io.Serializable;

/**
 * Debugging context, filename and line number.
 * 
 * @author Terence Haddock
 * @version 0.9
 */
public class DebugContext implements Serializable
{
    /** Display filename */
    String displayFilename;

    /** Line number */
    int lineno;

    /** Starting column number */
    int columnno;

    /**
     * Constructor.
     * @param displayFilename File name
     * @param lineno Line number
     */
    public DebugContext(String displayFilename, int lineno)
    {
        this.displayFilename = displayFilename;
        this.lineno = lineno;
        this.columnno = -1;
    }

    /**
     * Constructor.
     * @param displayFilename File name
     * @param lineno Line number
     * @param column Column number
     */
    public DebugContext(String displayFilename, int lineno, int columnno)
    {
        this.displayFilename = displayFilename;
        this.lineno = lineno;
        this.columnno = columnno;
    }

    /**
     * Constructor, without a defined filename/lineno.
     */
    public DebugContext()
    {
        this.displayFilename = null;
        this.lineno = 0;
        this.columnno = 0;
    }

    /**
     * Set the location filename.
     * @param filename Filename to set for the debugging context
     */
    public void setFilename(String filename)
    {
        this.displayFilename = filename;
    }

    /**
     * Get the location filename.
     * @param filename Filename for debugging location.
     */
    public String getFilename()
    {
        return displayFilename;
    }

    /**
     * Set the location line number.
     * @param lineno Line number to set for the debugging context
     */
    public void setLineNo(int lineno)
    {
        this.lineno = lineno;
    }

    /**
     * Get the location line number.
     * @return location line number.
     */
    public int getLineNo()
    {
        return lineno;
    }

    /**
     * Set the location column number.
     * @param columnno Column number to set for the debugging context
     */
    public void setColumnNo(int lineno)
    {
        this.columnno = columnno;
    }

    /**
     * Get the column number.
     * @return location column number.
     */
    public int getColumnNo()
    {
        return columnno;
    }

    /**
     * Obtain the string representation of this debugging context.
     * @return string representation of this debugging context.
     */
    public String toString()
    {
        if (columnno != -1)
            return "file: " + displayFilename + " line: " + lineno + " column: " + columnno;
        return "file: " + displayFilename + " line: " + lineno;
    }
};

