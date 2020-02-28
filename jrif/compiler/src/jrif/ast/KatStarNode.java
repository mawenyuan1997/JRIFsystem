package jrif.ast;

import KAT.KATexpression;
import KAT.StarExpression;
import polyglot.util.Position;

public class KatStarNode extends KatExprNode {

    KatExprNode expr;
    public KatStarNode(Position pos, KatExprNode e) {
        super(pos);
        expr = e;
    }

    public KATexpression disambiguate() {
        KATexpression l = expr.disambiguate();
        return new StarExpression(l);
    }
}
