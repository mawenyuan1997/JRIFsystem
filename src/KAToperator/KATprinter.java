package KAToperator;

import KAT.*;

public class KATprinter implements KATexpressionVisitor {
    @Override
    public Object visit(PrimitiveAction expr) {
        return expr.id;
    }

    @Override
    public Object visit(PlusExpression expr) {
        return expr.left.accept(this) + "+" + expr.right.accept(this);
    }

    @Override
    public Object visit(ConcatExpression expr) {
        return "(" + expr.left.accept(this) + ")"
                + "(" + expr.right.accept(this) + ")";
    }

    @Override
    public Object visit(StarExpression expr) {
        return "(" + expr.p.accept(this) + ")*";
    }

    @Override
    public Object visit(ConcatTest expr) {
        return "(" + expr.left.accept(this) + ")"
                + "(" + expr.right.accept(this) + ")";
    }

    @Override
    public Object visit(NegateTest expr) {
        return "~(" + expr.test.accept(this) + ")";
    }

    @Override
    public Object visit(OneTest expr) {
        return "1";
    }

    @Override
    public Object visit(PlusTest expr) {
        return expr.left.accept(this) + "+" + expr.right.accept(this);
    }

    @Override
    public Object visit(PrimitiveTest expr) {
        return expr.id;
    }

    @Override
    public Object visit(ZeroTest expr) {
        return "0";
    }
}
