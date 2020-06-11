package KATautomata.KAToperator;

/*
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
    public Object visit(Action expr) {
        HashSet<KatExpr> singleton = new HashSet<>();
        if (expr.id.equals(action))
            singleton.add(new OneTest());
        return singleton;
    }

    @Override
    public Object visit(PlusExpr expr) {
        HashSet<KatExpr> s = (HashSet<KatExpr>) expr.left.accept(this);
        s.addAll((HashSet<KatExpr>) expr.right.accept(this));
        return s;
    }

    @Override
    public Object visit(ConcatExpr expr) {
        HashSet<KatExpr> dx = (HashSet<KatExpr>) expr.left.accept(this);
        HashSet<KatExpr> dxy = dx.stream()
                                       .map(e -> new ConcatExpr(e, expr.right))
                                       .collect(Collectors.toCollection(HashSet::new));

        if ((Boolean) expr.left.accept(E)) {
            dxy.addAll((HashSet<KatExpr>) expr.right.accept(this));
        }

        return dxy;
    }

    @Override
    public Object visit(StarExpr expr) {
        HashSet<KatExpr> dx = (HashSet<KatExpr>) expr.p.accept(this);
        return dx.stream()
                 .map(e -> new ConcatExpr(e, expr))
                 .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Object visit(ConcatTest expr) {
        return new HashSet<KatExpr>();
    }

    @Override
    public Object visit(NegateTest expr) {
        return new HashSet<KatExpr>();
    }

    @Override
    public Object visit(OneTest expr) {
        return new HashSet<KatExpr>();
    }

    @Override
    public Object visit(PlusTest expr) {
        return new HashSet<KatExpr>();
    }

    @Override
    public Object visit(PrimitiveTest expr) {
        return new HashSet<KatExpr>();
    }

    @Override
    public Object visit(ZeroTest expr) {
        return new HashSet<KatExpr>();
    }
}
*/