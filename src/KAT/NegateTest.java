package KAT;

import KAToperator.KATexpressionVisitor;

public class NegateTest extends TestExpression {

    public TestExpression test;

    public NegateTest(TestExpression expr) {
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
}
