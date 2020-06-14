package jrif.types;

import KATautomata.KAT.KatExpr;
import polyglot.util.SerialVersionUID;

public class KatExprType {
    private static final long serialVersionUID = SerialVersionUID.generate();
    private KatExpr expr;

    public KatExprType(KatExpr e) {
        expr = e;
    }

    public KatExpr getExpr() {
        return expr;
    }
}
