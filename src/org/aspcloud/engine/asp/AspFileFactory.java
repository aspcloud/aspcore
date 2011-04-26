/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jregex.Pattern;

import org.apache.log4j.Category;

import org.aspcloud.engine.asp.parse.FileFactory;

/**
 * This class is a factory to handle finding files on the local file
 * system. It is used by the other test routines.
 */
public class AspFileFactory implements FileFactory, Serializable
{
    /** Debugging class */
    private static final transient Category DBG = Category.getInstance(AspFileFactory.class);
    
    private transient AspContext context;

    /** Map of files loaded using this file factory, used to determine if
        any of the files have changed. */
    Map loadedFiles = new HashMap();

    /**
     * Constructor, initialized with an AspContext.
     * @param context AspContext to initiaze to
     */
    public AspFileFactory(AspContext context)
    {
        this.context = context;
    }
    
    public void setContext(AspContext context)
    {
        this.context = context;
    }

    /**
     * Resolve a relative file's location based on the given file.
     * @param context Asp Context
     * @param baseFile base file to use.
     * @param relFile relative file to use.
     */
    public String resolveFile(String baseFilename, String relFile,
        boolean virtual) throws AspException
    {
        if (DBG.isDebugEnabled())
            DBG.debug("Resolve file: " + relFile + " / Base: " + baseFilename +
                 " / Virtual: " + virtual);
        String absFilename;
        if (isSeparatorChar(relFile.charAt(0)))
        {
            if (DBG.isDebugEnabled()) DBG.debug("Virtual file");
            /* Absolute filename */
            if (virtual)
            {
                Server server = context.getAspServer();
                absFilename = server.MapPath(relFile);
            } else {
                absFilename = relFile;
            }
        } else {
            /* Relative filename */
            File baseFile = new File(baseFilename);
            String path;
            String basePath = baseFile.getParent();
            if (basePath == null) basePath = "";
            absFilename = concatPath(basePath, relFile);
        }
        if (DBG.isDebugEnabled()) DBG.debug("Result: " + absFilename);
        return absFilename;
    }

    private final static boolean isSeparatorChar(char ch)
    {
        if (ch == File.separatorChar || ch == '/') return true;
        return false;
    }

    /**
     * Internal utility function to concatenate two paths.
     * @param absPath Absolute path to start with
     * @param addPath Relative path to add
     */
    private static String concatPath(String absPath, String addPath)
        throws AspException
    {
        if (DBG.isDebugEnabled()) {
            DBG.debug("concatPath(" + absPath + "," + addPath + ")");
        }
        final Pattern pathSep = new Pattern("/|\\\\");
        String absPathStrings[] = pathSep.tokenizer(absPath).split();
        String addPathStrings[] = pathSep.tokenizer(addPath).split();
        String newPathStrings[] = new String[absPathStrings.length +
            addPathStrings.length];
        int absPathPos = absPathStrings.length - 1;
        int addPathPos = 0;
        int newPathPos = 0;
        for (int i = 0; i < absPathStrings.length; i++) {
            newPathStrings[i] = absPathStrings[i];
            newPathPos++;
        }
        if (DBG.isDebugEnabled()) DBG.debug("newPathPos: " + newPathPos);
        for (int i = 0; i < addPathStrings.length; i++) {
            if (addPathStrings[i].equalsIgnoreCase(".")) continue;
            if (addPathStrings[i].equalsIgnoreCase(".."))
            {
                if (newPathPos == 0) throw new AspException("Invalid path: " + absPath + File.pathSeparator + addPath);
                newPathPos--;
                continue;
            }
            newPathStrings[newPathPos++] = addPathStrings[i];
        }
        if (DBG.isDebugEnabled()) DBG.debug("newPathPos: " + newPathPos);
        StringBuffer buf = new StringBuffer();
        if (isSeparatorChar(absPath.charAt(0)))
        {
            buf.append(File.separatorChar);
        }
        for (int i = 0; i < newPathPos; i++)
        {
            if (i != 0) buf.append(File.separatorChar);
            buf.append(newPathStrings[i]);
        }
        if (DBG.isDebugEnabled()) DBG.debug("Final path: " + buf.toString());
        return buf.toString();
    }

    /**
     * Get the file resource.
     * @param file file to obtain stream of
     * @return input stream of the specified file
     */
    public Reader getResource(String filename) throws AspException
    {
        try {
            Server server = context.getAspServer();
            FileInformation finfo = getFileInformation(filename);
            InputStream is = new FileInputStream(finfo.file);
            return new InputStreamReader(is, "UTF-8");
        } catch (IOException ex) {
            throw new AspNestedException(ex);
        }
    }

    /**
     * Clear the loaded files cache.
     */
    public void clearLoadedFilesCache()
    {
        loadedFiles = new HashMap();
    }

    /**
     * Check if any of the cached files have been modified on the disk.
     * @return <b>true</b> if a file has been modified, <b>false</b> otherwise.
     */
    public boolean isModified()
    {
        synchronized(loadedFiles)
        {
            for (Iterator i = loadedFiles.values().iterator(); i.hasNext();)
            {
                FileInformation finfo = (FileInformation)i.next();
                if (DBG.isDebugEnabled())
                {
                    DBG.debug("finfo.file.LastModified() = " +
                        finfo.file.lastModified());
                    DBG.debug("finfo.lastModifiedTime = " +
                        finfo.lastModifiedTime);
                }
                if (finfo.file.lastModified() > finfo.lastModifiedTime)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get the cached file information for the file.
     */
    private FileInformation getFileInformation(String fileLocation)
    {
        synchronized(loadedFiles)
        {
            if (!loadedFiles.containsKey(fileLocation))
            {
                FileInformation finfo = new FileInformation();
                finfo.file = new File(fileLocation);
                finfo.lastModifiedTime = finfo.file.lastModified();
                loadedFiles.put(fileLocation, finfo);
            }
            return (FileInformation)loadedFiles.get(fileLocation);
        }
    }

    /**
     * Class to contain cached file information.
     */
    private static class FileInformation implements Serializable
    {
        /** File handle */
        File file;

        /** Last modified time */
        long lastModifiedTime;
    }
}
