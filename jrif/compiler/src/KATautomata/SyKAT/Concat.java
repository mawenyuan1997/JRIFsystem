package KATautomata.SyKAT;

import KATautomata.SyKAToperator.SyKATexpressionVisitor;
import polyglot.util.SerialVersionUID;

import java.io.Serializable;

/**
 * Symbolic KAT Concat class
 *
 * author: Wenyuan Ma   date:2020-06-17
 */
public class Concat implements SyKatExpr, Serializable {
    private static final long serialVersionUID = SerialVersionUID.generate();
    public SyKatExpr left,right;

    public Concat(SyKatExpr l, SyKatExpr r) {
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
