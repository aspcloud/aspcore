/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp.msxml2;


import org.apache.log4j.Category;

/**
 * This class implements msxml2.DOMElement.
 *
 * Contributed by Jim Horner &lt;jhorner@arinbe.com&gt;
 */
public class DOMElement extends DOMNode {
	
	/** Debugging category. */
	Category DBG = Category.getInstance(DOMElement.class);
	
	public DOMElement () {
		super();
		this._Attributes = new DOMNamedNodeMap();
	}
	
	public String TagName() {	
		return this._NodeName;
	}
}
