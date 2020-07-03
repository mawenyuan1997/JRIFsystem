package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;
import jif.types.LabelSubstitution;
import jif.types.principal.Principal;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.SerialVersionUID;

import java.util.List;

public class StarExpr implements KatExpr {
    private static final long serialVersionUID = SerialVersionUID.generate();
    public KatExpr p;

    public StarExpr(KatExpr expr) {
        this.p = expr;
    }

    @Override
    public Object accept(KATexpressionVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof StarExpr)) return false;
        StarExpr e = (StarExpr) o;
        return this.p.equals(e.p);
    }

    @Override
    public List<Principal> principals() {
        return p.principals();
    }

    @Override
    public void subst(LabelSubstitution substitution) throws SemanticException {
        p.subst(substitution);
    }

    @Override
    public int hashCode() {
        return ("@" + p.hashCode()).hashCode();
    }
}
