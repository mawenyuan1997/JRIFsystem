package SyKAT;

import SyKAToperator.SyKATexpressionVisitor;

public interface SyKATexpression {
    Object accept(SyKATexpressionVisitor visitor);
}
