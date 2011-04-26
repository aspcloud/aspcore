/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import org.apache.log4j.Category;

/**
 * Implements a conditional node. IF .. THEN .. ELSE .. END IF
 * 
 * @author Terence Haddock
 * @version 0.9
 */
public class ConditionalNode implements Node
{
    /** Debugging category */
    private static final transient Category        DBG = Category.getInstance(ConditionalNode.class);

    /** Condition to test */
    Node            condition;

    /** Code to evaluate if the condition is true */
    Node            trueNode;

    /** Code to evaluate if the condition is false, <b>null</b> if no code. */
    Node            falseNode;

    /** Context of this conditional node */
    DebugContext        context;

    /** 
     * Conditional node constructor.
     *
     * @param condition Condition to test.
     * @param trueNode node to execute if condition is true
     * @param falseNode node to execute if condition is false
     * @param context Context of this conditional node
     */
    public ConditionalNode(Object condition, Object trueNode, Object falseNode, DebugContext context)
    {
        this.condition = (Node)condition;
        this.trueNode = (Node)trueNode;
        this.falseNode = (Node)falseNode;
        this.context = context;
    }

    /**
     * Gets the condition this node is based on.
     * @return condition
     */
    public Node getCondition()
    {
        return condition;
    }

    /**
     * Gets the true node, null for none
     * @return true node
     */
    public Node getTrueNode()
    {
        return trueNode;
    }

    /**
     * Gets the false node, null for none
     * @return false node
     */
    public Node getFalseNode()
    {
        return falseNode;
    }

    /**
     * Dumps the output of this conditional node.
     * @throws AspException if any error occurs.
     * @see Node#dump()
     */
    public void dump() throws AspException
    {
        System.out.print("if ");
        condition.dump();
        System.out.println(" then");
        trueNode.dump();
        if (falseNode != null) {
            System.out.print("else\n");
            falseNode.dump();
        }
        System.out.println("end if");
    }

    /**
     * Prepares the nodes for execution.
     *
     * @param context Global context
     * @throws AspException if any error occurs.
     * @see Node#prepare(AspContext)
     */
    public void prepare(AspContext context) throws AspException
    {
        /* Prepare the true node */
        trueNode.prepare(context);
        /* Prepare the false node, if non-null */
        if (falseNode != null) falseNode.prepare(context);
    }

    /**
     * Executes this conditional node.
     * @param context AspContext to evaluate this node under.
     * @return Result of this conditional node.
     * @throws AspException if any error occurs.
     * @see Node#execute(AspContext)
     */
    public Object execute(AspContext ctx) throws AspException
    {
        try {
            Object obj = condition.execute(ctx);
            if (DBG.isDebugEnabled()) {
                DBG.debug("Condition: " + condition);
                DBG.debug("Result: " + obj);
                DBG.debug("Result Class: " + obj.getClass());
                DBG.debug("Result context: " + context);
            }
            Boolean i = Types.coerceToBoolean(obj);
            if (i.booleanValue()) {
                return trueNode.execute(ctx);
            } else if (falseNode != null) {
                return falseNode.execute(ctx);
            }
        } catch (AspException ex) {
            if (!ex.hasContext()) ex.setContext(context);
            throw ex;
        } catch (Exception ex) {
            DBG.error("Error in execute: ", ex);
            throw new AspNestedException(ex, context);
        }
        return null;
    }
};

