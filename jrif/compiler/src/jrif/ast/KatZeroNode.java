package jrif.ast;

import KAT.TestExpression;
import KAT.ZeroTest;
import polyglot.util.Position;

public class KatZeroNode extends KatTestNode {
    public KatZeroNode(Position pos) {
        super(pos);
    }

    public TestExpression disambiguate() {
        return new ZeroTest();
    }
}
