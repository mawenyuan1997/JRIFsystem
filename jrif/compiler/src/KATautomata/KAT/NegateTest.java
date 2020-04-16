package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;

public class NegateTest extends TestExpr {

    public TestExpr test;

    public NegateTest(TestExpr expr) {
        test = expr;
    }

    public Object accept(KATexpressionVisitor visitor) {
        return visitor.visit(this);
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
