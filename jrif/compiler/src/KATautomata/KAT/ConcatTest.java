package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;

public class ConcatTest extends TestExpr {

    public TestExpr left, right;

    public ConcatTest(TestExpr p, TestExpr q) {
        left = p;
        right = q;
    }

    public Object accept(KATexpressionVisitor visitor) {
       return  visitor.visit(this);
    }

    @Override
    public boolean isCanonical() {
        return left.isCanonical() && right.isCanonical();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ConcatTest)) return false;
        ConcatTest e = (ConcatTest) o;
        return this.left.equals(e.left) && this.right.equals(e.right);
    }

    @Override
    public int hashCode() {
        return ("" + left.hashCode() + "*" + right.hashCode()).hashCode();
    }
}
