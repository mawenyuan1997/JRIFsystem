package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;

public class ZeroTest extends TestExpr {
    public ZeroTest() {}

    public Object accept(KATexpressionVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean isCanonical() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o instanceof ZeroTest) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
