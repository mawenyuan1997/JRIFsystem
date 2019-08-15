package KAT;

import KAToperator.KATexpressionVisitor;

public interface KATexpression {
    Object accept(KATexpressionVisitor visitor);
}
