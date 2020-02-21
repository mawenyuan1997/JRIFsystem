package jrif.ast;

import polyglot.util.Position;

public class KatPlusNode extends KatExprNode {

    KatExprNode left, right;
    public KatPlusNode(Position pos, KatExprNode e1, KatExprNode e2) {
        super(pos);
        left = e1;
        right = e2;
    }
}
