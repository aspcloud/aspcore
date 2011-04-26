/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp;

import java.util.Vector;

import org.apache.log4j.Category;

/**
 * BlockNode contains a block of ASP code, one or more lines of code.
 * 
 * @author Terence Haddock
 * @version 0.9
 */
public class BlockNode implements Node
{
    /** Debugging code */
    private static final transient Category DBG = Category.getInstance(BlockNode.class);

    /** List of blocks */
    Vector    blocks;

    /** List of debugging contexts corresponding to blocks */
    Vector    linenos;

    /**
     * Constructor, starting with no lines of code.
     */
    public BlockNode()
    {
        blocks = new Vector();
        linenos = new Vector();
    }

    /**
     * Obtains the number of blocks in this object.
     * @return number of blocks
     */
    public int size()
    {
        return blocks.size();
    }

    /**
     * Gets the block at the specified index.
     * @param i index
     * @return block
     */
    public Node getBlock(int index)
    {
        return (Node)blocks.get(index);
    }

    /**
     * Appends a line of code with the debugging context.
     *
     * @param obj Object to insert
     * @param lineno Context of this object
     */
    public void append(Object obj, DebugContext lineno)
    {
        blocks.add(obj);
        linenos.add(lineno);
    }

    /**
     * Dumps the contents of this block.
     *
     * @throws AspException if an error occurs
     * @see Node#dump()
     */
    public void dump() throws AspException
    {
        int i;
        System.out.print("{B}");
        for (i = 0; i < blocks.size(); i++)
        {
            Node node = (Node)blocks.get(i);
            if (node != null) {
                node.dump();
                if (!(node instanceof BlockNode)) {
                    System.out.println();
                }
            }
        }
    }

    /**
     * Prepares this node for execution.
     *
     * @param context Global context
     * @throws AspException if an error occurs.
     * @see Node#prepare(AspContext)
     */
    public void prepare(AspContext context) throws AspException
    {
        int i;
        for (i = 0; i < blocks.size(); i++)
        {
            try {
                Node node = (Node)blocks.get(i);
                if (node != null) {
                    node.prepare(context);
                }
            } catch (AspException ex) {
                DebugContext dbg = (DebugContext)linenos.get(i);
                context.processException(ex, dbg);
            }
        }
    }

    /**
     * Executes this block of code.
     *
     * @param context Scope in which to execute this code.
     * @return Returns and ExitScope string.
     * @throws AspException if an error occurs.
     * @see Node#execute(AspContext)
     */
    public Object execute(AspContext context) throws AspException
    {
        int i;
        for (i = 0; i < blocks.size(); i++)
        {
            Node node = (Node)blocks.get(i);
            try {
                AspThread.checkTimeout(context);
                if (node != null)
                {
                    if (DBG.isDebugEnabled()) {
                        DBG.debug("Executing line: " + linenos.get(i));
                    }
                    Object obj = node.execute(context);
                    if (obj instanceof FunctionNode) {
                        obj = ((FunctionNode)obj).execute(
                            new VarListNode(), context);
                    }
                }
            } catch (AspExitException ex) {
                throw ex;
            } catch (AspException ex) {
                DebugContext dbg = (DebugContext)linenos.get(i);
                context.processException(ex, dbg);
            } catch (Exception ex) {
                DebugContext dbg = (DebugContext)linenos.get(i);
                DBG.error("Error in block " + dbg, ex);
                context.processException(ex, dbg);
            }
        }
        return null;
    }
};

