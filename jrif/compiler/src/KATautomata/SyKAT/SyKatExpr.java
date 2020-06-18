package KATautomata.SyKAT;

import KATautomata.SyKAToperator.SyKATexpressionVisitor;

public interface SyKatExpr {
    Object accept(SyKATexpressionVisitor visitor);
}
