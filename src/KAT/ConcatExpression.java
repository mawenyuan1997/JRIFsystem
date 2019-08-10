package KAT;

public class ConcatExpression implements KATexpression {

    KATexpression left,right;

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
}
