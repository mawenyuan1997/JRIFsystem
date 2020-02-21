package jrif.ast;

import polyglot.util.Position;

public class KatNegateNode extends KatTestNode {
    KatTestNode test;
    public KatNegateNode(Position pos, KatTestNode b) {
        super(pos);
        test = b;
    }
}
