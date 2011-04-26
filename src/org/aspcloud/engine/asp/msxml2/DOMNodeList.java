/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp.msxml2;

import java.util.Collection;
import java.util.Vector;


import org.apache.log4j.Category;

/**
 * This class implements msxml2.DOMNodeList. 
 *
 * Contributed by Jim Horner &lt;jhorner@arinbe.com&gt;
 */
public class DOMNodeList {

	/** Debugging category. */
	Category DBG = Category.getInstance(DOMNodeList.class);
	
	private Vector children;

	public DOMNodeList () {
		children = new Vector();
	}
	
	/**
	 * ASP-Accessible function
	 *   @param index integer specifying the node index
	 * 	 @return the DOMNode at the index
	 */
	public DOMNode Item(int index) {
		
		DOMNode result = null;
		result = (DOMNode) children.get(index);

		return result;
	}

	/**
	 * ASP-Accessible function
	 *   @param node DOMNode to append to list
	 */
	public void AppendNode (DOMNode node) {
		children.add(node);
	}
	
	/**
	 * ASP-Accessible function
	 *   @return the size of the node list
	 */
	public int Length() {
		return children.size();
	}

	// cheat method
	public Collection Items() {
		return this.children;
	}

}
