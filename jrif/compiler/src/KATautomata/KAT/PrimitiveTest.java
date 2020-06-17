package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;
import jif.types.principal.Principal;

import java.util.List;

public class PrimitiveTest extends TestExpr {

    private Principal principal;

    public PrimitiveTest(Principal p) {
        principal = p;
    }

    public Object accept(KATexpressionVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean isCanonical() {
        if (this.principal == null) return true;
        if (!principal.isCanonical()) {
            return false;
        }
        return true;
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
        return principal.toString();
    }
}
