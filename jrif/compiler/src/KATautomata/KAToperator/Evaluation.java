package KATautomata.KAToperator;

import java.util.HashMap;
/*
public class Evaluation implements KATexpressionVisitor {

    HashMap<String, Boolean> atom;

    public Evaluation(HashMap<String, Boolean> a) {
        atom = a;
    }

    @Override
    public Object visit(Action expr) {
        return false;
    }

    @Override
    public Object visit(PlusExpr expr) {
        return (Boolean) expr.left.accept(this)
                || (Boolean) expr.right.accept(this);
    }

    @Override
    public Object visit(ConcatExpr expr) {
        return (Boolean) expr.left.accept(this)
                && (Boolean) expr.right.accept(this);
    }

    @Override
    public Object visit(StarExpr expr) {
        return true;
    }

    @Override
    public Object visit(ConcatTest expr) {
        return (Boolean) expr.left.accept(this)
                && (Boolean) expr.right.accept(this);
    }

    @Override
    public Object visit(NegateTest expr) {
        return ! (Boolean) expr.test.accept(this);
    }

    @Override
    public Object visit(OneTest expr) {
        return true;
    }

    @Override
    public Object visit(PlusTest expr) {
        return (Boolean) expr.left.accept(this)
                || (Boolean) expr.right.accept(this);
    }

    @Override
    public Object visit(PrimitiveTest expr) {
        return atom.get(expr.id);
    }

    @Override
    public Object visit(ZeroTest expr) {
        return false;
    }
}
*/