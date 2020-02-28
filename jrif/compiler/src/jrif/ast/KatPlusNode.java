package jrif.ast;

import KAT.KATexpression;
import KAT.PlusExpression;
import polyglot.util.Position;

public class KatPlusNode extends KatExprNode {

    KatExprNode left, right;
    public KatPlusNode(Position pos, KatExprNode e1, KatExprNode e2) {
        super(pos);
        left = e1;
        right = e2;
    }

    public KATexpression disambiguate() {
        KATexpression l = left.disambiguate();
        KATexpression r = right.disambiguate();
        return new PlusExpression(l, r);
    }
}
