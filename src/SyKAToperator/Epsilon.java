package SyKAToperator;

import SyKAT.BDD.BDD;
import SyKAT.BDD.BooleanOperator;
import SyKAT.Concat;
import SyKAT.Plus;
import SyKAT.Star;

public class Epsilon implements SyKATexpressionVisitor {
    @Override
    public Object visit(BDD expr) {
        return new BDD(expr);
    }

    @Override
    public Object visit(Concat expr) {
        BDD l = (BDD) expr.left.accept(this);
        BDD r = (BDD) expr.right.accept(this);
        BooleanOperator op = new BooleanOperator() {
            @Override
            public boolean operate(boolean x, boolean y) {
                return x && y;
            }
        };
        return new BDD(op, l, r);
    }

    @Override
    public Object visit(Plus expr) {
        BDD l = (BDD) expr.left.accept(this);
        BDD r = (BDD) expr.right.accept(this);
        BooleanOperator op = new BooleanOperator() {
            @Override
            public boolean operate(boolean x, boolean y) {
                return x || y;
            }
        };
        return new BDD(op, l, r);
    }

    @Override
    public Object visit(Star expr) {
        return new BDD(BDD.Function.TRUE);
    }
}
