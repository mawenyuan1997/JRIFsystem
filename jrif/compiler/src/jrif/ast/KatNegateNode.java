package jrif.ast;

import KAT.NegateTest;
import KAT.TestExpression;
import polyglot.util.Position;

public class KatNegateNode extends KatTestNode {
    KatTestNode test;
    public KatNegateNode(Position pos, KatTestNode b) {
        super(pos);
        test = b;
    }

    public TestExpression disambiguate() {
        TestExpression t = test.disambiguate();
        return new NegateTest(t);
    }
}
