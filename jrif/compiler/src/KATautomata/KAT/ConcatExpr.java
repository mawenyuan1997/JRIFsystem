package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;
import jif.types.LabelSubstitution;
import jif.types.principal.Principal;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.SerialVersionUID;

import java.util.List;

public class ConcatExpr implements KatExpr {
    private static final long serialVersionUID = SerialVersionUID.generate();
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
    public List<Principal> principals() {
        List<Principal> l = left.principals();
        l.addAll(right.principals());
        return l;
    }

    @Override
    public void subst(LabelSubstitution substitution) throws SemanticException {
        left.subst(substitution);
        right.subst(substitution);
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
