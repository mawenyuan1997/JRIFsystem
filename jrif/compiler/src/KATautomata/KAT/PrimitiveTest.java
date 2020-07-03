package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;
import jif.types.LabelSubstitution;
import jif.types.principal.Principal;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.SerialVersionUID;

import java.util.ArrayList;
import java.util.List;

public class PrimitiveTest extends TestExpr {
    private static final long serialVersionUID = SerialVersionUID.generate();
    private Principal principal;

    private String str; // only for testing

    public PrimitiveTest(Principal p) {
        principal = p;
    }

    // for testing
    public PrimitiveTest(String s) {str = s;}

    public Object accept(KATexpressionVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public List<Principal> principals() {
        List<Principal> l = new ArrayList<>();
        l.add(principal);
        return l;
    }

    @Override
    public void subst(LabelSubstitution substitution)throws SemanticException {
        principal.subst(substitution);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PrimitiveTest)) return false;
        PrimitiveTest e = (PrimitiveTest) o;
        return this.principal.equals(e.principal);
    }

    @Override
    public int hashCode() {
        return principal.hashCode();
    }

    public String getString() {
        if (principal == null) return str;
        return principal.getClass().toString();
    }
}
