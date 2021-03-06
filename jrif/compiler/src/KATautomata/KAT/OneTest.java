package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;
import jif.types.LabelSubstitution;
import jif.types.principal.Principal;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.SerialVersionUID;

import java.util.ArrayList;
import java.util.List;

/**
 * KAT One class
 *
 * author: Wenyuan Ma   date:2020-07-03
 */
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
    public void subst(LabelSubstitution substitution) {
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
