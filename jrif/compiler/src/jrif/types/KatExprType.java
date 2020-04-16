package jrif.types;

import KATautomata.KAT.KatExpr;

public class KatExprType {
    private KatExpr expr;

    public KatExprType(KatExpr e) {
        expr = e;
    }

    public KatExpr getExpr() {
        return expr;
    }
}
