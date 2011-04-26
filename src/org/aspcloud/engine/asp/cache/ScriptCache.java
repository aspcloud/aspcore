/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp.cache;

import org.aspcloud.engine.asp.AspContext;

/**
 * This interface defines a "Script Cache", a caching object which can store
 * and retrieve scripts.
 */
public interface ScriptCache {
	/**
	 * Get the cached script object for the given filename/context.
	 * @param filename filename to obtain
	 * @param context context of script
	 * @return new or existing CachedScript object, never null
	 */
	CachedScript get(String filename, AspContext context);
}
