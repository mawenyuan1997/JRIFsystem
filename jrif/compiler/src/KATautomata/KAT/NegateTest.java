package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;
import jif.types.LabelSubstitution;
import jif.types.principal.Principal;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.SerialVersionUID;

import java.util.List;

/**
 * KAT Negate class
 *
 * author: Wenyuan Ma   date:2020-07-03
 */
public class NegateTest extends TestExpr {
    private static final long serialVersionUID = SerialVersionUID.generate();
    public TestExpr test;

    public NegateTest(TestExpr expr) {
        test = expr;
    }

    public Object accept(KATexpressionVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public List<Principal> principals() {
        return test.principals();
    }

    @Override
    public void subst(LabelSubstitution substitution) throws SemanticException {
        test.subst(substitution);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof NegateTest)) return false;
        NegateTest e = (NegateTest) o;
        return this.test.equals(e.test);
    }

    @Override
    public int hashCode() {
        return ("-" + test.hashCode()).hashCode();
    }
}
