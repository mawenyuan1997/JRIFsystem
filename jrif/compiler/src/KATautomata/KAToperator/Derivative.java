package KATautomata.KAToperator;

import KAT.*;

import java.util.HashMap;
/*
public class Derivative implements KATexpressionVisitor {

    HashMap<String, Boolean> atom;
    String action;
    Evaluation E;

    public Derivative(HashMap<String, Boolean> a, String p) {
        atom = new HashMap<>(a);
        action = p;
        E = new Evaluation(a);
    }

    @Override
    public Object visit(Action expr) {
        if (expr.id.equals(action))
            return new OneTest();
        else
            return new ZeroTest();
    }

    @Override
    public Object visit(PlusExpression expr) {
        return new PlusExpression(
                (KATexpression) expr.left.accept(this),
                (KATexpression) expr.right.accept(this)
        );
    }

    @Override
    public Object visit(ConcatExpression expr) {
        KATexpression t1 = new ConcatExpression(
                                    (KATexpression) expr.left.accept(this),
                                    expr.right
        );
        KATexpression t2 = null;
        if ((Boolean) expr.left.accept(E)) {
            t2 = new ConcatExpression(
                    new OneTest(), (KATexpression) expr.right.accept(this)
            );
        } else {
            t2 = new ConcatExpression(
                    new ZeroTest(), (KATexpression) expr.right.accept(this)
            );
        }
        return new PlusExpression(t1, t2);
    }

    @Override
    public Object visit(StarExpression expr) {
        return new ConcatExpression(
                    (KATexpression) expr.p.accept(this),
                    new StarExpression(expr.p)
        );
    }

    @Override
    public Object visit(ConcatTest expr) {
        return new ZeroTest();
    }

    @Override
    public Object visit(NegateTest expr) {
        return new ZeroTest();
    }

    @Override
    public Object visit(OneTest expr) {
        return new ZeroTest();
    }

    @Override
    public Object visit(PlusTest expr) {
        return new ZeroTest();
    }

    @Override
    public Object visit(PrimitiveTest expr) {
        return new ZeroTest();
    }

    @Override
    public Object visit(ZeroTest expr) {
        return new ZeroTest();
    }

}
*/