package KAToperator;

import KAT.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PartialDerivative implements KATexpressionVisitor{

    HashMap<String, Boolean> atom;
    String action;
    Evaluation E;

    public PartialDerivative(HashMap<String, Boolean> a, String p) {
        atom = new HashMap<>(a);
        action = p;
        E = new Evaluation(a);
    }

    @Override
    public Object visit(PrimitiveAction expr) {
        HashSet<KATexpression> singleton = new HashSet<>();
        if (expr.id.equals(action))
            singleton.add(new OneTest());
        return singleton;
    }

    @Override
    public Object visit(PlusExpression expr) {
        HashSet<KATexpression> s = (HashSet<KATexpression>) expr.left.accept(this);
        s.addAll((HashSet<KATexpression>) expr.right.accept(this));
        return s;
    }

    @Override
    public Object visit(ConcatExpression expr) {
        HashSet<KATexpression> dx = (HashSet<KATexpression>) expr.left.accept(this);
        HashSet<KATexpression> dxy = dx.stream()
                                       .map(e -> new ConcatExpression(e, expr.right))
                                       .collect(Collectors.toCollection(HashSet::new));

        if ((Boolean) expr.left.accept(E)) {
            dxy.addAll((HashSet<KATexpression>) expr.right.accept(this));
        }

        return dxy;
    }

    @Override
    public Object visit(StarExpression expr) {
        HashSet<KATexpression> dx = (HashSet<KATexpression>) expr.p.accept(this);
        return dx.stream()
                 .map(e -> new ConcatExpression(e, expr))
                 .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Object visit(ConcatTest expr) {
        return new HashSet<KATexpression>();
    }

    @Override
    public Object visit(NegateTest expr) {
        return new HashSet<KATexpression>();
    }

    @Override
    public Object visit(OneTest expr) {
        return new HashSet<KATexpression>();
    }

    @Override
    public Object visit(PlusTest expr) {
        return new HashSet<KATexpression>();
    }

    @Override
    public Object visit(PrimitiveTest expr) {
        return new HashSet<KATexpression>();
    }

    @Override
    public Object visit(ZeroTest expr) {
        return new HashSet<KATexpression>();
    }
}
