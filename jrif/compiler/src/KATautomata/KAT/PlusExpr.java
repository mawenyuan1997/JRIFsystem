package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;

public class PlusExpr implements KatExpr {

    public KatExpr left, right;
    public PlusExpr(KatExpr p, KatExpr q) {
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
        if (!(o instanceof PlusExpr)) return false;
        PlusExpr e = (PlusExpr) o;
        return this.left.equals(e.left) && this.right.equals(e.right) ||
                this.left.equals(e.right) && this.right.equals(e.left);
    }

    @Override
    public int hashCode() {
        return ("" + left.hashCode() + "+" + right.hashCode()).hashCode();
    }
}

