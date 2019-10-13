package SyKAToperator;

import SyKAT.BDD.BDD;
import SyKAT.Concat;
import SyKAT.Plus;
import SyKAT.Star;

public class Delta implements SyKATexpressionVisitor {
    @Override
    public Object visit(BDD expr) {
        BDD bdd = (BDD) expr;
        if (!bdd.isAction()) {
            return new BDD(BDD.Function.FALSE);
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
