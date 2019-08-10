package KAT;

public class StarExpression implements KATexpression {

    KATexpression p;

    public StarExpression(KATexpression expr) {
        this.p = expr;
    }

    @Override
    public Object accept(KATexpressionVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof StarExpression)) return false;
        StarExpression e = (StarExpression) o;
        return this.p.equals(e.p);
    }
}
