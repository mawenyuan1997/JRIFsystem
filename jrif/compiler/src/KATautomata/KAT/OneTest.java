package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;
import jif.types.principal.Principal;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.SerialVersionUID;

import java.util.ArrayList;
import java.util.List;

public class OneTest extends TestExpr {
    private static final long serialVersionUID = SerialVersionUID.generate();
    public OneTest() {}

    public Object accept(KATexpressionVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o instanceof OneTest) return true;
        return false;
    }

    @Override
    public List<Principal> principals() {
        return new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
