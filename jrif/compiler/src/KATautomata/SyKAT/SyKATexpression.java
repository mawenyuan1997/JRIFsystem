package KATautomata.SyKAT;

import KATautomata.SyKAToperator.SyKATexpressionVisitor;

public interface SyKATexpression {
    Object accept(SyKATexpressionVisitor visitor);
}
