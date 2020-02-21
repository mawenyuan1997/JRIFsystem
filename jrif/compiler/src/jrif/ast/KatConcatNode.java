package jrif.ast;

import KAT.ConcatExpression;
import polyglot.util.Position;

public class KatConcatNode extends KatExprNode {

    KatExprNode left, right;
    public KatConcatNode(Position pos, KatExprNode e1, KatExprNode e2) {
        super(pos);
        left = e1;
        right = e2;
    }

    @Override
    public void disambiguate() {
        left.disambiguate();
        right.disambiguate();
        this.policy = new ConcatExpression(left.policy, right.policy);
    }
}
