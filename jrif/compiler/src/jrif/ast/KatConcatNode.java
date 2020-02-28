package jrif.ast;

import KAT.ConcatExpression;
import KAT.KATexpression;
import polyglot.util.Position;
import polyglot.visit.AmbiguityRemover;

public class KatConcatNode extends KatExprNode {

    KatExprNode left, right;
    public KatConcatNode(Position pos, KatExprNode e1, KatExprNode e2) {
        super(pos);
        left = e1;
        right = e2;
    }

    @Override
    public KATexpression disambiguate(AmbiguityRemover sc) {
        KATexpression l = left.disambiguate(sc);
        KATexpression r = right.disambiguate(sc);
        return new ConcatExpression(l, r);
    }
}
