/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp.cache;

import java.io.Serializable;

import org.aspcloud.engine.asp.AspContext;
import org.aspcloud.engine.asp.AspFileFactory;
import org.aspcloud.engine.asp.Node;

/**
 * This object contains information about a cached script.
 */
public class CachedScript implements Serializable {
	/** File factory for this script */
	public AspFileFactory fileFactory;

	/** Node representation of script */
	public Node node;

	/** Last time file was checked */
	public long checkedTime;
	
	public CachedScript(AspContext context) {
		fileFactory = new AspFileFactory(context);
	}
}
