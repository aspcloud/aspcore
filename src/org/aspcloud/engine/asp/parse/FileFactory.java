
package org.aspcloud.engine.asp.parse;

import org.aspcloud.engine.asp.*;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;

/**
 * This interface defines a file factory class, a class which can
 * obtain input streams from a file source.
 */
public interface FileFactory
{
    /**
     * Resolves a relative file based on a base file
     * @param context Asp context
     * @param baseFile base file, null means no base file
     * @param relFile relative file
     * @param virtual Is this a virtual reference?
     */
    public String resolveFile(String baseFile, String relFile, boolean virtual)
        throws AspException;

    /** 
     * Get the defined resource.
     * @param filename filename or URL
     */
    public Reader getResource(String filename)
        throws AspException;
}
