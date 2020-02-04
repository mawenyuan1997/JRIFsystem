package SyKAT;

import SyKAToperator.SyKATexpressionVisitor;

public class Star implements SyKATexpression {

    public SyKATexpression p;

    public Star(SyKATexpression expr) {p = expr;}

    @Override
    public Object accept(SyKATexpressionVisitor visitor) { return visitor.visit(this); }

    @Override
    public int hashCode() {
        return ("@" + p.hashCode()).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Star)) return false;
        Star e = (Star) o;
        return this.p.equals(e.p);
    }

    public String toString() {
        return "(" + p.toString() + ")*";
    }
}
