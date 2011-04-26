
package org.aspcloud.engine.asp.parse;

/**
 * This class is an interface to the TokenManager interface for the
 * VBScriptTokenManager class.
 */
public class VBScriptTokenManagerInterface extends VBScriptTokenManager
    implements TokenManager
{
    /**
     * Constructor, initialized with the given stream.
     */
    public VBScriptTokenManagerInterface(SimpleCharStream stream)
    {
        super(stream);
    }
}

