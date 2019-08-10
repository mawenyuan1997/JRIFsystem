package KAT;

public interface KATexpressionVisitor {
    Object visit(PrimitiveAction expr);
    Object visit(PlusExpression expr);
    Object visit(ConcatExpression expr);
    Object visit(StarExpression expr);
    Object visit(ConcatTest expr);
    Object visit(NegateTest expr);
    Object visit(OneTest expr);
    Object visit(PlusTest expr);
    Object visit(PrimitiveTest expr);
    Object visit(ZeroTest expr);
}
