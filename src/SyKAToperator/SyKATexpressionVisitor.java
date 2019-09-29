package SyKAToperator;

import SyKAT.BDD.BDD;
import SyKAT.Concat;
import SyKAT.Plus;
import SyKAT.Star;

public interface SyKATexpressionVisitor {
    Object visit(BDD expr);
    Object visit(Concat expr);
    Object visit(Plus expr);
    Object visit(Star expr);
}
