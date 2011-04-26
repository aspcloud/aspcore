/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp.msxml2;

/**
 * This class implements msxml2.DOMParseError. 
 *
 * Contributed by Jim Horner &lt;jhorner@arinbe.com&gt;
 */
public class DOMParseError {
	
	private String _Reason;

	public DOMParseError () {
		this._Reason = "No Error.";
	}

	public String Reason() {
		return this._Reason;
	}
	
	public void setReason(String msg) {
		this._Reason = msg;
	}
}
