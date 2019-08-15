package KAToperator;

import KAT.*;

public class Simplify implements KATexpressionVisitor {

    public Simplify() {}
    @Override
    public Object visit(PrimitiveAction expr) {
        return expr;
    }

    @Override
    public Object visit(PlusExpression expr) {
        KATexpression t1 = (KATexpression) expr.left.accept(this);
        KATexpression t2 = (KATexpression) expr.right.accept(this);
        if (t1 instanceof OneTest || t2 instanceof OneTest) return new OneTest();
        if (t1 instanceof ZeroTest) return t2;
        if (t2 instanceof ZeroTest) return t1;
        return new PlusExpression(t1, t2);
    }

    @Override
    public Object visit(ConcatExpression expr) {
        KATexpression t1 = (KATexpression) expr.left.accept(this);
        KATexpression t2 = (KATexpression) expr.right.accept(this);
        if (t1 instanceof ZeroTest || t2 instanceof ZeroTest) return new ZeroTest();
        if (t1 instanceof OneTest) return t2;
        if (t2 instanceof OneTest) return t1;
        return new ConcatExpression(t1, t2);
    }

    @Override
    public Object visit(StarExpression expr) {
        KATexpression t1 = (KATexpression) expr.p.accept(this);
        if (t1 instanceof OneTest) return new OneTest();
        if (t1 instanceof ZeroTest) return new ZeroTest();
        return new StarExpression(t1);
    }

    @Override
    public Object visit(ConcatTest expr) {
        TestExpression t1 = (TestExpression) expr.left.accept(this);
        TestExpression t2 = (TestExpression) expr.right.accept(this);
        if (t1 instanceof ZeroTest || t2 instanceof ZeroTest) return new ZeroTest();
        if (t1 instanceof OneTest) return t2;
        if (t2 instanceof OneTest) return t1;
        return new ConcatTest(t1, t2);
    }

    @Override
    public Object visit(NegateTest expr) {
        TestExpression t1 = (TestExpression) expr.test.accept(this);
        if (t1 instanceof OneTest) return new ZeroTest();
        if (t1 instanceof ZeroTest) return new OneTest();
        return new NegateTest(t1);
    }

    @Override
    public Object visit(OneTest expr) {
        return expr;
    }

    @Override
    public Object visit(PlusTest expr) {
        TestExpression t1 = (TestExpression) expr.left.accept(this);
        TestExpression t2 = (TestExpression) expr.right.accept(this);
        if (t1 instanceof OneTest || t2 instanceof OneTest) return new OneTest();
        if (t1 instanceof ZeroTest) return t2;
        if (t2 instanceof ZeroTest) return t1;
        return new PlusTest(t1, t2);
    }

    @Override
    public Object visit(PrimitiveTest expr) {
        return expr;
    }

    @Override
    public Object visit(ZeroTest expr) {
        return expr;
    }
}
