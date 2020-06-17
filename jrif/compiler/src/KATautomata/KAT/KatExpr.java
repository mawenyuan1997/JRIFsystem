package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;

public interface KatExpr {
    Object accept(KATexpressionVisitor visitor);
    boolean isCanonical();
}
