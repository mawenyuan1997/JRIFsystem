package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;

public class PlusTest extends TestExpr {

    public TestExpr left, right;

    public PlusTest(TestExpr p, TestExpr q) {
        left = p;
        right = q;
    }

    @Override
    public boolean isCanonical() {
        return left.isCanonical() && right.isCanonical();
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

    @Override
    public int hashCode() {
        return ("" + left.hashCode() + "+" + right.hashCode()).hashCode();
    }
}
