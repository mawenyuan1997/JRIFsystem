package jrif.ast;

import KAT.OneTest;
import KAT.TestExpression;
import polyglot.util.Position;

public class KatOneNode extends KatTestNode {
    public KatOneNode(Position pos) {
        super(pos);
    }

    public TestExpression disambiguate() {
        return new OneTest();
    }
}
