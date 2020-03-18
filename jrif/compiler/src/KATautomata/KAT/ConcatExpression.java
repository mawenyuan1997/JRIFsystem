package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;

public class ConcatExpression implements KATexpression {

    public KATexpression left,right;

    public ConcatExpression(KATexpression p, KATexpression q) {
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
        if (!(o instanceof ConcatExpression)) return false;
        ConcatExpression e = (ConcatExpression) o;
        return this.left.equals(e.left) && this.right.equals(e.right);
    }

    @Override
    public int hashCode() {
        return ("" + left.hashCode() + "*" + right.hashCode()).hashCode();
    }
}
