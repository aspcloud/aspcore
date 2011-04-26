/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp.msxml2;

import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Category;

/**
 * This class implements msxml2.DOMNamedNodeMap. 
 *
 * Contributed by Jim Horner &lt;jhorner@arinbe.com&gt;
 */
public class DOMNamedNodeMap {

	/** Debugging category. */
	Category DBG = Category.getInstance(DOMNamedNodeMap.class);
	
	private HashMap map;
	
	public DOMNamedNodeMap () {
		map = new HashMap();
	}

	public DOMNode getNamedItem(String name) {
		return (DOMNode) map.get(name);
	}
	
	public DOMNode Item(int index) {
		// might need to use something other than a map
		return null;
	}
	
	public DOMNode SetNamedItem(DOMNode attribute) {
		
		map.put(attribute._NodeName, attribute);

		return attribute;
	}	

	public int Length() {
		return this.map.size();
	}
		
	// cheat method
	public Collection Items(){
		return map.values();
	}
	
}
