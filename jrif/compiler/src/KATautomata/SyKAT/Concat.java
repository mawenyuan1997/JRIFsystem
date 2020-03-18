package KATautomata.SyKAT;

import KATautomata.SyKAToperator.SyKATexpressionVisitor;

public class Concat implements SyKATexpression {

    public SyKATexpression left,right;

    public Concat(SyKATexpression l, SyKATexpression r) {
        left = l;
        right = r;
    }

    @Override
    public Object accept(SyKATexpressionVisitor visitor) { return visitor.visit(this); }

    @Override
    public int hashCode() {
        return ("" + left.hashCode() + "*" + right.hashCode()).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Concat)) return false;
        Concat e = (Concat) o;
        return this.left.equals(e.left) && this.right.equals(e.right);
    }

    public String toString() {
        return "(" + left.toString() + ")(" + right.toString() + ")";
    }
}
