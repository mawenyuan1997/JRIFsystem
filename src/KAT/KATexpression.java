package KAT;

public interface KATexpression {
    Object accept(KATexpressionVisitor visitor);
}
