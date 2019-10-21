package SyKAToperator;

import SyKAT.BDD.BDD;
import SyKAT.BDD.Operator;
import SyKAT.Concat;
import SyKAT.Plus;
import SyKAT.Star;

public class Epsilon implements SyKATexpressionVisitor {
    @Override
    public Object visit(BDD expr) {
        if (expr.isAction())
            return new BDD(BDD.Function.FALSE);
        return new BDD(expr);
    }

    @Override
    public Object visit(Concat expr) {
        BDD l = (BDD) expr.left.accept(this);
        BDD r = (BDD) expr.right.accept(this);
        Operator<Boolean> op = new Operator<Boolean>() {
            @Override
            public Boolean operate(Boolean x, Boolean y) {
                return x && y;
            }
        };
        return new BDD(op, l, r);
    }

    @Override
    public Object visit(Plus expr) {
        BDD l = (BDD) expr.left.accept(this);
        BDD r = (BDD) expr.right.accept(this);
        Operator<Boolean> op = new Operator<Boolean>() {
            @Override
            public Boolean operate(Boolean x, Boolean y) { return x || y; }
        };
        return new BDD(op, l, r);
    }

    @Override
    public Object visit(Star expr) {
        return new BDD(BDD.Function.TRUE);
    }
}
