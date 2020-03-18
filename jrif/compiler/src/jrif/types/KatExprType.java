package jrif.types;

import KATautomata.KAT.KATexpression;

public class KatExprType {
    private KATexpression expr;

    public KatExprType(KATexpression e) {
        expr = e;
    }

    public KATexpression getExpr() {
        return expr;
    }
}
