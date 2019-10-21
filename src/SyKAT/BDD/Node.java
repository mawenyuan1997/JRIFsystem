/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SyKAT.BDD;

/**
 *
 * @author Eric 'Siggy' Scott
 */
public class Node<T>
{
    /* If you add fields, don't forget to update the copy constructor! */
    int low; // Index for the table entry in BDDTree that points to the child
    int high;
    int inputIndex;
    T terminalValue;
    
    public Node(T value, int numInputs)
    {
        this.low = -1;
        this.high = -1;
        this.inputIndex = numInputs;
        this.terminalValue = value;
    }
    
    public Node(int lowChild, int highChild, int inputIndex)
    {
        this.low = lowChild;
        this.high = highChild;
        this.inputIndex = inputIndex;
    }
    
    /**
     * Copy constructor 
     */
    public Node(Node<T> n)
    {
        this.low = n.low;
        this.high = n.high;
        this.inputIndex = n.inputIndex;
        this.terminalValue = n.terminalValue;
    }
    
    public boolean isTerminal()
    {
        return ((low == -1 && high == -1) ? true : false);
    }
    
    @Override
    public boolean equals(Object n)
    {
        if (! (n instanceof Node))
            return false;
        return (    (this.low == ((Node)n).low)
                &&  (this.high == ((Node)n).high)
                &&  (this.inputIndex == ((Node)n).inputIndex)
                &&  (this.terminalValue == null && ((Node)n).terminalValue == null ||
                this.terminalValue.equals(((Node)n).terminalValue)));
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 29 * hash + this.low;
        hash = 29 * hash + this.high;
        hash = 29 * hash + this.inputIndex;
        if (this.terminalValue != null) {
            hash = 29 * hash + this.terminalValue.hashCode();
        }
        return hash;
    }
}

