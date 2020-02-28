package jrif.ast;

import KAT.ConcatTest;
import KAT.KATexpression;
import KAT.TestExpression;
import polyglot.util.Position;

public class KatAndNode extends KatTestNode {
    KatTestNode left, right;
    public KatAndNode(Position pos, KatTestNode e1, KatTestNode e2) {
        super(pos);
        left = e1;
        right = e2;
    }


    public TestExpression disambiguate() {
        TestExpression l = left.disambiguate();
        TestExpression r = right.disambiguate();
        return new ConcatTest(l, r);
    }
}
