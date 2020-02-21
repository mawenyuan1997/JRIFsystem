package jrif.ast;

import polyglot.util.Position;

public class KatOrNode extends KatTestNode {
    KatTestNode left, right;
    public KatOrNode(Position pos, KatTestNode e1, KatTestNode e2) {
        super(pos);
        left = e1;
        right = e2;
    }
}
