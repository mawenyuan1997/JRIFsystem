package SyKAToperator;

import SyKAT.BDD.BDD;
import SyKAT.BDD.BooleanBDDutil;
import SyKAT.Concat;
import SyKAT.Plus;
import SyKAT.Star;

import static SyKAT.BDD.BooleanBDDutil.BDDfromFunction;

public class Delta implements SyKATexpressionVisitor {
    @Override
    public Object visit(BDD bdd) {
        if (!bdd.isAction()) {
            return BDDfromFunction(BooleanBDDutil.Function.FALSE);
        }
        return null;
    }

    @Override
    public Object visit(Concat expr) {
        return null;
    }

    @Override
    public Object visit(Plus expr) {
        return null;
    }

    @Override
    public Object visit(Star expr) {
        return null;
    }
}
