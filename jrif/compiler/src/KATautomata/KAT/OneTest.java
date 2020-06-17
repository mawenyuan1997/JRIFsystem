package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;

public class OneTest extends TestExpr {
    public OneTest() {}

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
        if (o instanceof OneTest) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
