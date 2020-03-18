package KATautomata.SyKAToperator;

import KATautomata.SyKAT.BDD.BDD;
import KATautomata.SyKAT.Concat;
import KATautomata.SyKAT.Plus;
import KATautomata.SyKAT.Star;

public interface SyKATexpressionVisitor {
    Object visit(BDD<Boolean> expr);
    Object visit(Concat expr);
    Object visit(Plus expr);
    Object visit(Star expr);
}
