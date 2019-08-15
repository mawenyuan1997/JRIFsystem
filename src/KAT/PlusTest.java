package KAT;

import KAToperator.KATexpressionVisitor;

public class PlusTest extends TestExpression {

    public TestExpression left, right;

    public PlusTest(TestExpression p, TestExpression q) {
        left = p;
        right = q;
    }

    public Object accept(KATexpressionVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PlusTest)) return false;
        PlusTest e = (PlusTest) o;
        return this.left.equals(e.left) && this.right.equals(e.right) ||
                this.left.equals(e.right) && this.right.equals(e.left);
    }
}
