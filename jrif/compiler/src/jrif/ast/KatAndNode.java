package jrif.ast;

import KAT.ConcatTest;
import KAT.TestExpression;
import polyglot.util.Position;

public class KatAndNode extends KatTestNode {
    KatTestNode left, right;
    public KatAndNode(Position pos, KatTestNode e1, KatTestNode e2) {
        super(pos);
        left = e1;
        right = e2;
    }

    @Override
    public void disambiguate() {
        left.disambiguate();
        right.disambiguate();
        this.policy = new ConcatTest((TestExpression) left.policy, (TestExpression) right.policy);
    }
}
