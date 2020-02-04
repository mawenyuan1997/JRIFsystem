package SyKAT;

import SyKAToperator.SyKATexpressionVisitor;

public class Plus implements SyKATexpression {

    public SyKATexpression left,right;

    public Plus(SyKATexpression l, SyKATexpression r) {
        left = l;
        right = r;
    }

    @Override
    public Object accept(SyKATexpressionVisitor visitor) { return visitor.visit(this); }

    @Override
    public int hashCode() {
        return ("" + left.hashCode() + "+" + right.hashCode()).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Plus)) return false;
        Plus e = (Plus) o;
        return this.left.equals(e.left) && this.right.equals(e.right);
    }

    public String toString() {
        return "(" + left.toString() + ")+(" + right.toString() + ")";
    }
}
