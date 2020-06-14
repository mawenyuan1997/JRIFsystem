/* SyKAT.BDD.java
 * 
 * LightBDD is a library for handling Reduced Ordered Binary Decision Diagrams.
 * 
 */
package KATautomata.SyKAT.BDD;

import KATautomata.SyKAT.SyKATexpression;
import KATautomata.SyKAToperator.SyKATexpressionVisitor;
import polyglot.util.SerialVersionUID;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Reduced Ordered Binary Decision diagram.
 * 
 * For an explanation of some of the internals, see Henrik Reif Anderson,
 * "An Introduction to Binary Decision Diagrams," 1997:
 * http://www.cs.unb.ca/~gdueck/courses/cs6805/Notes/bdd97.pdf
 * 
 * @author Eric 'Siggy' Scott
 */
public class BDD<T> extends Executable<T> implements SyKATexpression, Serializable
{
//    private static final long serialVersionUID = SerialVersionUID.generate();
    /* If you add fields, don't forget to update the copy constructor! */
    private BDDTree<T> tree;
    private boolean isForAction = false;

    /**
     *  Manual constructor
     */
    public BDD(BDDTree<T> tree)
    {
        this.tree = tree;
    }

    /**
     * Deep copy constructor
     */
    public BDD(BDD<T> x)
    {
        this(x, false);
    }
    
    /**
     * Deep copy constructor
     * 
     * @param x Function to copy
     * @param negation If true, builds the compliment of x
     */
    public BDD(BDD<T> x, boolean negation)
    {
        this.tree = (negation ? x.tree.BuildNegation() : new BDDTree(x.tree));
    }
    
    /**
     * Builds a SyKAT.BDD out of the specified boolean function.
     * 
     * Based on Anderson (1997).
     * 
     * @param f Function to clone
     */
    
    public BDD(BooleanFunction f)
    {
        this.tree = new BDDTree(f.getNumInputs());
        buildThisFromFunction(f, new boolean[f.getNumInputs()], 0);
    }
    
    private int buildThisFromFunction(Executable f, boolean[] inputString, int inputIndex)
    {
        if (inputIndex == f.getNumInputs()) {
            Node leaf = new Node(f.execute(inputString), inputIndex);
            return mk(leaf);
        }
        else
        {
            inputString[inputIndex] = false;
            int lowChild = buildThisFromFunction(f, inputString, inputIndex + 1);
            inputString[inputIndex] = true;
            int highChild = buildThisFromFunction(f, inputString, inputIndex + 1);
            return mk(new Node(lowChild, highChild, inputIndex));
        }
    }

    /**
     * Build BDD for action
     * @param f
     * @param isForAction
     */
    public BDD(BooleanFunction f, boolean isForAction) {
        this(f);
        this.isForAction = isForAction;
    }
    /**
     * Build a SyKAT.BDD by *applying* the specified boolean operator to two preexisting
     * BDDs.
     * 
     */
    public BDD(Operator op, BDD<T> x, BDD<T> y)
    {
        assert x.tree.getNumInputs() == y.tree.getNumInputs();
        this.tree = new BDDTree(x.tree.getNumInputs());
        HashMap<Integer[], Integer> dynamicProgrammingMemory = new HashMap();
        apply(dynamicProgrammingMemory, op, x.tree, y.tree, x.tree.getRootIndex(), y.tree.getRootIndex());
    }
    
    /**
     * Build a new SyKAT.BDD by applying a operator to two existing ones.
     * See Anderson (1997) for an explanation of this algorithm.
     */
    private <T> void apply(HashMap dynamicProgrammingMemory, Operator<T> op, BDDTree<T> xTree, BDDTree<T> yTree, int xIndex, int yIndex)
    {
        assert xTree.getNumInputs() == yTree.getNumInputs();
        int newRoot = applyLoop(dynamicProgrammingMemory, op, xTree, yTree, xIndex, yIndex);
        tree.setRootIndex(newRoot); // Required in the case that the new tree returns false for all inputs (without this line it returns all trues!)
    }
    private <T> int applyLoop(HashMap dynamicProgrammingMemory, Operator<T> op, BDDTree<T> xTree, BDDTree<T> yTree, int xIndex, int yIndex)
    {
        Integer[] key = new Integer[] {xIndex,yIndex};
        Node<T> x = xTree.getNode(xIndex);
        Node<T> y = yTree.getNode(yIndex);
        int output;
        if (dynamicProgrammingMemory.containsKey(key))
            return (Integer) dynamicProgrammingMemory.get(key);
        else if (x.isTerminal() && y.isTerminal())
            output = mk(new Node(op.operate(x.terminalValue, y.terminalValue), xTree.getNumInputs()));
        else if (x.isTerminal())
            output = mk(new Node(applyLoop(dynamicProgrammingMemory, op, xTree, yTree, xIndex, y.low),applyLoop(dynamicProgrammingMemory, op, xTree, yTree, xIndex, y.high), y.inputIndex));
        else if (y.isTerminal())
            output = mk(new Node(applyLoop(dynamicProgrammingMemory, op, xTree, yTree, x.low, yIndex),applyLoop(dynamicProgrammingMemory, op, xTree, yTree, x.high, yIndex), x.inputIndex));
        else if (x.inputIndex == y.inputIndex)
            output = mk(new Node(applyLoop(dynamicProgrammingMemory, op, xTree, yTree, x.low, y.low), applyLoop(dynamicProgrammingMemory, op, xTree, yTree, x.high, y.high), x.inputIndex));
        else if (x.inputIndex < y.inputIndex)
            output = mk(new Node(applyLoop(dynamicProgrammingMemory, op, xTree, yTree, x.low, yIndex), applyLoop(dynamicProgrammingMemory, op, xTree, yTree, x.high, yIndex), x.inputIndex));
        else //(x.inputIndex > y.inputIndex)
            output = mk(new Node(applyLoop(dynamicProgrammingMemory, op, xTree, yTree, xIndex, y.low), applyLoop(dynamicProgrammingMemory, op, xTree, yTree, xIndex, y.high), y.inputIndex));
        dynamicProgrammingMemory.put(key, output);
        return output;
    }

    /**
     * Combination function for a bottom-up assembly of a SyKAT.BDD tree.
     * Again, see Anderson (1997) to understand why we do it this way.
     */
    private int mk(Node<T> node)
    {
        if (tree.contains(node))
            return tree.getNodeIndex(node);
        else
            return tree.addNode(node);
    }

    /**
     * Execute the boolean function represented by this SyKAT.BDD.
     */
    @Override
    public T execute(boolean[] input)
    {
        assert(input.length == this.tree.getNumInputs());
        Node<T> currentNode = tree.getNode(tree.getRootIndex());
        for (int i = 0; i < input.length; i++)
        {
//            System.out.println(""+currentNode.low+" "+currentNode.high+" "+currentNode.inputIndex);
            if (currentNode.inputIndex == i)
                currentNode = (input[i] ? tree.getNode(currentNode.high) : tree.getNode(currentNode.low));
        }

        return currentNode.terminalValue;
    }
    @Override
    public int getNumInputs()
    {
        return this.tree.getNumInputs();
    }

    public boolean isAction() { return isForAction; }

    public BDDTree getTree()
    {
        return this.tree;
    }

    /**
     *  Test whether this is equal to the reference SyKAT.BDD.  Runs a DFS-based
     *  rooted directed acyclic graph isomorphism algorithm, which
     *  is linear in the number of nodes.
     */
    @Override
    public boolean equals(Object reference)
    {
        if (! (reference instanceof BDD))
            return false;
        return this.tree.equals(((BDD)reference).getTree());
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 47 * hash + (this.tree != null ? this.tree.hashCode() : 0);
        return hash;
    }

    @Override
    public Object accept(SyKATexpressionVisitor visitor) { return visitor.visit((BDD<Boolean>) this); }

    public String toString() {
        if (isAction()) return "BDDAction";
        return "BDDTest";
    }
    /*
    @Override
    public String toDot(String name)
    {
        String dot = "digraph " + name + " {\n";
        dot += dotBody("");
        dot += "}";
        return dot;
    }
    
    @Override
    public String toSubDot(String prefix)
    {
        String dot = "subgraph cluster" + prefix + " {\n";
        dot += dotBody(prefix);
        dot += "}";
        return dot;
    }
    
    private String dotBody(String prefix)
    {
        String dot = "True [shape=box];\nFalse [shape=box];\n";
        int r = tree.getRootIndex();
        boolean[] visited = new boolean[r+1];
        for (int i = 0; i < r+1; i++)
            visited[i] = false;
        return dot + toDotDFS(r, prefix, tree.getNode(r), null, false, visited);
    }
    
    private String toDotDFS(int i, String prefix, Node currentNode, String parentName, boolean lowEdge, boolean[] visited)
    {
        String currentName = prefix + "Node" + i + "_Var" + currentNode.inputIndex;
        if (currentNode.isTerminal())
            return parentName + "->" + ((Boolean)currentNode.terminalValue ? "True" : "False") + (lowEdge ? "[style=dashed];\n" : ";\n");
        else
        {
            String output = "";
            if (parentName != null)
                output += parentName + "->" + currentName + (lowEdge ? "[style=dashed];\n" : ";\n");
            if (!visited[i])
            { // Only visit this nodes children if we haven't been down this route already
                output += toDotDFS(currentNode.low, prefix, tree.getNode(currentNode.low), currentName, true, visited);
                output += toDotDFS(currentNode.high, prefix, tree.getNode(currentNode.high), currentName, false, visited);
            }
            visited[i] = true;
            return output;
        }
            
    }
    */
}
