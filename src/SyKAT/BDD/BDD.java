/* SyKAT.BDD.java
 * 
 * LightBDD is a library for handling Reduced Ordered Binary Decision Diagrams.
 * 
 */
package SyKAT.BDD;

import SyKAT.SyKATexpression;
import SyKAToperator.SyKATexpressionVisitor;

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
public class BDD<T> extends Executable<T> implements SyKATexpression
{   
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

    /**
     * Build BDD for action
     * @param f
     * @param isForAction
     */
    public BDD(BooleanFunction f, boolean isForAction) {
        this(f);
        this.isForAction = isForAction;
    }
    
    private int buildThisFromFunction(Executable f, boolean[] inputString, int inputIndex)
    {
        if (inputIndex == f.getNumInputs()) {
            Node leaf = new Node(-1, -1, inputIndex);
            leaf.terminalValue = f.execute(inputString);
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

    @Override
    public Object accept(SyKATexpressionVisitor visitor) { return visitor.visit(this); }


       
    /**
     * Build a SyKAT.BDD by *applying* the specified boolean operator to two preexisting
     * BDDs.
     * 
     */
    public BDD(Operator op, BDD<T> x, BDD<T> y)
    {
        this.tree = new BDDTree(x.tree.getNumInputs());
        HashMap<Integer[], Integer> dynamicProgrammingMemory = new HashMap();
        apply(dynamicProgrammingMemory, op, x.tree, y.tree, x.tree.getRootIndex(), y.tree.getRootIndex());
    }
    
    /**
     *  Build a SyKAT.BDD by *restricting* (fixing or, rather, ignoring) one of the
     *  inputs of a pre-existing SyKAT.BDD.
     */
    public BDD(BDD x, int inputToFix, boolean value)
    {
        this.tree = new BDDTree(x.tree);
        int newRoot = restrict(x.tree, x.tree.getRootIndex(), inputToFix, value, new HashMap<Integer, Integer>());
        tree.setRootIndex(newRoot);
    }
    
    /**
     * Elementary composition.  If |f1| and |f2| are the number of inputs of the
     * argument BDDs, the result has |f1| + |f2| - 1 inputs.
     * 
     * @param var The output of f2 is fed into the varth input of f1
     * @param f1 "Female" function
     * @param f2 "Male" function
     */
    public BDD(int var, BDD f1, BDD f2)
    {
        this(var, f1, f2, true);
    }
    
    /**
     * Build a SyKAT.BDD by composing f1 and f2.
     * 
     * @param var The output of f2 is fed into the varth input of f1
     * @param f1 "Female" function
     * @param f2 "Male" function
     * @param autoConcatonate If true, does elementary composition.
     *          If false, the inputs are unmodified, and the resulting
     *          composition isn't as intuitive.  (To understand how it works,
     *          have a good ponder of Shannon's Expansion).
     */
    public BDD(int var, BDD f1, BDD f2, boolean autoConcatonate)
    {
        assert(var < f1.getNumInputs());
        f1 = new BDD(f1);
        f2 = new BDD(f2);
        if (autoConcatonate)
        {
            int oldF1NumInputs = f1.tree.getNumInputs();
            f1.tree.preConcatonateInputs(f2.tree.getNumInputs());
            var += f2.tree.getNumInputs(); // The varth input of f1 has moved
            f2.tree.postConcatonateInputs(oldF1NumInputs);
        }
        
        buildThisFromComposition(var, f1, f2);
        
        if (autoConcatonate)
            this.tree.collapseInput(var);
    }
    
    private void buildThisFromCompositionFast(int var, BDD f1, BDD f2)
    {
        // TODO The existing algorithm is O(m^2 n^2), where m and n are the
        // number of nodes in f1 and f2, respectively.  An O(m^2 n) algorithm
        // exists in Bryant.  It should be implemented here.
    }
    
    private void buildThisFromComposition(int var, BDD f1, BDD f2)
    {
        
        /* This algorithm uses the Shannon Expansion of boolean functions to
         * express the composition in terms of apply() and restrict().  If
         * f|var1=0 represents f.restrict(1, false), then the composition
         * f1|var=f2 = (f2 & f1|var=1) | (!f2 & f1|var=0).
         * 
         * See Randall E. Bryant, "Graph-Based Algorithms for Boolean Function
         * Manipulation," IEEE Transactions on Computers, 1986.
         * 
         * We simply run this expression.  This is not the fastest way to
         * do composition, but it's easier to understand/implement int terms of
         * apply() and restrict().
         */
        
        // Special case: If f2 is a constant, just restrict.
        if (f2.isConstant())
        {
            BDD f1_restricted = new BDD(f1, var, (Boolean) f2.tree.getRootNode().terminalValue);
            this.tree = new BDDTree(f1_restricted.tree);
            return;
        }           
        
        final Operator and = new Operator<Boolean>() {@Override public Boolean operate(Boolean x, Boolean y) { return (x&y); }};
        final Operator or = new Operator<Boolean>() {@Override public Boolean operate(Boolean x, Boolean y) { return (x|y); }};
        
        BDD f1_restrictedHigh = new BDD(f1, var, true);
        BDD f1_restrictedLow = new BDD(f1, var, false);
        BDD x = new BDD(and, f2, f1_restrictedHigh);
        BDD f2_not = new BDD(f2, true);
        BDD y = new BDD(and, f2_not, f1_restrictedLow);
        
        this.tree = new BDDTree(x.tree.getNumInputs());
        HashMap<Integer[], Integer> dynamicProgrammingMemory = new HashMap();
        apply(dynamicProgrammingMemory, or, x.tree, y.tree, x.tree.getRootIndex(), y.tree.getRootIndex());
    }
   
    @Override
    public int getNumInputs()
    {
        return this.tree.getNumInputs();
    }

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
    
    public boolean isConstant()
    {
        return (this.tree.getRootNode().isTerminal());
    }
    
    public void preConcatonateInputs(int numInputsToAdd)
    {
        this.tree.preConcatonateInputs(numInputsToAdd);
    }
    
    public void postConcatonateInputs(int numInputsToAdd)
    {
        this.tree.postConcatonateInputs(numInputsToAdd);
    }
    
    public void collapseInput(int var)
    {
        this.tree.collapseInput(var);
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
            System.out.println(""+currentNode.low+" "+currentNode.high+" "+currentNode.inputIndex);
            if (currentNode.inputIndex == i)
                currentNode = (input[i] ? tree.getNode(currentNode.high) : tree.getNode(currentNode.low));
        }

        return currentNode.terminalValue;
    }
    
    /**
     * Reduce this SyKAT.BDD into a compact representation.  Normally, BDDs
     * have memory complexity exponential in the number of inputs.  Reducing
     * them collapses isomorphic subgraphs (i.e. eliminates redundancy in the
     * decision tree) to make it much more compact.
     */
    public void reduce()
    {
        //TODO
    }
    
    
    /**
     * Build a new SyKAT.BDD by applying a operator to two existing ones.
     * See Anderson (1997) for an explanation of this algorithm.
     */
    private <T> void apply(HashMap dynamicProgrammingMemory, Operator<T> op, BDDTree<T> xTree, BDDTree<T> yTree, int xIndex, int yIndex)
    {
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
            output = mk(new Node(op.operate(x.terminalValue, y.terminalValue), tree.getNumInputs()));
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
     * Build a new SyKAT.BDD by restricting one of the inputs of a pre-existing one.
     */
    private int restrict(BDDTree xTree, int currentIndex, int inputToFix, boolean value, HashMap<Integer, Integer> dpMemory)
    {
        /* We search for all nodes with Node.inputIndex = inputIndex and replace
         * them with their low- or high-child depending on the parity of value.
         * From Anderson (1997). */
        if (dpMemory.containsKey(currentIndex))
            return dpMemory.get(currentIndex);
        
        Node u = xTree.getNode(currentIndex);
        int returnValue = 0;
        
        if (u.inputIndex > inputToFix)
            returnValue = currentIndex;
        else if (u.inputIndex < inputToFix)
            returnValue = mk(new Node(restrict(xTree, u.low, inputToFix, value, dpMemory), restrict(xTree, u.high, inputToFix, value, dpMemory), u.inputIndex));
        else if (!value)
            returnValue = restrict(xTree, u.low, inputToFix, value, dpMemory);
        else
            returnValue = restrict(xTree, u.high, inputToFix, value, dpMemory);
        
        dpMemory.put(currentIndex, returnValue);
        return returnValue;
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
    public boolean isAction() { return isForAction; }
}
