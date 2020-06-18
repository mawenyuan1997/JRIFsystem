package KATautomata.SyKAT;

import KATautomata.SyKAToperator.SyKATexpressionVisitor;
import polyglot.util.SerialVersionUID;

import java.io.Serializable;

public class Star implements SyKatExpr, Serializable {
    private static final long serialVersionUID = SerialVersionUID.generate();
    public SyKatExpr p;

    public Star(SyKatExpr expr) {p = expr;}

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
