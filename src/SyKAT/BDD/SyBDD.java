package SyKAT.BDD;

import SyKAT.SyKATexpression;

import java.util.HashMap;

public class SyBDD {

    /**
     * n ⊕ m apply pointwise syntactic sum operation from KAT expressions
     * to the two BDDs n and m: n ⊕ m = ⌊φ 􏰄→ ⌈n⌉(φ) + ⌈m⌉(φ)⌋;
     * @param dynamicProgrammingMemory
     * @param yTree
     * @param xIndex
     * @param yIndex
     */
    public void add(HashMap dynamicProgrammingMemory, SyBDD yTree, int xIndex, int yIndex) {
    }

    /**
     * n ⊙ x syntactically multiplies all leaves of the BDD n by the expression x,
     * from the right: n ⊙ x = ⌊φ 􏰄→ ⌊n⌋(φ) · x⌋;
     * @param x
     */
    public void concat(SyKATexpression x) {
    }

    /**
     * f ⊗ n “multiplies” the Boolean BDD f with the BDD n:
     * f ⊗ n = ⌊φ 􏰄→ ⌊n⌋(φ) if ⌊f⌋(φ) = 1, 0 otherwise⌋.
     * @param f
     */
    public void multiplyBooleanBDD(BDD f) {
    }

}
