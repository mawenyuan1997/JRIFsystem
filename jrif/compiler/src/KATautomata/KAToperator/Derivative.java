package KATautomata.KAToperator;


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
    public Object visit(PlusExpr expr) {
        return new PlusExpr(
                (KatExpr) expr.left.accept(this),
                (KatExpr) expr.right.accept(this)
        );
    }

    @Override
    public Object visit(ConcatExpr expr) {
        KatExpr t1 = new ConcatExpr(
                                    (KatExpr) expr.left.accept(this),
                                    expr.right
        );
        KatExpr t2 = null;
        if ((Boolean) expr.left.accept(E)) {
            t2 = new ConcatExpr(
                    new OneTest(), (KatExpr) expr.right.accept(this)
            );
        } else {
            t2 = new ConcatExpr(
                    new ZeroTest(), (KatExpr) expr.right.accept(this)
            );
        }
        return new PlusExpr(t1, t2);
    }

    @Override
    public Object visit(StarExpr expr) {
        return new ConcatExpr(
                    (KatExpr) expr.p.accept(this),
                    new StarExpr(expr.p)
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