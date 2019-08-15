package KAT;

import KAToperator.KATexpressionVisitor;

public class OneTest extends TestExpression {
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
}
