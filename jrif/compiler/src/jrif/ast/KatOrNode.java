package jrif.ast;

import KAT.PlusTest;
import KAT.TestExpression;
import polyglot.util.Position;

public class KatOrNode extends KatTestNode {
    KatTestNode left, right;
    public KatOrNode(Position pos, KatTestNode e1, KatTestNode e2) {
        super(pos);
        left = e1;
        right = e2;
    }

    public TestExpression disambiguate() {
        TestExpression l = left.disambiguate();
        TestExpression r = right.disambiguate();
        return new PlusTest(l, r);
    }
}
