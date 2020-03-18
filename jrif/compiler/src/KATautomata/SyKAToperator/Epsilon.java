package KATautomata.SyKAToperator;

import KATautomata.SyKAT.BDD.BDD;
import KATautomata.SyKAT.BDD.Operator;
import KATautomata.SyKAT.Concat;
import KATautomata.SyKAT.Plus;
import KATautomata.SyKAT.Star;
import KATautomata.SyKAT.BDD.BooleanBDDutil;
import KATautomata.SyKAT.SyKATexpression;

import static KATautomata.SyKAT.BDD.BooleanBDDutil.singleBooleanBDD;

public class Epsilon implements SyKATexpressionVisitor {
    int numInputs;
    public Epsilon(int num) {numInputs = num;}
    @Override
    public Object visit(BDD<Boolean> expr) {
        if (expr.isAction()) {
            return singleBooleanBDD(false, numInputs);
        }
        return new BDD<Boolean>(expr);
    }

    @Override
    public Object visit(Concat expr) {
        BDD<Boolean> l = (BDD<Boolean>) expr.left.accept(this);
        BDD<Boolean> r = (BDD<Boolean>) expr.right.accept(this);
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
        BDD<Boolean> l = (BDD<Boolean>) expr.left.accept(this);
        BDD<Boolean> r = (BDD<Boolean>) expr.right.accept(this);
        Operator<Boolean> op = new Operator<Boolean>() {
            @Override
            public Boolean operate(Boolean x, Boolean y) { return x || y; }
        };
        return new BDD(op, l, r);
    }

    @Override
    public Object visit(Star expr) {
        return singleBooleanBDD(true, numInputs);
    }
}
