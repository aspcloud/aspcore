/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

/**
 * Implements the looping constructs, DO .. UNTIL, WHILE ... DO,
 * WHILE .. WEND, etc...
 *
 * @author Terence Haddock
 * @version 0.9
 */
public class DoNode implements Node
{
    /** Expression to test */
    Node        expr;

    /** Block to execute */
    BlockNode    code;

    /** Check expression after loop? */
    boolean        checkAfter;

    /** DO .. UNTIL, or DO .. WHILE? */
    boolean        doUntil;

    /**
     * Constructor.
     *
     * @param expr Expression to test.
     * @param code Code to execute in loop.
     * @param checkAfter check after loop?
     * @param doUntil DO .. UNTIL or DO .. WHILE?
     */
    public DoNode(Node expr, BlockNode code, boolean checkAfter,
                boolean doUntil)
    {
        this.expr = expr;
        this.code = code;
        this.checkAfter = checkAfter;
        this.doUntil = doUntil;
    }

    /**
     * Dumps this node.
     * @throws AspException if an error occurs
     * @see Node#dump() 
     */
    public void dump() throws AspException
    {
        if (!checkAfter) {
            System.out.print("DO ");
            if (doUntil) {
                System.out.print("UNTIL");
            } else {
                System.out.print("WHILE");
            }
            expr.dump();
            System.out.println();
        }
        code.dump();
        System.out.print("LOOP");
        if (checkAfter) {
            if (doUntil) {
                System.out.print("UNTIL ");
            } else {
                System.out.print("WHILE ");
            }
            expr.dump();
            System.out.println();
        }
        
    }

    /**
     * Prepares this node for executtion.
     * @param context Global context
     * @throws AspException if an error occurs
     * @see Node#prepare(AspContext)
     */
    public void prepare(AspContext context) throws AspException
    {
        code.prepare(context);
    }

    /**
     * Executes this node
     * @param context Current context
     * @return result of this node's execution
     * @throws AspException if an error occurs
     * @see Node#prepare(AspContext)
     */
    public Object execute(AspContext context) throws AspException
    {
        if (!checkAfter) {
            // Check when we start.
            Object obj = expr.execute(context);
            Boolean val = Types.coerceToBoolean(obj);
            if (doUntil && val.booleanValue()) return null;
            if (!doUntil && !val.booleanValue()) return null;
        }
        do {
            // Execute the sub-expression
            try {
                code.execute(context);
            } catch (AspExitDoException ex) {
                // Early exit
                return null;
            }
            // Check the values now
            Object obj = expr.execute(context);
            Boolean val = Types.coerceToBoolean(obj);
            if (doUntil && val.booleanValue()) return null;
            if (!doUntil && !val.booleanValue()) return null;
        } while (true);
    }
};

