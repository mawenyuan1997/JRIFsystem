package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;

public class StarExpr implements KatExpr {

    public KatExpr p;

    public StarExpr(KatExpr expr) {
        this.p = expr;
    }

    @Override
    public boolean isCanonical() {
        return p.isCanonical();
    }

    @Override
    public Object accept(KATexpressionVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof StarExpr)) return false;
        StarExpr e = (StarExpr) o;
        return this.p.equals(e.p);
    }

    @Override
    public int hashCode() {
        return ("@" + p.hashCode()).hashCode();
    }
}
