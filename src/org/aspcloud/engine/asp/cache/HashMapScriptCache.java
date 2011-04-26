/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp.cache;

import java.util.Map;

import org.apache.log4j.Logger;

import org.aspcloud.engine.asp.AspContext;

/**
 * This class implements a "static" script cache, all scripts are stored in
 * an internal hashtable.
 */
public class HashMapScriptCache implements ScriptCache {
	private final Logger LOG = Logger.getLogger(HashMapScriptCache.class);
	
	private Map scriptCache = new java.util.HashMap();

	/**
	 * @see org.aspcloud.engine.asp.cache.ScriptCache#get(java.lang.String, org.aspcloud.engine.asp.AspContext)
	 */
	public synchronized CachedScript get(String filename, AspContext context) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Get: " + filename);
		}
		CachedScript script = (CachedScript)scriptCache.get(filename);
		if (script == null)
		{
			script = new CachedScript(context);
			scriptCache.put(filename, script);
		}
		return script;
	}
}
