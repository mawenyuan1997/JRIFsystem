package KATautomata.KAToperator;

import KATautomata.KAT.*;

public class KATprinter implements KATexpressionVisitor {
    @Override
    public Object visit(Action expr) {
        return expr.getPrimActions().toString();
    }

    @Override
    public Object visit(PlusExpr expr) {
        return expr.left.accept(this) + "+" + expr.right.accept(this);
    }

    @Override
    public Object visit(ConcatExpr expr) {
        return "(" + expr.left.accept(this) + ")"
                + "(" + expr.right.accept(this) + ")";
    }

    @Override
    public Object visit(StarExpr expr) {
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
        return expr.getString();
    }

    @Override
    public Object visit(ZeroTest expr) {
        return "0";
    }
}
