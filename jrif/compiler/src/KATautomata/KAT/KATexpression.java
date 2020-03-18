package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;

public interface KATexpression {
    Object accept(KATexpressionVisitor visitor);
}
