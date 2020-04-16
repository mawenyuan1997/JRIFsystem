package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;

public class ConcatExpr implements KatExpr {

    public KatExpr left,right;

    public ConcatExpr(KatExpr p, KatExpr q) {
        left = p;
        right = q;
    }

    @Override
    public Object accept(KATexpressionVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ConcatExpr)) return false;
        ConcatExpr e = (ConcatExpr) o;
        return this.left.equals(e.left) && this.right.equals(e.right);
    }

    @Override
    public int hashCode() {
        return ("" + left.hashCode() + "*" + right.hashCode()).hashCode();
    }
}
