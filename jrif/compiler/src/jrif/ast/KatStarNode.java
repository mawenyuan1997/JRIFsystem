package jrif.ast;

import polyglot.util.Position;

public class KatStarNode extends KatExprNode {

    KatExprNode expr;
    public KatStarNode(Position pos, KatExprNode e) {
        super(pos);
        expr = e;
    }
}
